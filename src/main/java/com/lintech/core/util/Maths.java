package com.lintech.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Maths {
	
	
	public static long getPermutationTotal(TreeMap<String,List<Object>> map){
		long total=1;
		Set<Entry<String, List<Object>>> entrySet = map.entrySet();
    	for(Entry<String, List<Object>> entry:entrySet) {
    		List<Object> value = entry.getValue();
    		if(value==null||value.isEmpty()) {
    			throw  new IllegalArgumentException("map value(List<Object>) could not be null or empty.");
    		}
    		total=total*value.size();
    	}
    	return total;
	}
	
    public static List<TreeMap<String,Object>> getPermutation(TreeMap<String,List<Object>> map){
    	Set<Entry<String, List<Object>>> entrySet = map.entrySet();
    	List<String> keys=new ArrayList<String>();
    	List<List<Object>> values=new ArrayList<List<Object>>();
    	for(Entry<String, List<Object>> entry:entrySet) {
    		keys.add(entry.getKey());
    		List<Object> value = entry.getValue();
    		if(value==null||value.isEmpty()) {
    			throw  new IllegalArgumentException("map value(List<Object>) could not be null or empty.");
    		}
    		values.add(value);
    	}
		List<TreeMap<String,Object>> all=new ArrayList<TreeMap<String,Object>>();
    	permutation(keys,values,0, null, all);
    	return all;
    }
	
	public static void permutation(List<String> keys,List<List<Object>> values,int index,TreeMap<String,Object> result,List<TreeMap<String,Object>> all){
		if(index==values.size()) {
			//System.out.println(result);
			TreeMap<String,Object> clone = (TreeMap<String,Object>)result.clone();
			all.add(clone);
			return;
		}
        List<Object> list = values.get(index);
        String key = keys.get(index);
        for(int i=0;i<list.size();i++) {
    	  if(index == 0){
              result = new TreeMap<String,Object>();
          }
    	  result.put(key, list.get(i));
    	  permutation(keys,values,index+1,result,all);
    	  result.remove(key);
        }
    }
    

}
