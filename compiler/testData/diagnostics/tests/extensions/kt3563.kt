// // !WITH_NEW_INFERENCE

// KT-3563 Compiler requiring java.io.File, and it's unclear why

package bar

import java.io.File

class Customer(<!NI;UNUSED_PARAMETER!><!UNUSED_PARAMETER!>name1<!><!>: String)

fun foo(f: File, c: Customer) {
    f.name1

    c.<!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>name1<!><!><!> // name1 should be unresolved here
}

val File.name1: String
    get() = getName()