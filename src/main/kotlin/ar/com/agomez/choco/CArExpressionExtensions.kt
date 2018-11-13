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

import org.chocosolver.solver.expression.continuous.arithmetic.CArExpression
import org.chocosolver.solver.expression.continuous.relational.CReExpression

/**
 * Extension for [CArExpression.neg] for invoking it as the unary operator `-`.
 *
 * @receiver The expression to negate.
 * @return The expression `-x`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.unaryMinus(): CArExpression = neg()

/**
 * Extension for [CArExpression.add] for invoking it as the operator `+`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs + rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.plus(c: Double): CArExpression = add(c)

/**
 * Extension for [CArExpression.add] for invoking it as the operator `+`.
 *
 * @receiver The lhs of the operation.
 * @param e The rhs of the operation.
 * @return The expression `lhs + rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.plus(e: CArExpression): CArExpression = add(e)

/**
 * Extension for [CArExpression.sub] for invoking it as the operator `-`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs - rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.minus(c: Double): CArExpression = sub(c)

/**
 * Extension for [CArExpression.sub] for invoking it as the operator `-`.
 *
 * @receiver The lhs of the operation.
 * @param e The rhs of the operation.
 * @return The expression `lhs - rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.minus(e: CArExpression): CArExpression = sub(e)

/**
 * Extension for [CArExpression.mul] for invoking it as the operator `*`.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs * rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.times(c: Double): CArExpression = mul(c)

/**
 * Extension for [CArExpression.mul] for invoking it as the operator `*`.
 *
 * @receiver The lhs of the operation.
 * @param e The rhs of the operation.
 * @return The expression `lhs * rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun CArExpression.times(e: CArExpression): CArExpression = mul(e)

/**
 * Extension for [CArExpression.pow] for invoking it in infix mode.
 *
 * @receiver The base.
 * @param c The exponent.
 * @return The expression `base ** exponent`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.pow(c: Double): CArExpression = pow(c)

/**
 * Extension for [CArExpression.pow] for invoking it in infix mode.
 *
 * @receiver The base.
 * @param e The exponent.
 * @return The expression `base ** exponent`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.pow(e: CArExpression): CArExpression = pow(e)

/**
 * Extension for [CArExpression.atan2] for invoking it in infix mode.
 *
 * @receiver The lhs of the operation.
 * @param c The rhs of the operation.
 * @return The expression `lhs atan2 rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.atan2(c: Double): CArExpression = atan2(c)

/**
 * Extension for [CArExpression.atan2] for invoking it in infix mode.
 *
 * @receiver The lhs of the operation.
 * @param e The rhs of the operation.
 * @return The expression `lhs atan2 rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.atan2(e: CArExpression): CArExpression = atan2(e)

/**
 * Extension for [CArExpression.eq] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs == rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.eq(c: Double): CReExpression = eq(c)

/**
 * Extension for [CArExpression.eq] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs == rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.eq(e: CArExpression): CReExpression = eq(e)

/**
 * Extension for [CArExpression.lt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs < rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.lt(c: Double): CReExpression = lt(c)

/**
 * Extension for [CArExpression.lt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs < rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.lt(e: CArExpression): CReExpression = lt(e)

/**
 * Extension for [CArExpression.le] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs <= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.le(c: Double): CReExpression = le(c)

/**
 * Extension for [CArExpression.le] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs <= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.le(e: CArExpression): CReExpression = le(e)

/**
 * Extension for [CArExpression.gt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs > rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.gt(c: Double): CReExpression = gt(c)

/**
 * Extension for [CArExpression.gt] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs > rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.gt(e: CArExpression): CReExpression = gt(e)

/**
 * Extension for [CArExpression.ge] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param c The rhs of the comparison.
 * @return The expression `lhs >= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.ge(c: Double): CReExpression = ge(c)

/**
 * Extension for [CArExpression.ge] for invoking it in infix mode.
 *
 * @receiver The lhs of the comparison.
 * @param e The rhs of the comparison.
 * @return The expression `lhs >= rhs`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
infix fun CArExpression.ge(e: CArExpression): CReExpression = ge(e)
