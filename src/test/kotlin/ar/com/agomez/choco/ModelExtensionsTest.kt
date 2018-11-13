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
            Assertions.assertThat(name).isEqualTo("testModel")
            executed.set(true)
        }
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
    fun testRealVarMatrixWithRange() {
        assertOnRealVarMatrix(model.realVarMatrix(4, 3, 1.0..3.0, 0.001), 4, 3, 1.0, 3.0, 0.001)
    }

    @Test
    fun testRealVarMatrixWithNameRange() {
        assertOnRealVarMatrix(model.realVarMatrix("v", 4, 3, 1.0..3.0, 0.001), 4, 3, 1.0, 3.0, 0.001, "v")
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

    private fun assertOnRealVar(v: RealVar, lb: Double, ub: Double, precision: Double, name: String? = null) {
        Assertions.assertThat(v.lb).isEqualTo(lb)
        Assertions.assertThat(v.ub).isEqualTo(ub)
        Assertions.assertThat(v.precision).isEqualTo(precision)
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
