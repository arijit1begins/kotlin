// // !WITH_NEW_INFERENCE

fun trans(n: Int, f: () -> Boolean) = if (f()) n else null

fun foo() {
    var i: Int? = 5    
    if (i != null) {
        fun can(): Boolean {
            i = null
            return true
        }
        <!NI;SMARTCAST_IMPOSSIBLE!><!SMARTCAST_IMPOSSIBLE!>i<!><!>.hashCode()
        trans(<!NI;SMARTCAST_IMPOSSIBLE!><!NI;SMARTCAST_IMPOSSIBLE!><!SMARTCAST_IMPOSSIBLE!>i<!><!><!>, ::can)
        <!NI;SMARTCAST_IMPOSSIBLE!><!SMARTCAST_IMPOSSIBLE!>i<!><!>.hashCode()
    }
}
