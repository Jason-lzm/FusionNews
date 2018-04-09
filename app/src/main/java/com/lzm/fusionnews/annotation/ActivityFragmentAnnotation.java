package com.lzm.fusionnews.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: lzm
 * CreateDate: 2018-1-17.
 * Description: Activity、Fragment初始化的用到的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActivityFragmentAnnotation {

    /**
     * 布局资源id
     * @return
     */
    int contentViewId() default -1;

    /**
     * 菜单id
     * @return
     */
    int menuId() default -1;

    /**
     * 是否开启侧滑
     * @return
     */
    boolean enableSlidr() default  false;

    /**
     * 是否有导航栏
     * @return
     */
    boolean hasNavigationView() default false;

    /**
     * toolbar的标题id
     *
     * @return
     */
    int toolbarTitle() default -1;

    /**
     * toolbar的菜单按钮id
     *
     * @return
     */
    int toolbarIndicator() default -1;

    /**
     * toolbar菜单默认选中项
     *
     * @return
     */
    int menuDefaultCheckedItem() default -1;
}
