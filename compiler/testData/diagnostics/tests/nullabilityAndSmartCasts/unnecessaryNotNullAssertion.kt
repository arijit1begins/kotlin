// !DIAGNOSTICS: -UNUSED_PARAMETER, -SENSELESS_COMPARISON, -DEBUG_INFO_SMARTCAST
// // !WITH_NEW_INFERENCE

fun takeNotNull(s: String) {}
fun <T> notNull(): T = TODO()
fun <T> nullable(): T? = null
fun <T> dependOn(x: T) = x

fun test() {
    takeNotNull(notNull()<!NI;UNNECESSARY_NOT_NULL_ASSERTION!><!UNNECESSARY_NOT_NULL_ASSERTION!>!!<!><!>)
    takeNotNull(nullable()!!)

    var x: String? = null
    takeNotNull(dependOn(x)<!NI;UNNECESSARY_NOT_NULL_ASSERTION!>!!<!>)
    takeNotNull(dependOn(dependOn(x))<!NI;UNNECESSARY_NOT_NULL_ASSERTION!>!!<!>)
    takeNotNull(dependOn(dependOn(x)<!NI;UNNECESSARY_NOT_NULL_ASSERTION!>!!<!>))
    takeNotNull(dependOn(dependOn(x!!)))

    if (x != null) {
        takeNotNull(dependOn(x)<!NI;UNNECESSARY_NOT_NULL_ASSERTION!><!UNNECESSARY_NOT_NULL_ASSERTION!>!!<!><!>)
        takeNotNull(dependOn(dependOn(x))<!NI;UNNECESSARY_NOT_NULL_ASSERTION!><!UNNECESSARY_NOT_NULL_ASSERTION!>!!<!><!>)
        takeNotNull(dependOn(dependOn(x)<!NI;UNNECESSARY_NOT_NULL_ASSERTION!><!UNNECESSARY_NOT_NULL_ASSERTION!>!!<!><!>))
    }
}