package com.uunnfly.MyProxy;

import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: myProxy
 * @Package: com.uunnfly.MyProxy
 * @ClassName: JavaCompiler
 * @Description:
 * @Author: uunnfly
 * @CreateDate: 2019/3/26 0026 20:29
 * @UpdateUser: 无
 * @UpdateDate: 2019/3/26 0026 20:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JavaCompiler {
    public static void compile(File javaFile)throws IOException {
        javax.tools.JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null,null,null);
        Iterable iterable = fileManager.getJavaFileObjects(javaFile);
        javax.tools.JavaCompiler.CompilationTask task = javaCompiler.getTask(null,fileManager,null,null,null,iterable);
        task.call();
        fileManager.close();
    }
}
