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

import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression
import org.chocosolver.solver.expression.discrete.relational.ReExpression

/**
 * Extension for [ArExpression.neg] for invoking it as the unary operator `-`.
 *
 * @receiver The expression to negate.
 * @return The expression `-x`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.unaryMinus(): ArExpression = neg()

/**
 * Extension for [ArExpression.add] for invoking it as the operator `+`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs + rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.plus(c: Int): ArExpression = add(c)

/**
 * Extension for [ArExpression.add] for invoking it as the operator `+`.
 *
 * @receiver The lhs of the operation.
 * @param e The rhs of the operation.
 * @return The expression `lhs + rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.plus(e: ArExpression): ArExpression = add(e)

/**
 * Extension for [ArExpression.add] for invoking it as the operator `+`.
 *
 * @receiver The lhs of the operation.
 * @param o The rhs of the operation.
 * @return The expression `lhs + rhs_1 + rhs_2 ... + rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.plus(o: Array<out ArExpression>): ArExpression = add(*o)

/**
 * Extension for [ArExpression.sub] for invoking it as the operator `-`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs - rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.minus(c: Int): ArExpression = sub(c)

/**
 * Extension for [ArExpression.sub] for invoking it as the operator `-`.
 *
 * @receiver The lhs of the operation.
 * @param o The rhs of the operation.
 * @return The expression `lhs - rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.minus(o: ArExpression): ArExpression = sub(o)

/**
 * Extension for [ArExpression.mul] for invoking it as the operator `*`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs * rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.times(c: Int): ArExpression = mul(c)

/**
 * Extension for [ArExpression.mul] for invoking it as the operator `*`.
 *
 * @receiver The lhs of the operation.
 * @param o The rhs of the operation.
 * @return The expression `lhs * rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.times(o: ArExpression): ArExpression = mul(o)

/**
 * Extension for [ArExpression.mul] for invoking it as the operator `*`.
 *
 * @receiver The lhs of the operation.
 * @param o The rhs of the operation.
 * @return The expression `lhs * rhs_1 * rhs_2 ... * rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.times(o: Array<out ArExpression>): ArExpression = mul(*o)

/**
 * Extension for [ArExpression.mod] for invoking it as the operator `%`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs % rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.rem(c: Int): ArExpression = mod(c)

/**
 * Extension for [ArExpression.mod] for invoking it as the operator `%`.
 *
 * @receiver The lhs of the operation.
 * @param o The rhs of the operation.
 * @return The expression `lhs % rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun ArExpression.rem(o: ArExpression): ArExpression = mod(o)

/**
 * Extension for [ArExpression.pow] for invoking it in infix mode.
 *
 * @receiver The base.
 * @param c The exponent.
 * @return The expression `base ** exponent`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.pow(c: Int): ArExpression = pow(c)

/**
 * Extension for [ArExpression.pow] for invoking it in infix mode.
 *
 * @receiver The base.
 * @param e The exponent.
 * @return The expression `base ** exponent`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.pow(e: ArExpression): ArExpression = pow(e)

/**
 * Extension for [ArExpression.eq] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs == rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.eq(c: Int): ReExpression = eq(c)

/**
 * Extension for [ArExpression.eq] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs == rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.eq(e: ArExpression): ReExpression = eq(e)

/**
 * Extension for [ArExpression.eq] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs == rhs_1 == rhs_2 ... == rhs_n`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.eq(e: Array<out ArExpression>): ReExpression = eq(*e)

/**
 * Extension for [ArExpression.ne] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs != rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.ne(c: Int): ReExpression = ne(c)

/**
 * Extension for [ArExpression.ne] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs != rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.ne(e: ArExpression): ReExpression = ne(e)

/**
 * Extension for [ArExpression.lt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs < rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.lt(c: Int): ReExpression = lt(c)

/**
 * Extension for [ArExpression.lt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs < rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.lt(e: ArExpression): ReExpression = lt(e)

/**
 * Extension for [ArExpression.le] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs <= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.le(c: Int): ReExpression = le(c)

/**
 * Extension for [ArExpression.le] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs <= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.le(e: ArExpression): ReExpression = le(e)

/**
 * Extension for [ArExpression.gt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs > rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.gt(c: Int): ReExpression = gt(c)

/**
 * Extension for [ArExpression.gt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs > rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.gt(e: ArExpression): ReExpression = gt(e)

/**
 * Extension for [ArExpression.ge] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs >= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.ge(c: Int): ReExpression = ge(c)

/**
 * Extension for [ArExpression.ge] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs >= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun ArExpression.ge(e: ArExpression): ReExpression = ge(e)
