// !CHECK_TYPE
// // !WITH_NEW_INFERENCE
object Outer {
    private var x
        get() = object : CharSequence {
            override val length: Int
                get() = 0

            override fun get(index: Int): Char {
                checkSubtype<CharSequence>(<!NI;TYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM!><!NI;DEBUG_INFO_MISSING_UNRESOLVED!><!TYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM!>x<!><!><!>)
                return ' '
            }

            override fun subSequence(startIndex: Int, endIndex: Int) = ""

            fun bar() {
            }
        }
        set(q) {
            checkSubtype<CharSequence>(x)
            y = q
            x = q
        }

    private var y = <!NI;DEBUG_INFO_LEAKING_THIS!><!DEBUG_INFO_LEAKING_THIS!>x<!><!>

    fun foo() {
        x = y

        checkSubtype<CharSequence>(x)
        checkSubtype<CharSequence>(y)

        x.bar()
        y.bar()
    }
}
