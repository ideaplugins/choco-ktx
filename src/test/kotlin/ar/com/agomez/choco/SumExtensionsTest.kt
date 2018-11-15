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

import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.IntVar
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class SumExtensionsTest {

    private lateinit var model: Model
    private lateinit var x: IntVar
    private lateinit var y: IntVar
    private lateinit var z: IntVar

    @BeforeEach
    internal fun setUp() {
        model = Model()
        x = model.intVar("x", 1, 4)
        y = model.intVar("y", 3, 6)
        z = model.intVar("z", -10, 10)
    }

    @Test
    fun testSumArrayConstantEQ() {
        assertOnConstraint(model.sum { arrayOf(x, y, z) eq 10 }, model.sum(arrayOf(x, y, z), "=", 10))
    }

    @Test
    fun testSumArrayConstantNE() {
        assertOnConstraint(model.sum { arrayOf(x, y, z) ne 10 }, model.sum(arrayOf(x, y, z), "!=", 10))
    }

    @Test
    fun testSumArrayConstantLT() {
        assertOnConstraint(model.sum { arrayOf(x, y, z) lt 10 }, model.sum(arrayOf(x, y, z), "<", 10))
    }

    @Test
    fun testSumArrayConstantLE() {
        assertOnConstraint(model.sum { arrayOf(x, y, z) le 10 }, model.sum(arrayOf(x, y, z), "<=", 10))
    }

    @Test
    fun testSumArrayConstantGT() {
        assertOnConstraint(model.sum { arrayOf(x, y, z) gt 10 }, model.sum(arrayOf(x, y, z), ">", 10))
    }

    @Test
    fun testSumArrayConstantGE() {
        assertOnConstraint(model.sum { arrayOf(x, y, z) ge 10 }, model.sum(arrayOf(x, y, z), ">=", 10))
    }

    @Test
    fun testSumListConstantEQ() {
        assertOnConstraint(model.sum { listOf(x, y, z) eq 10 }, model.sum(arrayOf(x, y, z), "=", 10))
    }

    @Test
    fun testSumListConstantNE() {
        assertOnConstraint(model.sum { listOf(x, y, z) ne 10 }, model.sum(arrayOf(x, y, z), "!=", 10))
    }

    @Test
    fun testSumListConstantLT() {
        assertOnConstraint(model.sum { listOf(x, y, z) lt 10 }, model.sum(arrayOf(x, y, z), "<", 10))
    }

    @Test
    fun testSumListConstantLE() {
        assertOnConstraint(model.sum { listOf(x, y, z) le 10 }, model.sum(arrayOf(x, y, z), "<=", 10))
    }

    @Test
    fun testSumListConstantGT() {
        assertOnConstraint(model.sum { listOf(x, y, z) gt 10 }, model.sum(arrayOf(x, y, z), ">", 10))
    }

    @Test
    fun testSumListConstantGE() {
        assertOnConstraint(model.sum { listOf(x, y, z) ge 10 }, model.sum(arrayOf(x, y, z), ">=", 10))
    }

    @Test
    fun testSumArrayVariableEQ() {
        assertOnConstraint(model.sum { arrayOf(x, y) eq z }, model.sum(arrayOf(x, y), "=", z))
    }

    @Test
    fun testSumArrayVariableNE() {
        assertOnConstraint(model.sum { arrayOf(x, y) ne z }, model.sum(arrayOf(x, y), "!=", z))
    }

    @Test
    fun testSumArrayVariableLT() {
        assertOnConstraint(model.sum { arrayOf(x, y) lt z }, model.sum(arrayOf(x, y), "<", z))
    }

    @Test
    fun testSumArrayVariableLE() {
        assertOnConstraint(model.sum { arrayOf(x, y) le z }, model.sum(arrayOf(x, y), "<=", z))
    }

    @Test
    fun testSumArrayVariableGT() {
        assertOnConstraint(model.sum { arrayOf(x, y) gt z }, model.sum(arrayOf(x, y), ">", z))
    }

    @Test
    fun testSumArrayVariableGE() {
        assertOnConstraint(model.sum { arrayOf(x, y) ge z }, model.sum(arrayOf(x, y), ">=", z))
    }

    @Test
    fun testSumListVariableEQ() {
        assertOnConstraint(model.sum { listOf(x, y) eq z }, model.sum(arrayOf(x, y), "=", z))
    }

    @Test
    fun testSumListVariableNE() {
        assertOnConstraint(model.sum { listOf(x, y) ne z }, model.sum(arrayOf(x, y), "!=", z))
    }

    @Test
    fun testSumListVariableLT() {
        assertOnConstraint(model.sum { listOf(x, y) lt z }, model.sum(arrayOf(x, y), "<", z))
    }

    @Test
    fun testSumListVariableLE() {
        assertOnConstraint(model.sum { listOf(x, y) le z }, model.sum(arrayOf(x, y), "<=", z))
    }

    @Test
    fun testSumListVariableGT() {
        assertOnConstraint(model.sum { listOf(x, y) gt z }, model.sum(arrayOf(x, y), ">", z))
    }

    @Test
    fun testSumListVariableGE() {
        assertOnConstraint(model.sum { listOf(x, y) ge z }, model.sum(arrayOf(x, y), ">=", z))
    }

    @Test
    fun testSumExpression() {
        assertOnConstraint(model.sum { (x + y) ge z }, model.sum(arrayOf(x, y), ">=", z))
        assertOnConstraint(model.sum { (x + y + z) gt 0 }, model.sum(arrayOf(x, y, z), ">", 0))
    }

    @Test
    fun testSumListConstantWithCardinality() {
        assertOnConstraint(model.sum(8) { listOf(x, y, z) ge 10 }, model.sum(arrayOf(x, y, z), ">=", 10, 8))
    }

    @Test
    fun testSumListVariableWithCardinality() {
        assertOnConstraint(model.sum(8) { listOf(x, y) ge z }, model.sum(arrayOf(x, y), ">=", z, 8))
    }
}
