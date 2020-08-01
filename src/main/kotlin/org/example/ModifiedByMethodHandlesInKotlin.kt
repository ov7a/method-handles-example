package org.example

import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

class ModifiedByMethodHandlesInKotlin : Parent() {
    public override fun exampleMethod(): String {
        val child = Child()
        val firstPart = "modified return "
        val secondPart = callSuperExampleMethod(child)
        val thirdPart = callAnotherPrivateMethod(child)
        return firstPart + secondPart + thirdPart
    }

    private val methodLookup = MethodHandles.privateLookupIn(Child::class.java, MethodHandles.lookup())

    private fun callSuperExampleMethod(child: Child): String {
        val lookup = methodLookup
        val baseHandle = lookup.findSpecial(Parent::class.java, "exampleMethod", MethodType.methodType(String::class.java), Child::class.java)
        return baseHandle.invoke(child) as String
    }

    private fun callAnotherPrivateMethod(child: Child): String {
        val lookup = methodLookup
        val baseHandle = lookup.findVirtual(Child::class.java, "anotherPrivateMethod", MethodType.methodType(String::class.java))
        return baseHandle.invoke(child) as String
    }
}