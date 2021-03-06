package net.joey.kotlin;

import lombok.extern.slf4j.Slf4j;
import net.joey.KotlinGreetingJoiner;

/**
 * JVM 위에서 돌아가는 bytecode로 컴파일 되는 Kotlin언어로 작성된 클래스 메서드를 사용하는 예제입니다
 *
 * 1. new KotlinClass() 로 직접 호출
 * 2. Reflection을 이용하여 호출
 *
 * Created with java_sdk_examples.
 * User: neigie
 * Date: 2016. 10. 7.
 * Time: 오전 11:04
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class HelloWorld {

    public static void main(String[] args) {
        usingNewInstance();
        usingReflection();
    }

    private static void usingReflection() {
        ReflectionMain reflectionMain = new ReflectionMain();

        String resultRef = reflectionMain.reflect(KotlinGreetingJoiner.class, new Greeter("Hi with reflect!"), "getJoinedGreeting");
        log.info("reflect result: {}", resultRef);

        String resultRefWithParam = reflectionMain.reflect(KotlinGreetingJoiner.class, new Greeter("Hi with reflect!"), "getJoinedGreeting", "철수", "영희");
        log.info("reflect result: {}", resultRefWithParam);

        String resultMH = reflectionMain.methodHandle(KotlinGreetingJoiner.class, new Greeter("Hi with methodHandle!"), "getJoinedGreeting");
        log.info("method handle result: {}", resultMH);

        String resultMHWithParam = reflectionMain.methodHandle(KotlinGreetingJoiner.class, new Greeter("Hi with methodHandle!"), "getJoinedGreeting", "철수", "영희");
        log.info("method handle result with parameters: {}", resultMHWithParam);
    }

    private static void usingNewInstance() {
        // created using kotlin
        final KotlinGreetingJoiner example = new KotlinGreetingJoiner(new Greeter("Hi"));

        example.addName("Harry");
        example.addName("Sally");
        example.addName(null);
        example.addName("Joey");

        log.info("new instance result: {}", example.getJoinedGreeting());

        log.info("new instance result with param: {}", example.getJoinedGreeting(new String[]{"해리", "샐리"}));
    }
}
