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
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression
import org.chocosolver.solver.expression.discrete.arithmetic.BiArExpression
import org.chocosolver.solver.expression.discrete.arithmetic.NaArExpression
import org.chocosolver.solver.expression.discrete.arithmetic.UnArExpression
import org.chocosolver.solver.expression.discrete.relational.BiReExpression
import org.chocosolver.solver.expression.discrete.relational.NaReExpression
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.contracts.ExperimentalContracts

/**
 * @author Alejandro Gomez
 */
@ExperimentalContracts
class ArExpressionExtensionsTest {

    private lateinit var model: Model
    private lateinit var expr1: ArExpression
    private lateinit var expr2: ArExpression
    private lateinit var expr3: ArExpression

    @BeforeEach
    internal fun setUp() {
        model = Model()
        expr1 = model.intVar(0, 9)
        expr2 = model.intVar(10, 19)
        expr3 = model.intVar(20, 29)
    }

    @Test
    fun testArExpressionUnaryMinus() {
        assertEqualExpressions(-expr1, expr1.neg())
    }

    @Test
    fun testArExpressionPlusConstant() {
        assertEqualExpressions(expr1 + 1, expr1.add(1))
    }

    @Test
    fun testArExpressionPlusArExpression() {
        assertEqualExpressions(expr1 + expr2, expr1.add(expr2))
    }

    @Test
    fun testArExpressionPlusManyArExpression() {
        assertEqualExpressions(expr1 + arrayOf(expr2, expr3), expr1.add(expr2, expr3))
    }

    @Test
    fun testArExpressionPlusIterableArExpression() {
        assertEqualExpressions(expr1 + listOf(expr2, expr3), expr1.add(expr2, expr3))
    }

    @Test
    fun testArExpressionMinusConstant() {
        assertEqualExpressions(expr1 - 1, expr1.sub(1))
    }

    @Test
    fun testArExpressionMinusArExpression() {
        assertEqualExpressions(expr1 - expr2, expr1.sub(expr2))
    }

    @Test
    fun testArExpressionTimesConstant() {
        assertEqualExpressions(expr1 * 1, expr1.mul(1))
    }

    @Test
    fun testArExpressionTimesArExpression() {
        assertEqualExpressions(expr1 * expr2, expr1.mul(expr2))
    }

    @Test
    fun testArExpressionTimesManyArExpression() {
        assertEqualExpressions(expr1 * arrayOf(expr2, expr3), expr1.mul(expr2, expr3))
    }

    @Test
    fun testArExpressionTimesIterableArExpression() {
        assertEqualExpressions(expr1 * listOf(expr2, expr3), expr1.mul(expr2, expr3))
    }

    @Test
    fun testArExpressionDivConstant() {
        assertEqualExpressions(expr1 / 1, expr1.div(1))
    }

    @Test
    fun testArExpressionDivArExpression() {
        assertEqualExpressions(expr1 / expr2, expr1.div(expr2))
    }

    @Test
    fun testArExpressionRemConstant() {
        assertEqualExpressions(expr1 % 1, expr1.mod(1))
    }

    @Test
    fun testArExpressionRemArExpression() {
        assertEqualExpressions(expr1 % expr2, expr1.mod(expr2))
    }

    @Test
    fun testArExpressionPowConstant() {
        assertEqualExpressions(expr1 pow 1, expr1.pow(1))
    }

    @Test
    fun testArExpressionPowArExpression() {
        assertEqualExpressions(expr1 pow expr2, expr1.pow(expr2))
    }

    @Test
    fun testArExpressionEqConstant() {
        assertEqualExpressions(expr1 eq 1, expr1.eq(1))
    }

    @Test
    fun testArExpressionEqArExpression() {
        assertEqualExpressions(expr1 eq expr2, expr1.eq(expr2))
    }

    @Test
    fun testArExpressionEqManyArExpression() {
        assertEqualExpressions(expr1 eq arrayOf(expr2, expr3), expr1.eq(expr2, expr3))
    }

    @Test
    fun testArExpressionEqIterableArExpression() {
        assertEqualExpressions(expr1 eq listOf(expr2, expr3), expr1.eq(expr2, expr3))
    }

    @Test
    fun testArExpressionNeConstant() {
        assertEqualExpressions(expr1 ne 1, expr1.ne(1))
    }

    @Test
    fun testArExpressionNeArExpression() {
        assertEqualExpressions(expr1 ne expr2, expr1.ne(expr2))
    }

    @Test
    fun testArExpressionLtConstant() {
        assertEqualExpressions(expr1 lt 1, expr1.lt(1))
    }

    @Test
    fun testArExpressionLtArExpression() {
        assertEqualExpressions(expr1 lt expr2, expr1.lt(expr2))
    }

    @Test
    fun testArExpressionLeConstant() {
        assertEqualExpressions(expr1 le 1, expr1.le(1))
    }

    @Test
    fun testArExpressionLeArExpression() {
        assertEqualExpressions(expr1 le expr2, expr1.le(expr2))
    }

    @Test
    fun testArExpressionGtConstant() {
        assertEqualExpressions(expr1 gt 1, expr1.gt(1))
    }

    @Test
    fun testArExpressionGtArExpression() {
        assertEqualExpressions(expr1 gt expr2, expr1.gt(expr2))
    }

    @Test
    fun testArExpressionGeConstant() {
        assertEqualExpressions(expr1 ge 1, expr1.ge(1))
    }

    @Test
    fun testArExpressionGeArExpression() {
        assertEqualExpressions(expr1 ge expr2, expr1.ge(expr2))
    }

    private fun assertEqualExpressions(actual: ArExpression, expected: ArExpression) {
        Assertions.assertThat(actual).hasToString(expected.toString())
        Assertions.assertThat(actual).hasSameClassAs(expected)
        if (actual is UnArExpression || actual is BiArExpression || actual is NaArExpression) {
            if (actual is BiArExpression && expected is BiArExpression) {
                Assertions.assertThat(actual.op).isEqualTo(expected.op)
            }
            if (actual is NaArExpression && expected is NaArExpression) {
                Assertions.assertThat(actual.op).isEqualTo(expected.op)
            }
            if (actual is UnArExpression || actual is BiArExpression || actual is NaArExpression) {
                Assertions.assertThat(actual.expressionChild).hasSameSizeAs(expected.expressionChild)
                actual.expressionChild.zip(expected.expressionChild).forEach {
                    Assertions.assertThat(it.first).isEqualTo(it.second)
                }
            }
        } else if (actual is BiReExpression && expected is BiReExpression) {
            reflectiveAssert(actual, expected)
        } else if (actual is NaReExpression && expected is NaReExpression) {
            reflectiveAssert(actual, expected)
        } else {
            Assertions.fail("Unknown ArExpression type ${actual::class.java} ${expected::class.java}")
        }
    }
}
