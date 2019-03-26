package com.uunnfly.MyProxy;

/**
 * @ProjectName: myProxy
 * @Package: com.uunnfly.MyProxy
 * @ClassName: Agent
 * @Description:
 * @Author: uunnfly
 * @CreateDate: 2019/3/26 0026 19:37
 * @UpdateUser: 无
 * @UpdateDate: 2019/3/26 0026 19:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Agent implements Driver{
    private Driver driver;

    public Agent(Driver driver){
        this.driver = driver;
    }

    public void drive() {
        long start = System.currentTimeMillis();
        System.out.println("before driving...");

        driver.drive();

        long end = System.currentTimeMillis();
        System.out.println("after driving...");
        System.out.println("drive time = " + (end - start));
    }

}
