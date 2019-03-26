package com.uunnfly.MyProxy;


/**
 * @ProjectName: myProxy
 * @Package: com.uunnfly.MyProxy
 * @ClassName: Main
 * @Description:
 * @Author: uunnfly
 * @CreateDate: 2019/3/26 0026 19:24
 * @UpdateUser: 无
 * @UpdateDate: 2019/3/26 0026 19:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args)throws Exception{
//        Driver driver = new Agent(new Veteran());
//        driver.drive();

        Driver driver = (Driver)MyProxy.newProxyInstance(Driver.class,new DynamicProxy(new Veteran()));
        driver.drive();
    }
}
