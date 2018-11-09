package com.Spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengxt
 */
public class MyAnnotation {
    public static Map<String, Object> ioc = new HashMap<>(10);

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        MyAnnotation.instance();

        System.out.println(ioc);

        Test1 test1 = (Test1) MyAnnotation.ioc.get(MyAnnotation.getKey(Test1.class));

        test1.sayHi();

    }

    public static void instance() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        MyAnnotation myAnnotation = new MyAnnotation();
        // 获取所有内部类
        Class[] classes = MyAnnotation.class.getDeclaredClasses();
        boolean isDependencies = false;
        for (Class c : classes) {
            //初始化注解有@Componet的类
            if (!c.isAnnotation()) {
                Compoment compoment = (Compoment) c.getAnnotation(Compoment.class);
                if (compoment != null) {
                    String key = compoment.name();
                    if ("".equals(key)) {
                        key = getKey(c);
                    }
                    Object o;
                    if (MyAnnotation.ioc.get(key) == null) {
                        Constructor constructor = c.getConstructor(MyAnnotation.class);
                        o = constructor.newInstance(myAnnotation);
                        Field[] fields = c.getDeclaredFields();
                        for (Field f : fields) {
                            Autowired autowired = f.getAnnotation(Autowired.class);
                            //如果有注解@Autowired，则进行属性注入
                            if (autowired != null) {
                                String fieldKey = f.getType().getName() + "##" + f.getName();
                                Object object = MyAnnotation.ioc.get(fieldKey);
                                if (object == null) {
                                    //如果容器中没有初始化的类，跳过。
                                    isDependencies = true;
                                    break;
                                } else {
                                    //对属性进行赋值
                                    f.set(o, object);
                                }
                            }
                        }
                        if (isDependencies) {
                            continue;
                        }
                        Method[] methods = c.getDeclaredMethods();
                        for (Method method : methods) {
                            Autowired autowired = method.getAnnotation(Autowired.class);
                            //如果有注解@Autowired，则进行方法注入
                            if (autowired != null) {
                                Parameter[] parameters = method.getParameters();
                                if (parameters.length > 1) {
                                    System.out.println("autoWired error");
                                    break;
                                }
                                String pKey = MyAnnotation.getKey(parameters[0].getType());
                                System.out.println(pKey);
                                Object temp = MyAnnotation.ioc.get(pKey);
                                if (temp == null) {
                                    //如果容器中没有初始化的类，跳过。
                                    isDependencies = true;
                                    break;
                                } else {
                                    method.invoke(o, temp);
                                }
                            }
                        }
                        MyAnnotation.ioc.put(key, o);
                    }
                }
            }
        }

        if (isDependencies) {
            MyAnnotation.instance();
        }
    }

    public static String getKey(Class c) {
        String simpleName = c.getSimpleName();
        return c.getName() + "##" + simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    private @interface Compoment {
        String name() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    private @interface Autowired {
        String name() default "";
    }

    @Compoment
    public class Test1 {

        @Autowired
        Test2 test2;

        Test3 test3;

        @Autowired
        public void setTest3(Test3 test3) {
            this.test3 = test3;
        }

        public void sayHi() {
            test2.sayHi();
            test3.sayHi();
        }
    }

    @Compoment
    public class Test2 {
        public void sayHi() {
            System.out.println("test2 say hi");
        }
    }

    @Compoment
    public class Test3 {
        public void sayHi() {
            System.out.println("test3 say hi");
        }
    }

}
