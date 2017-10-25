/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.js.translate.intrinsic.functions.factories

import org.jetbrains.kotlin.backend.common.isBuiltInCoroutineContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.js.backend.ast.JsExpression
import org.jetbrains.kotlin.js.backend.ast.JsNameRef
import org.jetbrains.kotlin.js.backend.ast.metadata.inlineStrategy
import org.jetbrains.kotlin.js.translate.callTranslator.CallInfo
import org.jetbrains.kotlin.js.translate.context.TranslationContext
import org.jetbrains.kotlin.js.translate.intrinsic.functions.basic.FunctionIntrinsic
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.inline.InlineStrategy

object CoroutineContextFIF : FunctionIntrinsicFactory {
    override fun getIntrinsic(descriptor: FunctionDescriptor): FunctionIntrinsic? {
        if (!descriptor.isBuiltInCoroutineContext()) return null
        return Intrinsic
    }

    object Intrinsic : FunctionIntrinsic() {
        override fun apply(callInfo: CallInfo, arguments: List<JsExpression>, context: TranslationContext): JsExpression {
            // These shall be already checked in front-end
            val continuation = context.continuationParameterDescriptor ?: throw IllegalStateException("coroutineContext called from outside of coroutine")
            val continuationDescriptor = continuation.type.constructor.declarationDescriptor as? ClassDescriptor ?: throw IllegalStateException("Continuation is not a class")
            val contContexts = continuationDescriptor.unsubstitutedMemberScope.getContributedVariables(
                    Name.identifier("context"), NoLookupLocation.FROM_BUILTINS
            )
            if (contContexts.isEmpty()) throw IllegalStateException("Continuation does not have context property")
            if (contContexts.size > 1) throw IllegalStateException("Multiple contexts in continuation")

            val res = JsNameRef(context.getNameForDescriptor(contContexts.first()).ident, context.getNameForDescriptor(continuation).makeRef())
            res.inlineStrategy = InlineStrategy.NOT_INLINE
            return res
        }
    }
}
