package net.joey.kotlin;

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with java_sdk_examples.
 * User: neigie
 * Date: 2016. 10. 7.
 * Time: 오후 2:34
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ReflectionMain {

    public String reflect(Class<?> clazz, Object constParam, String methodName, String... methodParams) {
        String result = null;
        try {
            Constructor constructor = clazz.getConstructor(constParam.getClass());
            Object obj = constructor.newInstance(constParam);
            Method method;
            if (methodParams.length == 0) {
                method = clazz.getMethod(methodName);
                result = (String) method.invoke(obj);
            } else {
                method = clazz.getMethod(methodName, methodParams.getClass());
                // Method.invoke()의 parameters 는 Object[] 여야 하므로 casting.
                result = (String) method.invoke(obj, new Object[]{methodParams});
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String methodHandle(Class<?> clazz, Object constParam, String methodName, String... methodParams) {
        String result = null;
        try {
            MethodHandles.Lookup methodHandle = MethodHandles.lookup();
            Constructor constructor = clazz.getConstructor(constParam.getClass());
            Object obj = constructor.newInstance(constParam);

            MethodHandle method;
            if (methodParams.length == 0) {
                method = methodHandle.findVirtual(clazz, methodName, MethodType.methodType(String.class));
                result = (String) method.invoke(obj);
            } else {
                method = methodHandle.findVirtual(clazz, methodName, MethodType.methodType(String.class, methodParams.getClass()));
                result = (String) method.invoke(obj, methodParams);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Error Occured in [{}] : cause: {}, message: {}", e.getClass().getName(), e.getCause(), e.getMessage());
//            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
