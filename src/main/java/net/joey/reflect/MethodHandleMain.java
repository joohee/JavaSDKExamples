package net.joey.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.math.BigDecimal;

/**
 * MethodHandle에서 제공하는 메서드 사용 예제입니다.
 *
 * Created with Java8ConcurrencyFeatures.
 * User: neigie
 * Date: 2016. 10. 5.
 * Time: 오후 7:34
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class MethodHandleMain {

    public static void main(String[] args) {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(BigDecimal.class, int.class);
        try {
            MethodHandle power = lookup.findVirtual(BigDecimal.class, "pow", mt);

            BigDecimal value = (BigDecimal) power.invoke(new BigDecimal(5), 2);
            log.info("result of pow: {}", value.intValue());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
