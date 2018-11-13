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
import org.chocosolver.solver.search.limits.TimeCounter
import org.chocosolver.solver.variables.IntVar
import org.chocosolver.util.criteria.Criterion
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Duration

/**
 * @author Alejandro Gomez
 */
class SolverExtensionsTest {

    private lateinit var model: Model
    private lateinit var x: IntVar
    private lateinit var y: IntVar

    @BeforeEach
    internal fun setUp() {
        model = Model()
        x = model.intVar("x", 1, 4)
        y = model.intVar("y", 3, 6)
        x.ge(y).post()
    }

    @Test
    fun testLimitTimeWith1Second() {
        model.solver.limitTime(Duration.ofSeconds(1))
        assertOnSolver(true)
    }

    @Test
    fun testLimitTimeWith0Seconds() {
        model.solver.limitTime(Duration.ofSeconds(0))
        assertOnSolver(false)
    }

    @Test
    fun testSequenceSolutions() {
        model.solver.solve()
        val solutions = model.solver.sequenceSolutions(TimeCounter(model, Duration.ofSeconds(1).toNanos()))
        // solutions for x >= y are (4, 3) and (4, 4)
        Assertions.assertThat(solutions.count()).isEqualTo(2)
    }

    @Test
    fun testSequenceOptimalSolutions() {
        model.solver.solve()
        val solutions = model.solver.sequenceOptimalSolutions(y, true, TimeCounter(model, Duration.ofSeconds(1).toNanos()))
        // the only solution for x >= y maximizing y is (4, 4)
        Assertions.assertThat(solutions.count()).isEqualTo(1)
    }

    private fun assertOnSolver(isSolved: Boolean) {
        Assertions.assertThat(model.solver.solve()).isEqualTo(isSolved)
        val criteria = getProperty(model.solver, "criteria") as List<*>
        Assertions.assertThat(criteria).hasSize(1)
        Assertions.assertThat(criteria[0]).isInstanceOf(TimeCounter::class.java)
        Assertions.assertThat((criteria[0] as Criterion).isMet).isEqualTo(!isSolved)
    }
}
