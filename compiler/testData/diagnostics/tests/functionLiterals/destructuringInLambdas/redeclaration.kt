// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_ANONYMOUS_PARAMETER
// // !WITH_NEW_INFERENCE
data class A(val x: Int, val y: String)
data class B(val u: Double, val w: Short)

fun foo(block: (A, B) -> Unit) { }

fun bar() {
    foo { (<!NI;REDECLARATION!><!NI;UNUSED_DESTRUCTURED_PARAMETER_ENTRY!><!REDECLARATION!><!UNUSED_DESTRUCTURED_PARAMETER_ENTRY!>a<!><!><!><!>, <!NI;REDECLARATION!><!REDECLARATION!>a<!><!>), b ->
        a checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><Int>() }
        b checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><String>() }
    }

    foo { (<!NI;REDECLARATION!><!NI;UNUSED_DESTRUCTURED_PARAMETER_ENTRY!><!REDECLARATION!><!UNUSED_DESTRUCTURED_PARAMETER_ENTRY!>a<!><!><!><!>, b), <!NI;REDECLARATION!><!REDECLARATION!>a<!><!> ->
        a checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><Int>() }
        b checkType { _<String>() }
    }

    foo { <!NI;REDECLARATION!><!REDECLARATION!>a<!><!>, (<!NI;REDECLARATION!><!REDECLARATION!>a<!><!>, b) ->
        a checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><Int>() }
        b checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><String>() }
    }

    foo { (a, <!NI;REDECLARATION!><!NI;UNUSED_DESTRUCTURED_PARAMETER_ENTRY!><!REDECLARATION!><!UNUSED_DESTRUCTURED_PARAMETER_ENTRY!>b<!><!><!><!>), (c, <!NI;REDECLARATION!><!REDECLARATION!>b<!><!>) ->
        a checkType { _<Int>() }
        b checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><String>() }
        c checkType { <!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!TYPE_MISMATCH!>_<!><!><!><B>() }
    }
}
