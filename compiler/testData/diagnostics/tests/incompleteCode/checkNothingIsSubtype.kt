// !DIAGNOSTICS: -UNUSED_EXPRESSION
// // !WITH_NEW_INFERENCE
package d

interface A<T>

fun <T> infer(<!NI;UNUSED_PARAMETER!><!UNUSED_PARAMETER!>a<!><!>: A<T>) : T {<!NI;NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!><!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!><!>

fun test(nothing: Nothing?) {
    <!NI;UNREACHABLE_CODE!>val <!NI;UNUSED_VARIABLE!><!UNUSED_VARIABLE!>i<!><!> =<!> <!TYPE_INFERENCE_INCORPORATION_ERROR!>infer<!>(<!NI;TYPE_MISMATCH!><!NI;TYPE_MISMATCH!><!NI;DEBUG_INFO_CONSTANT!><!TYPE_MISMATCH!><!DEBUG_INFO_CONSTANT!>nothing<!><!><!><!><!>)
}

fun sum(<!NI;UNUSED_PARAMETER!><!UNUSED_PARAMETER!>a<!><!> : IntArray) : Int {
for (n
<!NI;SYNTAX!><!SYNTAX!>return<!><!><!NI;SYNTAX!><!><!SYNTAX!><!> "?"
<!NI;NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!><!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!><!>