package com.outstandingteam.palette.util;

import java.lang.annotation.*;

/**
 * 使用spring 的 aop 技术切到自定义注解上
 * 自定义注解类
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface PaletteLog {

    String value() default "";
}
