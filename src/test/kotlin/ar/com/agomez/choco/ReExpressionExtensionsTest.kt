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

import org.assertj.core.api.Assertions
import org.chocosolver.solver.Model
import org.chocosolver.solver.expression.discrete.logical.BiLoExpression
import org.chocosolver.solver.expression.discrete.logical.NaLoExpression
import org.chocosolver.solver.expression.discrete.relational.BiReExpression
import org.chocosolver.solver.expression.discrete.relational.NaReExpression
import org.chocosolver.solver.expression.discrete.relational.ReExpression
import org.chocosolver.solver.variables.impl.FixedBoolVarImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class ReExpressionExtensionsTest {

    private lateinit var model: Model
    private lateinit var expr1: ReExpression
    private lateinit var expr2: ReExpression
    private lateinit var expr3: ReExpression

    @BeforeEach
    internal fun setUp() {
        model = Model()
        expr1 = model.boolVar(true)
        expr2 = model.boolVar(false)
        expr3 = model.boolVar(true)
    }

    @Test
    fun testReExpressionUnaryMinus() {
        assertEqualExpressions(-expr1, expr1.not())
    }

    @Test
    fun testReExpressionAndReExpression() {
        assertEqualExpressions(expr1 and expr2, expr1.and(expr2))
    }

    @Test
    fun testReExpressionAndManyReExpression() {
        assertEqualExpressions(expr1 and arrayOf(expr2, expr3), expr1.and(expr2, expr3))
    }

    @Test
    fun testReExpressionOrReExpression() {
        assertEqualExpressions(expr1 or expr2, expr1.or(expr2))
    }

    @Test
    fun testReExpressionOrManyReExpression() {
        assertEqualExpressions(expr1 or arrayOf(expr2, expr3), expr1.or(expr2, expr3))
    }

    @Test
    fun testReExpressionXorReExpression() {
        assertEqualExpressions(expr1 xor expr2, expr1.xor(expr2))
    }

    @Test
    fun testReExpressionXorManyReExpression() {
        assertEqualExpressions(expr1 xor arrayOf(expr2, expr3), expr1.xor(expr2, expr3))
    }

    @Test
    fun testReExpressionImpReExpression() {
        assertEqualExpressions(expr1 imp expr2, expr1.imp(expr2))
    }

    @Test
    fun testReExpressionIffReExpression() {
        assertEqualExpressions(expr1 iff expr2, expr1.iff(expr2))
    }

    @Test
    fun testReExpressionIffManyReExpression() {
        assertEqualExpressions(expr1 iff arrayOf(expr2, expr3), expr1.iff(expr2, expr3))
    }

    private fun assertEqualExpressions(actual: ReExpression, expected: ReExpression) {
        Assertions.assertThat(actual).hasToString(expected.toString())
        if (actual is FixedBoolVarImpl && expected is FixedBoolVarImpl) {
            Assertions.assertThat(actual.booleanValue).isEqualTo(expected.booleanValue)
        } else if (actual is BiReExpression && expected is BiReExpression) {
            reflectiveAssert(actual, expected)
        } else if (actual is NaReExpression && expected is NaReExpression) {
            reflectiveAssert(actual, expected)
        } else if (actual is BiLoExpression && expected is BiLoExpression) {
            reflectiveAssert(actual, expected)
        } else if (actual is NaLoExpression && expected is NaLoExpression) {
            reflectiveAssert(actual, expected)
        } else {
            Assertions.fail("Unknown ReExpression type ${actual::class.java} ${expected::class.java}")
        }
    }
}
