package net.joey.kotlin;

/**
 * Created with java_sdk_examples.
 * User: neigie
 * Date: 2016. 10. 7.
 * Time: 오전 11:00
 * To change this template use File | Settings | File Templates.
 */
public class Greeter {

    private final String greeting;

    Greeter(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
