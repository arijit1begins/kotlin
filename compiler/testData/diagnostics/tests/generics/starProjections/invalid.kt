// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_PARAMETER
// // !WITH_NEW_INFERENCE
// FILE: main.kt
class Inv<T>(val x: T)

class A<T : Inv<in T>> {
    fun foo(): T = null!!
}

class Inv2<<!NI;FINITE_BOUNDS_VIOLATION!><!FINITE_BOUNDS_VIOLATION!>T : Inv2<in T><!><!>>(val x: T)

fun main(a: A<*>, j: JavaClass<*>, i2: Inv2<*>) {
    // Probably it's too restrictive to suppose star projection type here as Any?,
    // but looks like we can refine it later
    a.foo() checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!>_<!><!><Any?>() }
    j.foo() checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!>_<!><!><Any?>() }
    i2.x checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!>_<!><!><Any?>() }

    j.<!MEMBER_PROJECTED_OUT!>bar<!>(<!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!><!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!><!>, <!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!><!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!>2<!><!>, <!NI;TYPE_MISMATCH!><!NI;TYPE_MISMATCH!>Any()<!><!>)
}

// FILE: JavaClass.java
public class JavaClass<T extends JavaClass<? super T>> {
    public void bar(T... x) {}
    public T foo() {}
}
