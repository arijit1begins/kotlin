// !CHECK_TYPE
// // !WITH_NEW_INFERENCE
interface A<T>

interface In<in T>

interface Out<out T>

fun <T: Any> doT(t: T?): T = throw Exception("$t")
fun <T: Any> doOut(o: Out<T?>): T { throw Exception("$o") }
fun <T: Any> doIn(i: In<T?>) { throw Exception("$i") }
fun <T: Any> doA(i: A<T?>) { throw Exception("$i") }

fun test(out: Out<Int>, i: In<Int>, inv: A<Int>) {
    // T? >: Int => T = Int
    doT(1)
    val r = doOut(out)
    r checkType { _<Int>() }

    // T? <: Int => error
    <!TYPE_INFERENCE_INCORPORATION_ERROR!>doIn<!>(<!NI;TYPE_MISMATCH!><!NI;TYPE_MISMATCH!><!TYPE_MISMATCH!>i<!><!><!>)

    // T? >: Int => error
    <!TYPE_INFERENCE_INCORPORATION_ERROR!>doA<!>(<!NI;TYPE_MISMATCH!><!NI;TYPE_MISMATCH!><!TYPE_MISMATCH!>inv<!><!><!>)
}
