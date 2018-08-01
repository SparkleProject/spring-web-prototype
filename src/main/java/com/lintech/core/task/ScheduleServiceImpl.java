package com.lintech.core.task;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.dao.TaskDao;
import com.lintech.entity.Task;


/**
 * 任务调度中心实现类
 *
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    Logger logger=LoggerFactory.getLogger(getClass());
    private static Scheduler scheduler;
    private static Object lock = new Object();

    @Autowired
    TaskDao taskDao;

   
    
    public static Scheduler getScheduler() throws Exception {
        if (scheduler == null)
            synchronized (lock) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }
        return scheduler;
    }
    
    public  void startup() throws Exception {
        if (scheduler == null) {
            scheduler=getScheduler();
        }
        initializeTasks();
    }
    
    public  void shutdown() throws Exception {
        if (scheduler != null) {
            scheduler.shutdown();
            scheduler = null;
        }
    }
    
    
    public void startup(Task task) throws Exception {
        if(isScheduling(task.getId())){
            return;
        }
        add(task);
    }

    public void shutdown(Integer id) throws Exception {
        if(isScheduling(id)){
            delete(id);
        }
    }

    public void add(Task task) throws Exception {
        JobDetail jobDetail=null;
        Trigger trigger=null;
        jobDetail = getJobDetail(task);
        if(jobDetail==null)
            return;
        trigger = getTrigger(task);
        if(task.getState()!=null&&task.getState()==0){
            try{
                scheduler.scheduleJob(jobDetail, trigger);
                if(logger.isDebugEnabled()){
                    logger.debug(new StringBuilder()
                    .append("scheduler loaded task:")
                    .append(jobDetail.getFullName())
                    .append(jobDetail.getJobClass().toString()
                            ).toString());
                }
            }catch(Exception e){
                if(logger.isDebugEnabled()){
                    logger.debug(new StringBuilder()
                    .append("scheduler loaded task,but exception:")
                    .append(jobDetail.getName())
                    .append(jobDetail.getJobClass().toString()
                            ).toString());
                    e.printStackTrace();
                }
                throw new Exception(e);
            }
        }else{
            logger.debug(new StringBuilder()
            .append("scheduler loaded task,but state STOP:")
            .append(jobDetail.getName())
            .append(jobDetail.getJobClass().toString()
                    ).toString());
        }
    }
    
    public void delete(Integer id) throws Exception {
    	logger.warn("update task {}",id);
        if(isScheduling(id)){
        	scheduler.deleteJob(String.valueOf(id),Scheduler.DEFAULT_GROUP);
        }
    }
    
    public void update(Task task) throws Exception {
    	logger.warn("update task {}",task.getName());
        delete(task.getId());
        startup(task);
    }

    public void pause(Integer id) throws Exception {
    	logger.warn("pause task {}",id);
        if(isScheduling(id)){
            scheduler.pauseJob(String.valueOf(id),Scheduler.DEFAULT_GROUP);
        }
    }

    public void resume(Integer id) throws Exception {
    	logger.warn("resume task {}",id);
        if(isScheduling(id)){
            scheduler.resumeJob(String.valueOf(id),Scheduler.DEFAULT_GROUP);
        }else{
            Task task = taskDao.findOne(id);
            add(task);
        }
    }
    

    public void pauseAll() throws Exception {
    	logger.warn("pause all tasks");
        if(scheduler!=null)
            scheduler.pauseAll();
        
    }

    public void resumeAll() throws Exception {
    	logger.warn("resume all tasks");
        if(scheduler!=null)
            scheduler.resumeAll();
    }

    /**
     * 判断任务是否在调度执行
     * @param id
     * @return
     * @throws SchedulerException
     */
    private boolean isScheduling(Integer id) throws SchedulerException{
        JobDetail jobDetail = scheduler.getJobDetail(String.valueOf(id),Scheduler.DEFAULT_GROUP);
        if(jobDetail==null)
            return false;
        return true;
    }
    
    /**
     * 初始所有任务
     * @throws SchedulerException 
     * @throws Exception
     */
    private void initializeTasks() throws SchedulerException{
        List<Task> taskList = taskDao.findAll();
        for(Task task:taskList){
			try {
				String targetObject = task.getTargetObject();
				Object instance=null;
				instance = Class.forName(targetObject).newInstance();
				 if(instance instanceof Job){
		                add(task);
		            }else{
		                logger.error(new StringBuilder()
		                             .append(targetObject)
		                             .append(" load failure,because it's not implements org.quartz.Job").toString());
		            }
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        scheduler.start();
    }
    
    
    /**
     * 获取触发器
     * @param task
     * @return
     * @throws Exception
     */
    private Trigger getTrigger(Task task) throws Exception{
        Trigger trigger=null;
        String e="invalid cron expression";
        String express[] = task.getCronExpression().split(":");
        switch(task.getPeriod()){
            default:
                break;

            case 0: 
                trigger = TriggerUtils.makeSecondlyTrigger(Integer.parseInt(express[0]));
                break;

            case 1: 
                trigger = TriggerUtils.makeMinutelyTrigger(Integer.parseInt(express[0]));
                break;

            case 2: 
                trigger = TriggerUtils.makeHourlyTrigger(Integer.parseInt(express[0]));
                break;

            case 3: 
                if(express.length != 2)
                    throw new Exception(e);
                trigger = TriggerUtils.makeDailyTrigger(Integer.parseInt(express[0]), Integer.parseInt(express[1]));
                break;

            case 4: 
                if(express.length != 3)
                    throw new Exception(e);
                trigger = TriggerUtils.makeWeeklyTrigger(Integer.parseInt(express[0]), Integer.parseInt(express[1]), Integer.parseInt(express[2]));
                break;

            case 5: 
                if(express.length != 3)
                    throw new Exception(e);
                trigger = TriggerUtils.makeMonthlyTrigger(Integer.parseInt(express[0]), Integer.parseInt(express[1]), Integer.parseInt(express[2]));
                break;
        }
        trigger.setName(String.valueOf(task.getId()));
        Date date = task.getBeginDate();
        if(date != null)
            trigger.setStartTime(date);
        date = task.getEndDate();
        if(date != null)
            trigger.setEndTime(date);
        return trigger;
    }
    
    /**
     * 获取任务详细
     * @param task
     * @return
     * @throws ClassNotFoundException
     */
    private JobDetail getJobDetail(Task task) {
        JobDetail jobDetail = null;
        try{
            jobDetail=new JobDetail(String.valueOf(task.getId()), Scheduler.DEFAULT_GROUP, Class.forName(task.getTargetObject()));
            jobDetail.setDescription(new StringBuilder().append(task.getName()).append(" ").append(task.getDescription()).toString());
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return jobDetail;
    }
}
