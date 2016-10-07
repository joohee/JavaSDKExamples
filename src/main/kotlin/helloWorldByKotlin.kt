package net.joey

import net.joey.kotlin.Greeter
import java.util.*

/**
 * Created with java_sdk_examples.
 * User: neigie
 * Date: 2016. 10. 7.
 * Time: 오전 11:00
 * To change this template use File | Settings | File Templates.
 */
class KotlinGreetingJoiner(val greeter: Greeter) {

    var names = ArrayList<String?>()

    fun addName(name: String?) {
        names.add(name)
    }

    fun getJoinedGreeting(): String {
        return "${greeter.greeting} ${names.filterNotNull().joinToString(separator = " and ")}"
    }

    fun getJoinedGreeting(names: Array<String?>): String {
        return "${names.filterNotNull().joinToString(separator = " 와 ")}"
    }
}
