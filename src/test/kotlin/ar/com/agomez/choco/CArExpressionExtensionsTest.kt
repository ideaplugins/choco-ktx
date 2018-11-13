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
import org.chocosolver.solver.expression.continuous.arithmetic.BiCArExpression
import org.chocosolver.solver.expression.continuous.arithmetic.CArExpression
import org.chocosolver.solver.expression.continuous.arithmetic.UnCArExpression
import org.chocosolver.solver.expression.continuous.relational.BiCReExpression
import org.chocosolver.solver.expression.continuous.relational.CReExpression
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class CArExpressionExtensionsTest {

    private lateinit var model: Model
    private lateinit var expr1: CArExpression
    private lateinit var expr2: CArExpression
    private lateinit var expr3: CArExpression

    @BeforeEach
    internal fun setUp() {
        model = Model()
        expr1 = model.realVar(0.0, 9.0, 0.001)
        expr2 = model.realVar(10.0, 19.0, 0.001)
        expr3 = model.realVar(20.0, 29.0, 0.001)
    }

    @Test
    fun testCArExpressionUnaryMinus() {
        assertEqualExpressions(-expr1, expr1.neg())
    }

    @Test
    fun testCArExpressionPlusConstant() {
        assertEqualExpressions(expr1 + 1.0, expr1.add(1.0))
    }

    @Test
    fun testCArExpressionPlusCArExpression() {
        assertEqualExpressions(expr1 + expr2, expr1.add(expr2))
    }

    @Test
    fun testCArExpressionMinusConstant() {
        assertEqualExpressions(expr1 - 1.0, expr1.sub(1.0))
    }

    @Test
    fun testCArExpressionMinusCArExpression() {
        assertEqualExpressions(expr1 - expr2, expr1.sub(expr2))
    }

    @Test
    fun testCArExpressionTimesConstant() {
        assertEqualExpressions(expr1 * 1.0, expr1.mul(1.0))
    }

    @Test
    fun testCArExpressionTimesCArExpression() {
        assertEqualExpressions(expr1 * expr2, expr1.mul(expr2))
    }

    @Test
    fun testCArExpressionDivConstant() {
        assertEqualExpressions(expr1 / 1.0, expr1.div(1.0))
    }

    @Test
    fun testCArExpressionDivCArExpression() {
        assertEqualExpressions(expr1 / expr2, expr1.div(expr2))
    }

    @Test
    fun testCArExpressionPowConstant() {
        assertEqualExpressions(expr1 pow 1.0, expr1.pow(1.0))
    }

    @Test
    fun testCArExpressionPowCArExpression() {
        assertEqualExpressions(expr1 pow expr2, expr1.pow(expr2))
    }

    @Test
    fun testCArExpressionAtan2Constant() {
        assertEqualExpressions(expr1 atan2 1.0, expr1.atan2(1.0))
    }

    @Test
    fun testCArExpressionAtan2CArExpression() {
        assertEqualExpressions(expr1 atan2 expr2, expr1.atan2(expr2))
    }

    @Test
    fun testCArExpressionEqConstant() {
        assertEqualExpressions(expr1 eq 1.0, expr1.eq(1.0))
    }

    @Test
    fun testCArExpressionEqCArExpression() {
        assertEqualExpressions(expr1 eq expr2, expr1.eq(expr2))
    }

    @Test
    fun testCArExpressionLtConstant() {
        assertEqualExpressions(expr1 lt 1.0, expr1.lt(1.0))
    }

    @Test
    fun testCArExpressionLtCArExpression() {
        assertEqualExpressions(expr1 lt expr2, expr1.lt(expr2))
    }

    @Test
    fun testCArExpressionLeConstant() {
        assertEqualExpressions(expr1 le 1.0, expr1.le(1.0))
    }

    @Test
    fun testCArExpressionLeCArExpression() {
        assertEqualExpressions(expr1 le expr2, expr1.le(expr2))
    }

    @Test
    fun testCArExpressionGtConstant() {
        assertEqualExpressions(expr1 gt 1.0, expr1.gt(1.0))
    }

    @Test
    fun testCArExpressionGtCArExpression() {
        assertEqualExpressions(expr1 gt expr2, expr1.gt(expr2))
    }

    @Test
    fun testCArExpressionGeConstant() {
        assertEqualExpressions(expr1 ge 1.0, expr1.ge(1.0))
    }

    @Test
    fun testCArExpressionGeCArExpression() {
        assertEqualExpressions(expr1 ge expr2, expr1.ge(expr2))
    }

    private fun assertEqualExpressions(actual: CArExpression, expected: CArExpression) {
        Assertions.assertThat(actual).hasToString(expected.toString())
        if (actual is UnCArExpression && expected is UnCArExpression) {
            reflectiveAssert(actual, expected)
        } else if (actual is BiCArExpression && expected is BiCArExpression) {
            reflectiveAssert(actual, expected)
        } else {
            Assertions.fail("Unknown CArExpression type ${actual::class.java} ${expected::class.java}")
        }
    }

    private fun assertEqualExpressions(actual: CReExpression, expected: CReExpression) {
        Assertions.assertThat(actual).hasToString(expected.toString())
        if (actual is BiCReExpression && expected is BiCReExpression) {
            reflectiveAssert(actual, expected)
        } else {
            Assertions.fail("Unknown CReExpression type ${actual::class.java} ${expected::class.java}")
        }
    }
}
