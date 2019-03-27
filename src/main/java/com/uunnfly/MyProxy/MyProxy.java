package com.uunnfly.MyProxy;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @ProjectName: myProxy
 * @Package: com.uunnfly.MyProxy
 * @ClassName: MyProxy
 * @Description:
 * @Author: uunnfly
 * @CreateDate: 2019/3/26 0026 20:24
 * @UpdateUser: 无
 * @UpdateDate: 2019/3/26 0026 20:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyProxy {
    public static Object newProxyInstance(Class inf, MyInvocationHandler handler) throws Exception{
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("LogProxy")
                .addSuperinterface(inf);

        FieldSpec fieldSpec = FieldSpec.builder(MyInvocationHandler.class, "handler", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);

        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(MyInvocationHandler.class, "handler")
                .addStatement("this.handler = handler")
                .build();
        typeSpecBuilder.addMethod(constructorMethodSpec);

        Method[] methods = inf.getDeclaredMethods();

        for(Method method : methods){

            MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addCode("try {\n")
                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")", Method.class);
            String statement;
            //写死参数为null
            String code = "this.handler.invoke(this, method, null)";
            if(method.getReturnType().getName() == "void")
                statement = "\t" + code;
            else
                statement = "\treturn " + code;

            methodSpecBuilder.addStatement(statement)
                    .addStatement("\t")
                    .addCode("} catch(Exception e) {\n")
                    .addCode("\te.printStackTrace();\n")
                    .addCode("}\n");

            typeSpecBuilder.addMethod(methodSpecBuilder.build());
        }

        String path = "./";
        File file = new File(path);
        JavaFile javaFile = JavaFile.builder("com.uunnfly.proxy",typeSpecBuilder.build()).build();
        javaFile.writeTo(file);

        JavaCompiler.compile(new File(path + "/com/uunnfly/proxy/LogProxy.java"));

        URL[] urls = new URL[]{file.toURI().toURL()};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("com.uunnfly.proxy.LogProxy");

        Constructor constructor = clazz.getConstructor(MyInvocationHandler.class);
        constructor.setAccessible(true);
        Object obj = constructor.newInstance(handler);
        return obj;
    }
}
