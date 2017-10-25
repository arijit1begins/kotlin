// !LANGUAGE: +ArrayLiteralsInAnnotations
// // !WITH_NEW_INFERENCE

annotation class Ann1(vararg val a: String = [])
annotation class Ann2(vararg val a: Int = [1, 2])
annotation class Ann3(vararg val a: Float = [1f])
annotation class Ann4(vararg val a: String = ["/"])

annotation class Ann5(vararg val a: Ann4 = [])
annotation class Ann6(vararg val a: Ann4 = [Ann4(*["a", "b"])])

annotation class Ann7(vararg val a: Long = [1L, <!NI;NULL_FOR_NONNULL_TYPE!><!NI;NULL_FOR_NONNULL_TYPE!><!NULL_FOR_NONNULL_TYPE!>null<!><!><!>, <!NI;TYPE_MISMATCH!><!NI;TYPE_MISMATCH!><!TYPE_MISMATCH!>""<!><!><!>])

@Ann1(*[])
fun test1_0() {}

@Ann1(*["a", "b"])
fun test1_1() {}

@Ann1(*<!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>["a", <!NI;CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>, <!NI;NULL_FOR_NONNULL_TYPE!><!ANNOTATION_PARAMETER_MUST_BE_CONST!>null<!><!>]<!>)
fun test1_2() {}

@Ann2(*[])
fun test2() {}

@Ann3(a = *<!NI;ASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_ANNOTATION!><!ASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_ANNOTATION!>[0f, <!NI;DIVISION_BY_ZERO!><!DIVISION_BY_ZERO!>1 / 0f<!><!>]<!><!>)
fun test3() {}

@Ann5(Ann4(*["/"]))
fun test5() {}

@Ann6(*[])
fun test6() {}

annotation class AnnArray(val a: Array<String>)

@AnnArray(<!NON_VARARG_SPREAD!>*<!>["/"])
fun testArray() {}

@Ann1(<!NI;TYPE_MISMATCH!><!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!><!TYPE_MISMATCH!>[""]<!><!><!>)
fun testVararg() {}