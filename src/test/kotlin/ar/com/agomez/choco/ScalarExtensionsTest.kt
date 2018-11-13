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
class ScalarExtensionsTest {

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
    fun testScalarArrayConstantEQ() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y, -3 * z) eq 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "=", 10))
    }

    @Test
    fun testScalarArrayConstantNE() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y, -3 * z) ne 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "!=", 10))
    }

    @Test
    fun testScalarArrayConstantLT() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y, -3 * z) lt 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "<", 10))
    }

    @Test
    fun testScalarArrayConstantLE() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y, -3 * z) le 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "<=", 10))
    }

    @Test
    fun testScalarArrayConstantGT() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y, -3 * z) gt 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), ">", 10))
    }

    @Test
    fun testScalarArrayConstantGE() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y, -3 * z) ge 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), ">=", 10))
    }

    @Test
    fun testScalarListConstantEQ() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y, -3 * z) eq 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "=", 10))
    }

    @Test
    fun testScalarListConstantNE() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y, -3 * z) ne 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "!=", 10))
    }

    @Test
    fun testScalarListConstantLT() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y, -3 * z) lt 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "<", 10))
    }

    @Test
    fun testScalarListConstantLE() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y, -3 * z) le 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), "<=", 10))
    }

    @Test
    fun testScalarListConstantGT() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y, -3 * z) gt 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), ">", 10))
    }

    @Test
    fun testScalarListConstantGE() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y, -3 * z) ge 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), ">=", 10))
    }

    @Test
    fun testScalarArrayVariableEQ() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y) eq z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "=", z))
    }

    @Test
    fun testScalarArrayVariableNE() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y) ne z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "!=", z))
    }

    @Test
    fun testScalarArrayVariableLT() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y) lt z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "<", z))
    }

    @Test
    fun testScalarArrayVariableLE() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y) le z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "<=", z))
    }

    @Test
    fun testScalarArrayVariableGT() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y) gt z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), ">", z))
    }

    @Test
    fun testScalarArrayVariableGE() {
        assertOnConstraint(model.scalar { arrayOf(-x, 2 * y) ge z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), ">=", z))
    }

    @Test
    fun testScalarListVariableEQ() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y) eq z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "=", z))
    }

    @Test
    fun testScalarListVariableNE() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y) ne z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "!=", z))
    }

    @Test
    fun testScalarListVariableLT() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y) lt z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "<", z))
    }

    @Test
    fun testScalarListVariableLE() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y) le z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), "<=", z))
    }

    @Test
    fun testScalarListVariableGT() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y) gt z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), ">", z))
    }

    @Test
    fun testScalarListVariableGE() {
        assertOnConstraint(model.scalar { listOf(-x, 2 * y) ge z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), ">=", z))
    }

    @Test
    fun testScalarListConstantWithCardinality() {
        assertOnConstraint(model.scalar(8) { listOf(-x, 2 * y, -3 * z) ge 10 }, model.scalar(arrayOf(x, y, z), intArrayOf(-1, 2, -3), ">=", 10, 8))
    }

    @Test
    fun testScalarListVariableWithCardinality() {
        assertOnConstraint(model.scalar(8) { listOf(-x, 2 * y) ge z }, model.scalar(arrayOf(x, y), intArrayOf(-1, 2), ">=", z, 8))
    }
}
