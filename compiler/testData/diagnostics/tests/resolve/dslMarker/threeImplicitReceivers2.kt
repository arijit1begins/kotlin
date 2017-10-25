// !DIAGNOSTICS: -UNUSED_PARAMETER
// // !WITH_NEW_INFERENCE
@DslMarker
@Target(AnnotationTarget.TYPE)
annotation class Ann1

@DslMarker
@Target(AnnotationTarget.TYPE)
annotation class Ann2

@DslMarker
@Target(AnnotationTarget.TYPE)
annotation class Ann3

class A {
    val B.y: (C.() -> Unit) get() = null!!
}

class B

class C {
    val D.x: (A.() -> Unit) get() = null!!
}

class D

fun foo(x: (@Ann1 A).() -> Unit) {}
fun bar(x: (@Ann2 B).() -> Unit) {}
fun baz(x: (@Ann3 C).() -> Unit) {}
fun foo1(x: (@Ann1 D).() -> Unit) {}
fun foo2(x: (@Ann2 D).() -> Unit) {}
fun foo3(x: (@Ann3 D).() -> Unit) {}

fun test() {
    foo {
        bar {
            baz {
                y()

                <!NI;DEBUG_INFO_MISSING_UNRESOLVED!><!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!><!UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!FUNCTION_EXPECTED!>x<!><!><!><!>()<!>

                with(D()) {
                    x()
                }

                foo1 {
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>x<!><!>()
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>y<!><!>()

                    with(A()) {
                        x()
                        y()
                    }

                    with(D()) {
                        <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>x<!><!>()
                    }
                    A().x()
                }

                foo2 {
                    x()
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>y<!><!>()
                }

                foo3 {
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>x<!><!>()
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>y<!><!>()
                }
            }
        }
    }

    foo1 {
        foo {
            baz {
                bar {
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>x<!><!>()
                    y()
                }
            }
        }
    }

    foo2 {
        foo {
            baz {
                bar {
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>x<!><!>()
                    y()
                }
            }
        }
    }

    foo3 {
        foo {
            baz {
                bar {
                    <!NI;DSL_SCOPE_VIOLATION!><!DSL_SCOPE_VIOLATION!>x<!><!>()
                    y()
                }
            }
        }
    }
}
