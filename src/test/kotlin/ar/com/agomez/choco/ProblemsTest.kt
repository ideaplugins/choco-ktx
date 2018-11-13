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
import org.chocosolver.solver.search.strategy.Search
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class ProblemsTest {

    @Test
    fun testMagicSquare() {
        model("magic square") {
            val n = 3
            val d = n * (n * n + 1) / 2
            val m = intVarMatrix("X", n, n, 1, n * n)
            allDifferent(m.flatMap { it.asIterable() }).post()
            m.forEach {
                sum { it eq d }.post()
            }
            (0 until n).forEach { j ->
                sum { (0 until n).map { i -> m[i][j] } eq d }.post()
            }
            sum { (0 until n).map { i -> m[i][i] } eq d }.post()
            sum { (0 until n).map { i -> m[i][n - i - 1] } eq d }.post()
            solver.showSolutions()
            solver.solveAll()
            Assertions.assertThat(solver.solutionCount).isGreaterThan(0)
        }
    }

    @Test
    fun testSendMoreMoney() {
        model("send+more=money") {
            val s = digitNonZero("s")
            val e = digit("e")
            val n = digit("n")
            val d = digit("d")
            val m = digitNonZero("m")
            val o = digit("o")
            val r = digit("r")
            val y = digit("y")
            allDifferent(s, e, n, d, m, o, r, y).post()
            scalar { arrayOf(1000 * s, 100 * e, 10 * n, 1 * d, 1000 * m, 100 * o, 10 * r, 1 * e, -10000 * m, -1000 * o, -100 * n, -10 * e, -1 * y) eq 0 }.post()
            solver.showSolutions()
            solver.solveAll()
            Assertions.assertThat(solver.solutionCount).isGreaterThan(0)
        }
    }

    @Test
    fun testSendMoreMoneyUsingCarry() {
        model("send+more=money") {
            val s = digitNonZero("s")
            val e = digit("e")
            val n = digit("n")
            val d = digit("d")
            val m = digitNonZero("m")
            val o = digit("o")
            val r = digit("r")
            val y = digit("y")
            val x = boolVarArray("X", 3)
            allDifferent(s, e, n, d, m, o, r, y).post()
            ((d + e) eq (y + (x[0] * 10))).post()
            ((x[0] + n + r) eq (e + (x[1] * 10))).post()
            ((x[1] + e + o) eq (n + (x[2] * 10))).post()
            ((x[2] + s + m) eq (o + (m * 10))).post()
            solver.setSearch(Search.inputOrderLBSearch(s, e, n, d, m, o, r, y))
            solver.showSolutions()
            solver.solveAll()
            Assertions.assertThat(solver.solutionCount).isGreaterThan(0)
        }
    }
}
