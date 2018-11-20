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
import org.chocosolver.solver.ResolutionPolicy
import org.chocosolver.solver.constraints.Constraint
import org.chocosolver.solver.constraints.Operator
import org.chocosolver.solver.constraints.nary.alldifferent.conditions.Condition
import org.chocosolver.solver.variables.BoolVar
import org.chocosolver.solver.variables.IntVar
import org.chocosolver.solver.variables.RealVar
import org.chocosolver.util.ESat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Alejandro Gomez
 */
class ModelExtensionsTest {

    private lateinit var model: Model
    private lateinit var v: BoolVar
    private lateinit var w: BoolVar
    private lateinit var x: IntVar
    private lateinit var y: IntVar
    private lateinit var z: IntVar

    @BeforeEach
    internal fun setUp() {
        model = Model()
        v = model.boolVar("v", true)
        w = model.boolVar("w", false)
        x = model.intVar("x", 1, 4)
        y = model.intVar("y", 3, 6)
        z = model.intVar("z", -10, 10)
        x.ge(y).post()
    }

    @Test
    fun testModel() {
        val executed = AtomicBoolean(false)
        val m = model("testModel") {
            Assertions.assertThat(this).isInstanceOf(Model::class.java)
            Assertions.assertThat(name).isEqualTo("testModel")
            executed.set(true)
        }
        Assertions.assertThat(m).isInstanceOf(Model::class.java)
        Assertions.assertThat(m.name).isEqualTo("testModel")
        Assertions.assertThat(executed.get()).isTrue()
    }

    @Test
    fun testRunWithModel() {
        val n = runWithModel("testModel") {
            Assertions.assertThat(name).isEqualTo("testModel")
            42
        }
        Assertions.assertThat(n).isEqualTo(42)
    }

    @Test
    fun testDigit() {
        val d = model.digit("d")
        Assertions.assertThat(d.lb).isEqualTo(0)
        Assertions.assertThat(d.ub).isEqualTo(9)
        Assertions.assertThat(d.hasEnumeratedDomain()).isTrue()
    }

    @Test
    fun testDigitNonZero() {
        val d = model.digitNonZero("d")
        Assertions.assertThat(d.lb).isEqualTo(1)
        Assertions.assertThat(d.ub).isEqualTo(9)
        Assertions.assertThat(d.hasEnumeratedDomain()).isTrue()
    }

    @Test
    fun testDigitWithBoundedDomain() {
        val d = model.digit("d", true)
        Assertions.assertThat(d.lb).isEqualTo(0)
        Assertions.assertThat(d.ub).isEqualTo(9)
        Assertions.assertThat(d.hasEnumeratedDomain()).isFalse()
    }

    @Test
    fun testDigitNonZeroWithBoundedDomain() {
        val d = model.digitNonZero("d", true)
        Assertions.assertThat(d.lb).isEqualTo(1)
        Assertions.assertThat(d.ub).isEqualTo(9)
        Assertions.assertThat(d.hasEnumeratedDomain()).isFalse()
    }

    @Test
    fun testTrueVar() {
        assertOnBoolVar(model.trueVar(), true)
    }

    @Test
    fun testTrueVarWithName() {
        assertOnBoolVar(model.trueVar("b"), true, "b")
    }

    @Test
    fun testFalseVar() {
        assertOnBoolVar(model.falseVar(), false)
    }

    @Test
    fun testFalseVarWithName() {
        assertOnBoolVar(model.falseVar("b"), false, "b")
    }

    @Test
    fun testMinimize() {
        model.minimize(x)
        Assertions.assertThat(model.resolutionPolicy).isEqualTo(ResolutionPolicy.MINIMIZE)
        Assertions.assertThat(model.objective).isEqualTo(x)
    }

    @Test
    fun testMaximize() {
        model.maximize(x)
        Assertions.assertThat(model.resolutionPolicy).isEqualTo(ResolutionPolicy.MAXIMIZE)
        Assertions.assertThat(model.objective).isEqualTo(x)
    }

    @Test
    fun testIntVarWithRange() {
        assertOnIntVar(model.intVar(4..8), 4, 8)
    }

    @Test
    fun testIntVarWithRangeBoundedDomainTrue() {
        assertOnIntVar(model.intVar(4..8, true), 4, 8, hasEnumeratedDomain = false)
    }

    @Test
    fun testIntVarWithRangeBoundedDomainFalse() {
        assertOnIntVar(model.intVar(4..8, false), 4, 8, hasEnumeratedDomain = true)
    }

    @Test
    fun testIntVarWithNameRange() {
        assertOnIntVar(model.intVar("v", 4..8), 4, 8, "v")
    }

    @Test
    fun testIntVarWithNameRangeBoundedDomainTrue() {
        assertOnIntVar(model.intVar("v", 4..8, true), 4, 8, "v", false)
    }

    @Test
    fun testIntVarWithNameRangeBoundedDomainFalse() {
        assertOnIntVar(model.intVar("v", 4..8, false), 4, 8, "v", true)
    }

    @Test
    fun testIntVarArrayWithRange() {
        assertOnIntVarArray(model.intVarArray(3, 4..8), 3, 4, 8)
    }

    @Test
    fun testIntVarArrayWithRangeBoundedDomainTrue() {
        assertOnIntVarArray(model.intVarArray(3, 4..8, true), 3, 4, 8, hasEnumeratedDomain = false)
    }

    @Test
    fun testIntVarArrayWithRangeBoundedDomainFalse() {
        assertOnIntVarArray(model.intVarArray(3, 4..8, false), 3, 4, 8, hasEnumeratedDomain = true)
    }

    @Test
    fun testIntVarArrayWithNameRange() {
        assertOnIntVarArray(model.intVarArray("v", 3, 4..8), 3, 4, 8, "v")
    }

    @Test
    fun testIntVarArrayWithNameRangeBoundedDomainTrue() {
        assertOnIntVarArray(model.intVarArray("v", 3, 4..8, true), 3, 4, 8, "v", false)
    }

    @Test
    fun testIntVarArrayWithNameRangeBoundedDomainFalse() {
        assertOnIntVarArray(model.intVarArray("v", 3, 4..8, false), 3, 4, 8, "v", true)
    }

    @Test
    fun testIntVarArrayFixed() {
        val array = model.intVarArrayFixed("v", 3, intArrayOf(1, 2, 3))
        Assertions.assertThat(array).hasSize(3)
        assertOnIntVar(array[0], 1, 1, "v[0]")
        assertOnIntVar(array[1], 2, 2, "v[1]")
        assertOnIntVar(array[2], 3, 3, "v[2]")
    }

    @Test
    fun testIntVarMatrixWithRange() {
        assertOnIntVarMatrix(model.intVarMatrix(3, 2, 4..8), 3, 2, 4, 8)
    }

    @Test
    fun testIntVarMatrixWithRangeBoundedDomainTrue() {
        assertOnIntVarMatrix(model.intVarMatrix(3, 2, 4..8, true), 3, 2, 4, 8, hasEnumeratedDomain = false)
    }

    @Test
    fun testIntVarMatrixWithRangeBoundedDomainFalse() {
        assertOnIntVarMatrix(model.intVarMatrix(3, 2, 4..8, false), 3, 2, 4, 8, hasEnumeratedDomain = true)
    }

    @Test
    fun testIntVarMatrixWithNameRange() {
        assertOnIntVarMatrix(model.intVarMatrix("v", 3, 2, 4..8), 3, 2, 4, 8, "v")
    }

    @Test
    fun testIntVarMatrixWithNameRangeBoundedDomainTrue() {
        assertOnIntVarMatrix(model.intVarMatrix("v", 3, 2, 4..8, true), 3, 2, 4, 8, "v", false)
    }

    @Test
    fun testIntVarMatrixWithNameRangeBoundedDomainFalse() {
        assertOnIntVarMatrix(model.intVarMatrix("v", 3, 2, 4..8, false), 3, 2, 4, 8, "v", true)
    }

    @Test
    fun testIntVarMatrixFixed() {
        val matrix = model.intVarMatrixFixed("v", 2, 3, arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)))
        Assertions.assertThat(matrix).hasSize(2)
        matrix.forEach { row ->
            Assertions.assertThat(row).hasSize(3)
        }
        assertOnIntVar(matrix[0][0], 1, 1, "v[0][0]")
        assertOnIntVar(matrix[0][1], 2, 2, "v[0][1]")
        assertOnIntVar(matrix[0][2], 3, 3, "v[0][2]")
        assertOnIntVar(matrix[1][0], 4, 4, "v[1][0]")
        assertOnIntVar(matrix[1][1], 5, 5, "v[1][1]")
        assertOnIntVar(matrix[1][2], 6, 6, "v[1][2]")
    }

    @Test
    fun testRealVarWithRange() {
        assertOnRealVar(model.realVar(1.0..3.0, 0.001), 1.0, 3.0, 0.001)
    }

    @Test
    fun testRealVarWithNameRange() {
        assertOnRealVar(model.realVar("v", 1.0..3.0, 0.001), 1.0, 3.0, 0.001, "v")
    }

    @Test
    fun testRealVarArrayWithRange() {
        assertOnRealVarArray(model.realVarArray(4, 1.0..3.0, 0.001), 4, 1.0, 3.0, 0.001)
    }

    @Test
    fun testRealVarArrayWithNameRange() {
        assertOnRealVarArray(model.realVarArray("v", 4, 1.0..3.0, 0.001), 4, 1.0, 3.0, 0.001, "v")
    }

    @Test
    fun testRealVarArrayFixed() {
        val array = model.realVarArrayFixed("v", 3, doubleArrayOf(1.1, 2.2, 3.3))
        Assertions.assertThat(array).hasSize(3)
        assertOnRealVar(array[0], 1.1, 1.1, name = "v[0]")
        assertOnRealVar(array[1], 2.2, 2.2, name = "v[1]")
        assertOnRealVar(array[2], 3.3, 3.3, name = "v[2]")
    }

    @Test
    fun testRealVarMatrixWithRange() {
        assertOnRealVarMatrix(model.realVarMatrix(4, 3, 1.0..3.0, 0.001), 4, 3, 1.0, 3.0, 0.001)
    }

    @Test
    fun testRealVarMatrixWithNameRange() {
        assertOnRealVarMatrix(model.realVarMatrix("v", 4, 3, 1.0..3.0, 0.001), 4, 3, 1.0, 3.0, 0.001, "v")
    }

    @Test
    fun testRealVarMatrixFixed() {
        val matrix = model.realVarMatrixFixed("v", 2, 3, arrayOf(doubleArrayOf(1.1, 2.2, 3.3), doubleArrayOf(4.4, 5.5, 6.6)))
        Assertions.assertThat(matrix).hasSize(2)
        matrix.forEach { row ->
            Assertions.assertThat(row).hasSize(3)
        }
        assertOnRealVar(matrix[0][0], 1.1, 1.1, name = "v[0][0]")
        assertOnRealVar(matrix[0][1], 2.2, 2.2, name = "v[0][1]")
        assertOnRealVar(matrix[0][2], 3.3, 3.3, name = "v[0][2]")
        assertOnRealVar(matrix[1][0], 4.4, 4.4, name = "v[1][0]")
        assertOnRealVar(matrix[1][1], 5.5, 5.5, name = "v[1][1]")
        assertOnRealVar(matrix[1][2], 6.6, 6.6, name = "v[1][2]")
    }

    @Test
    fun testScalarVsConstant() {
        assertOnSumConstraint(model.scalar(listOf(2 to x, -3 to y), Operator.GE, 10), model.scalar(arrayOf(x, y), arrayOf(2, -3).toIntArray(), ">=", 10))
    }

    @Test
    fun testScalarVsIntVar() {
        assertOnSumConstraint(model.scalar(listOf(2 to x, -3 to y), Operator.LT, z), model.scalar(arrayOf(x, y), arrayOf(2, -3).toIntArray(), "<", z))
    }

    @Test
    fun testScalarVsConstantWithCardinality() {
        assertOnSumConstraint(model.scalar(listOf(2 to x, -3 to y), Operator.GE, 10, 2), model.scalar(arrayOf(x, y), arrayOf(2, -3).toIntArray(), ">=", 10, 2))
    }

    @Test
    fun testScalarVsIntVarWithCardinality() {
        assertOnSumConstraint(model.scalar(listOf(2 to x, -3 to y), Operator.LT, z, 2), model.scalar(arrayOf(x, y), arrayOf(2, -3).toIntArray(), "<", z, 2))
    }

    @Test
    fun testSumVsConstant() {
        assertOnSumConstraint(model.sum(arrayOf(x, y), Operator.LT, 20), model.sum(arrayOf(x, y), "<", 20))
    }

    @Test
    fun testSumIterabledVsConstant() {
        assertOnSumConstraint(model.sum(listOf(x, y), Operator.LT, 20), model.sum(arrayOf(x, y), "<", 20))
    }

    @Test
    fun testSumVsIntVar() {
        assertOnSumConstraint(model.sum(arrayOf(x, y), Operator.LT, z), model.sum(arrayOf(x, y), "<", z))
    }

    @Test
    fun testSumIterableVsIntVar() {
        assertOnSumConstraint(model.sum(listOf(x, y), Operator.LT, z), model.sum(arrayOf(x, y), "<", z))
    }

    @Test
    fun testSumVsConstantWithCardinality() {
        assertOnSumConstraint(model.sum(arrayOf(x, y), Operator.LT, 20, 3), model.sum(arrayOf(x, y), "<", 20, 3))
    }

    @Test
    fun testSumIterableVsConstantWithCardinality() {
        assertOnSumConstraint(model.sum(listOf(x, y), Operator.LT, 20, 3), model.sum(arrayOf(x, y), "<", 20, 3))
    }

    @Test
    fun testSumVsIntVarWithCardinality() {
        assertOnSumConstraint(model.sum(arrayOf(x, y), Operator.LT, z, 3), model.sum(arrayOf(x, y), "<", z, 3))
    }

    @Test
    fun testSumIterableVsIntVarWithCardinality() {
        assertOnSumConstraint(model.sum(listOf(x, y), Operator.LT, z, 3), model.sum(arrayOf(x, y), "<", z, 3))
    }

    @Test
    fun testSumBoolVsConstant() {
        assertOnSumConstraint(model.sum(arrayOf(v, w), Operator.NQ, 20), model.sum(arrayOf(v, w), "!=", 20))
    }

    @Test
    fun testSumBoolIterableVsConstant() {
        assertOnSumConstraint(model.sum(listOf(v, w), Operator.NQ, 20), model.sum(arrayOf(v, w), "!=", 20))
    }

    @Test
    fun testSumBoolVsIntVar() {
        assertOnSumConstraint(model.sum(arrayOf(v, w), Operator.NQ, z), model.sum(arrayOf(v, w), "!=", z))
    }

    @Test
    fun testSumBoolIterableVsIntVar() {
        assertOnSumConstraint(model.sum(listOf(v, w), Operator.NQ, z), model.sum(arrayOf(v, w), "!=", z))
    }

    @Test
    fun testSumBoolVsIntVarWithCardinality() {
        assertOnSumConstraint(model.sum(arrayOf(v, w), Operator.NQ, z, 3), model.sum(arrayOf(v, w), "!=", z, 3))
    }

    @Test
    fun testSumBoolIterableVsIntVarWithCardinality() {
        assertOnSumConstraint(model.sum(listOf(v, w), Operator.NQ, z, 3), model.sum(arrayOf(v, w), "!=", z, 3))
    }

    @Test
    fun testAllDifferent() {
        assertOnConstraint(model.allDifferent(listOf(x, y, z)), model.allDifferent(x, y, z))
    }

    @Test
    fun testAllDifferentWithConsistency() {
        assertOnConstraint(model.allDifferent(listOf(x, y, z), Consistency.BC), model.allDifferent(arrayOf(x, y, z), "BC"))
    }

    @Test
    fun testAllDifferentUnderCondition() {
        assertOnConstraint(model.allDifferentUnderCondition(listOf(x, y, z), Condition.TRUE, true), model.allDifferentUnderCondition(arrayOf(x, y, z), Condition.TRUE, true))
    }

    @Test
    fun testAllDifferentExcept0() {
        assertOnConstraint(model.allDifferentExcept0(listOf(x, y, z)), model.allDifferentExcept0(arrayOf(x, y, z)))
    }

    @Test
    fun testAllEqual() {
        assertOnConstraint(model.allEqual(listOf(x, y, z)), model.allEqual(x, y, z))
    }

    @Test
    fun testNotAllEqual() {
        assertOnConstraint(model.notAllEqual(listOf(x, y, z)), model.notAllEqual(x, y, z))
    }

    @Test
    fun testAmong() {
        assertOnConstraint(model.among(x, listOf(x, y, z), intArrayOf(1, 2)), model.among(x, arrayOf(x, y, z), intArrayOf(1, 2)))
    }

    @Test
    fun testAmongList() {
        assertOnConstraint(model.among(x, listOf(x, y, z), listOf(1, 2)), model.among(x, arrayOf(x, y, z), intArrayOf(1, 2)))
    }

    @Test
    fun testAndBoolVars() {
        val v1 = model.boolVar("v1")
        val v2 = model.boolVar("v2")
        assertOnConstraint(model.andV(listOf(v1, v2)), model.and(v1, v2), """ARITHM \(\[IV_[\d]+ = 2]\)""")
    }

    @Test
    fun testAndConstraints() {
        val c1 = model.allEqual(x, y)
        val c2 = model.notAllEqual(y, z)
        assertOnConstraint(model.andC(listOf(c1, c2)), model.and(c1, c2), """ARITHM \(\[IV_[\d]+ = 2]\)""")
    }

    @Test
    fun testGlobalCardinality() {
        assertOnConstraint(model.globalCardinality(arrayOf(x, y, z), 1..3, arrayOf(x, y, z)), model.globalCardinality(arrayOf(x, y, z), intArrayOf(1, 2, 3), arrayOf(x, y, z), true))
    }

    @Test
    fun testGlobalCardinalityList() {
        assertOnConstraint(model.globalCardinality(listOf(x, y, z), 1..3, listOf(x, y, z), false), model.globalCardinality(arrayOf(x, y, z), intArrayOf(1, 2, 3), arrayOf(x, y, z), false))
    }

    private fun assertOnBoolVar(v: BoolVar, value: Boolean, name: String? = null) {
        Assertions.assertThat(v.booleanValue).isEqualTo(if (value) ESat.TRUE else ESat.FALSE)
        name?.run {
            Assertions.assertThat(v.name).isEqualTo(this)
        }
    }

    private fun assertOnIntVar(v: IntVar, lb: Int, ub: Int, name: String? = null, hasEnumeratedDomain: Boolean? = null) {
        Assertions.assertThat(v.lb).isEqualTo(lb)
        Assertions.assertThat(v.ub).isEqualTo(ub)
        name?.run {
            Assertions.assertThat(v.name).isEqualTo(this)
        }
        hasEnumeratedDomain?.run {
            Assertions.assertThat(v.hasEnumeratedDomain()).isEqualTo(this)
        }
    }

    private fun assertOnIntVarArray(vs: Array<out IntVar>, size: Int, lb: Int, ub: Int, name: String? = null, hasEnumeratedDomain: Boolean? = null) {
        Assertions.assertThat(vs).hasSize(size)
        vs.forEach {
            assertOnIntVar(it, lb, ub, null, hasEnumeratedDomain)
        }
        name?.run {
            vs.forEachIndexed { i, v ->
                Assertions.assertThat(v.name).isEqualTo("$this[$i]")
            }
        }
    }

    private fun assertOnIntVarMatrix(vs: Matrix<IntVar>, dim1: Int, dim2: Int, lb: Int, ub: Int, name: String? = null, hasEnumeratedDomain: Boolean? = null) {
        Assertions.assertThat(vs).hasSize(dim1)
        vs.forEach { row ->
            Assertions.assertThat(row).hasSize(dim2)
            row.forEach {
                assertOnIntVar(it, lb, ub, null, hasEnumeratedDomain)
            }
        }
        name?.run {
            vs.forEachIndexed { i, r ->
                r.forEachIndexed { j, v ->
                    Assertions.assertThat(v.name).isEqualTo("$this[$i][$j]")
                }
            }
        }
    }

    private fun assertOnRealVar(v: RealVar, lb: Double, ub: Double, precision: Double? = null, name: String? = null) {
        Assertions.assertThat(v.lb).isEqualTo(lb)
        Assertions.assertThat(v.ub).isEqualTo(ub)
        precision?.run {
            Assertions.assertThat(v.precision).isEqualTo(this)
        }
        name?.run {
            Assertions.assertThat(v.name).isEqualTo(this)
        }
    }

    private fun assertOnRealVarArray(vs: Array<out RealVar>, size: Int, lb: Double, ub: Double, precision: Double, name: String? = null) {
        Assertions.assertThat(vs).hasSize(size)
        vs.forEach {
            assertOnRealVar(it, lb, ub, precision)
        }
        name?.run {
            vs.forEachIndexed { i, v ->
                Assertions.assertThat(v.name).isEqualTo("$this[$i]")
            }
        }
    }

    private fun assertOnRealVarMatrix(vs: Matrix<RealVar>, dim1: Int, dim2: Int, lb: Double, ub: Double, precision: Double, name: String? = null) {
        Assertions.assertThat(vs).hasSize(dim1)
        vs.forEach { row ->
            Assertions.assertThat(row).hasSize(dim2)
            row.forEach {
                assertOnRealVar(it, lb, ub, precision)
            }
        }
        name?.run {
            vs.forEachIndexed { i, r ->
                r.forEachIndexed { j, v ->
                    Assertions.assertThat(v.name).isEqualTo("$this[$i][$j]")
                }
            }
        }
    }

    private fun assertOnSumConstraint(actual: Constraint, expected: Constraint) {
        assertOnConstraint(actual, expected)
    }
}
