package com.lintech.core;
//package com.lintech.core;
//
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.NativeWebRequest;
//
//import com.lintech.entity.User;
//
//@ControllerAdvice
//public class AdminControllerAdvice {
//
//    @ModelAttribute
//    public User newUser() {
//        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
//        return new User();
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
//    }
//
//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String processUnauthenticatedException(NativeWebRequest request, UnauthenticatedException e) {
//        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");
//        return "viewName"; //返回一个逻辑视图名
//    }
//}