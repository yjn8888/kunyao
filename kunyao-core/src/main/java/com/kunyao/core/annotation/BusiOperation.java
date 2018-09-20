package com.kunyao.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BusiOperation {
    
    /**
     * 操作名
     * @category
     * @author: zhangrongbinbj@hanhua.com
     * @since: 2015年7月21日
     * @return
     */
    String value();
}