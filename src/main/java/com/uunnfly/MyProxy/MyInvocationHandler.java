package com.uunnfly.MyProxy;

import java.lang.reflect.Method;

/**
 * @ProjectName: myProxy
 * @Package: com.uunnfly.MyProxy
 * @ClassName: MyInvocationHandler
 * @Description:
 * @Author: uunnfly
 * @CreateDate: 2019/3/26 0026 20:23
 * @UpdateUser: 无
 * @UpdateDate: 2019/3/26 0026 20:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args);
}
