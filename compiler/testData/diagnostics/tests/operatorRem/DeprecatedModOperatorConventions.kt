// !DIAGNOSTICS: -UNUSED_PARAMETER
// // !WITH_NEW_INFERENCE

class OldAndNew {
    <!NI;DEPRECATED_BINARY_MOD!><!DEPRECATED_BINARY_MOD!>operator<!><!> fun mod(x: Int) {}
    operator fun rem(x: Int) {}
}

class OnlyOld {
    <!NI;DEPRECATED_BINARY_MOD!><!DEPRECATED_BINARY_MOD!>operator<!><!> fun mod(x: Int) {}
}

class OnlyNew {
    operator fun rem(x: Int) {}
}

class Sample

<!NI;DEPRECATED_BINARY_MOD!><!DEPRECATED_BINARY_MOD!>operator<!><!> fun Sample.mod(x: Int) {}
operator fun Sample.rem(x: Int) {}

class IntAndUnit {
    <!NI;DEPRECATED_BINARY_MOD!><!DEPRECATED_BINARY_MOD!>operator<!><!> fun mod(x: Int) = 0
    operator fun rem(x: Int): Int = 0
}

fun test() {
    OldAndNew() % 1
    <!NI;TYPE_MISMATCH!>OnlyOld()<!> <!DEPRECATED_BINARY_MOD_AS_REM!>%<!> 1
    OnlyNew() % 1
    Sample() % 1

    takeInt(IntAndUnit() % 1)
}

fun takeInt(x: Int) {}