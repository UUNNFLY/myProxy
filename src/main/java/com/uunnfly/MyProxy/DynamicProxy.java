package com.uunnfly.MyProxy;

import java.lang.reflect.Method;

/**
 * @ProjectName: myProxy
 * @Package: com.uunnfly.MyProxy
 * @ClassName: DynamicProxy
 * @Description:
 * @Author: uunnfly
 * @CreateDate: 2019/3/26 0026 20:47
 * @UpdateUser: 无
 * @UpdateDate: 2019/3/26 0026 20:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DynamicProxy implements MyInvocationHandler {
    //obj为委托类对象;
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        long start = System.currentTimeMillis();
        System.out.println("before driving...");
        Object result = null;
        try {
            result = method.invoke(obj, args);

        }catch(Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("after driving...");
        System.out.println("drive time = " + (end - start));
        return result;

    }
}
