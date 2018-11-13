/*
 * Copyright 2018-present by the authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at following link.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ar.com.agomez.choco

import org.chocosolver.solver.expression.discrete.relational.ReExpression

/**
 * Extension for [ReExpression.neg] for invoking it as the unary operator `-`.
 *
 * @receiver The expression to negate.
 * @return The expression `-x`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ReExpression.unaryMinus(): ReExpression = not()

/**
 * Extension for [ReExpression.and] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs and rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.and(e: ReExpression): ReExpression = and(e)

/**
 * Extension for [ReExpression.and] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs and rhs_1 and rhs_2 ... and rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.and(e: Array<out ReExpression>): ReExpression = and(*e)

/**
 * Extension for [ReExpression.and] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs and rhs_1 and rhs_2 ... and rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.3
 */
infix fun ReExpression.and(e: Iterable<ReExpression>): ReExpression = and(e.asTypedArray())

/**
 * Extension for [ReExpression.or] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs or rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.or(e: ReExpression): ReExpression = or(e)

/**
 * Extension for [ReExpression.or] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs or rhs_1 or rhs_2 ... or rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.or(e: Array<out ReExpression>): ReExpression = or(*e)

/**
 * Extension for [ReExpression.or] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs or rhs_1 or rhs_2 ... or rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.3
 */
infix fun ReExpression.or(e: Iterable<ReExpression>): ReExpression = or(e.asTypedArray())

/**
 * Extension for [ReExpression.xor] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs xor rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.xor(e: ReExpression): ReExpression = xor(e)

/**
 * Extension for [ReExpression.xor] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs xor rhs_1 xor rhs_2 ... xor rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.xor(e: Array<out ReExpression>): ReExpression = xor(*e)

/**
 * Extension for [ReExpression.xor] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs xor rhs_1 xor rhs_2 ... xor rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.3
 */
infix fun ReExpression.xor(e: Iterable<ReExpression>): ReExpression = xor(e.asTypedArray())

/**
 * Extension for [ReExpression.imp] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs imp rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.imp(e: ReExpression): ReExpression = imp(e)

/**
 * Extension for [ReExpression.iff] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs iff rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.iff(e: ReExpression): ReExpression = iff(e)

/**
 * Extension for [ReExpression.iff] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs iff rhs_1 iff rhs_2 ... iff rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ReExpression.iff(e: Array<out ReExpression>): ReExpression = iff(*e)

/**
 * Extension for [ReExpression.iff] for invoking it in infix mode.
 *
 * @receiver The lhs of the expression.
 * @param e The rhs of the comparison.
 * @return The expression `lhs iff rhs_1 iff rhs_2 ... iff rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.3
 */
infix fun ReExpression.iff(e: Iterable<ReExpression>): ReExpression = iff(e.asTypedArray())
