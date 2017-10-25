// !LANGUAGE: +ArrayLiteralsInAnnotations
// // !WITH_NEW_INFERENCE

import kotlin.reflect.KClass

annotation class Foo(val a: Array<KClass<*>> = [])

class Gen<T>

annotation class Bar(val a: Array<KClass<*>> = [Int::class, Array<Int>::class, Gen::class])

@Foo([])
fun test1() {}

@Foo([Int::class, String::class])
fun test2() {}

@Foo([<!NI;ARRAY_CLASS_LITERAL_REQUIRES_ARGUMENT!><!ARRAY_CLASS_LITERAL_REQUIRES_ARGUMENT!>Array::class<!><!>])
fun test3() {}

@Foo([<!NI;CLASS_LITERAL_LHS_NOT_A_CLASS!><!CLASS_LITERAL_LHS_NOT_A_CLASS!>Gen<Int>::class<!><!>])
fun test4() {}

@Foo(<!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>[<!NI;TYPE_MISMATCH!>""<!>]<!>)
fun test5() {}

@Foo(<!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>[Int::class, <!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>]<!>)
fun test6() {}

@Bar
fun test7() {}