package com.outstandingteam.palette.config;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.entity.Log;
import com.outstandingteam.palette.entity.Token;
import com.outstandingteam.palette.mapper.LogMapper;
import com.outstandingteam.palette.mapper.TokenMapper;
import com.outstandingteam.palette.util.IPUtils;
import com.outstandingteam.palette.util.PaletteLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    LogMapper logMapper;
    @Autowired
    TokenMapper tokenMapper;
    private long startTime;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.outstandingteam.palette.util.PaletteLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore() {
        //设置方法开始的时间
        this.startTime = System.currentTimeMillis();
    }

    //切面 配置通知
    @AfterReturning(value = "logPointCut()", returning = "returnValue")
    public void saveSysLog(JoinPoint joinPoint, Object returnValue) throws JSONException {
        //保存日志
        Log log = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        PaletteLog paletteLog = method.getAnnotation(PaletteLog.class);
        if (paletteLog != null) {
            String value = paletteLog.value();
            log.setOperation(value);        //保存获取的操作
        }


        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        log.setMethod(className + "." + methodName);

        //请求的参数类型
        String[] parameterNames = signature.getParameterNames();
        //获取请求的参数值
        Object[] args = joinPoint.getArgs();
        JSONObject jsonObject = new JSONObject();
        long token = 0;
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i] == null || args[i] == null) continue;
            if(parameterNames[i].equals("token")){
                token = Long.parseLong(args[i].toString());
            }
            jsonObject.put(parameterNames[i], args[i].toString());
        }
        //将参数所在的数组转换成json并保存
        log.setParams(jsonObject.toString());

        //获取用户ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.setIp(IPUtils.getIpAddr(request));

        //获取用户名
        Token token1 = tokenMapper.selectById(token);
        if (token1 != null && new Date().after(token1.getDeadlineTime())) {
            log.setUserId(token1.getUserId());
        }

        // 获取并设置方法执行时间
        log.setTime(System.currentTimeMillis() - startTime);

        // 设置返回值
        if (returnValue != null) {
            log.setResultCode(((Result) returnValue).getCode());
        }

        //调用service保存SysLog实体类到数据库
        logMapper.insert(log);
    }

}