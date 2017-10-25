// WITH_RUNTIME
// WITH_COROUTINES
import helpers.*
import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*
import kotlin.test.assertEquals

suspend fun suspendHere() = if(coroutineContext != EmptyCoroutineContext) "$coroutineContext != $EmptyCoroutineContext" else "OK"

suspend fun multipleArgs(a: Any, b: Any, c: Any) = if(coroutineContext != EmptyCoroutineContext) "$coroutineContext != $EmptyCoroutineContext" else "OK"

class Controller {
    suspend fun controllerSuspendHere() = if(coroutineContext != EmptyCoroutineContext) "$coroutineContext != $EmptyCoroutineContext" else "OK"

    suspend fun controllerMultipleArgs(a: Any, b: Any, c: Any) = if(coroutineContext != EmptyCoroutineContext) "$coroutineContext != $EmptyCoroutineContext" else "OK"

    fun builder(c: suspend Controller.() -> String): String {
        var fromSuspension: String? = null

        c.startCoroutine(this, object: Continuation<String> {
            override val context: CoroutineContext
                get() =  EmptyCoroutineContext

            override fun resumeWithException(exception: Throwable) {
                fromSuspension = "Exception: " + exception.message!!
            }

            override fun resume(value: String) {
                fromSuspension = value
            }
        })

        return fromSuspension as String
    }
}

class ValuedController {
    val coroutineContext = object : CoroutineContext {
        public override fun <E : CoroutineContext.Element> get(key: CoroutineContext.Key<E>): E? = null
        public override fun <R> fold(initial: R, operation: (R, CoroutineContext.Element) -> R): R = initial
        public override fun plus(context: CoroutineContext): CoroutineContext = context
        public override fun minusKey(key: CoroutineContext.Key<*>): CoroutineContext = this
        public override fun hashCode(): Int = 0
        public override fun toString(): String = "AnotherEmptyCoroutineContext"
    }

    suspend fun controllerSuspendHere() = if(coroutineContext == EmptyCoroutineContext) "$coroutineContext == $EmptyCoroutineContext" else "OK"

    suspend fun controllerSuspendHereIntrinsic() = if(kotlin.coroutines.experimental.intrinsics.coroutineContext != EmptyCoroutineContext)
        "${kotlin.coroutines.experimental.intrinsics.coroutineContext} != $EmptyCoroutineContext"
    else
        "OK"

    suspend fun controllerMultipleArgs(a: Any, b: Any, c: Any) = if(coroutineContext == EmptyCoroutineContext) "$coroutineContext == $EmptyCoroutineContext" else "OK"

    fun builder(c: suspend ValuedController.() -> String): String {
        var fromSuspension: String? = null

        c.startCoroutine(this, object: Continuation<String> {
            override val context: CoroutineContext
                get() =  EmptyCoroutineContext

            override fun resumeWithException(exception: Throwable) {
                fromSuspension = "Exception: " + exception.message!!
            }

            override fun resume(value: String) {
                fromSuspension = value
            }
        })

        return fromSuspension as String
    }
}

fun builder(c: suspend () -> String): String {
    var fromSuspension: String? = null

    val continuation = object: Continuation<String> {
        override val context: CoroutineContext
            get() =  EmptyCoroutineContext

        override fun resumeWithException(exception: Throwable) {
            fromSuspension = "Exception: ${exception}"
        }

        override fun resume(value: String) {
            fromSuspension = value
        }
    }

    c.startCoroutine(continuation)

    return fromSuspension as String
}

fun box(): String {
    var res = builder { suspendHere() }
    if (res != "OK") { return "fail 1 $res"}
    res = builder { multipleArgs(1,1,1) }
    if (res != "OK") { return "fail 2 $res"}
    res = builder { if(coroutineContext != EmptyCoroutineContext) "$coroutineContext != $EmptyCoroutineContext" else "OK" }
    if (res != "OK") { return "fail 3 $res"}
    val c = Controller()
    res = c.builder { controllerSuspendHere() }
    if (res != "OK") { return "fail 4 $res"}
    res = c.builder { controllerMultipleArgs(1,1,1) }
    if (res != "OK") { return "fail 5 $res"}
    res = c.builder { if(coroutineContext != EmptyCoroutineContext) "$coroutineContext != $EmptyCoroutineContext" else "OK" }
    if (res != "OK") { return "fail 6 $res"}
    val v = ValuedController()
    res = v.builder { controllerMultipleArgs(1,1,1) }
    if (res != "OK") { return "fail 8 $res"}
    res = v.builder { if(coroutineContext == EmptyCoroutineContext) "$coroutineContext == $EmptyCoroutineContext" else "OK" }
    if (res != "OK") { return "fail 9 $res"}
    res = v.builder { controllerSuspendHereIntrinsic() }
    if (res != "OK") { return "fail 10 $res"}

    return "OK"
}
