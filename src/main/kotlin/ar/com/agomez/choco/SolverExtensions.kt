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

import org.chocosolver.solver.Solution
import org.chocosolver.solver.Solver
import org.chocosolver.solver.variables.IntVar
import org.chocosolver.util.criteria.Criterion
import java.time.Duration
import kotlin.streams.asSequence

/**
 * Extension for [Solver.limitTime] accepting a [Duration] instance.
 *
 * @param duration Maximal resolution time.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Solver.limitTime(duration: Duration): Unit = limitTime(duration.toMillis())

/**
 * Extension for [Solver.streamSolutions] for accessing solutions as a [Sequence].
 *
 * @param stop optional criterion to stop the search before finding all/best solutions.
 * @return a sequence that contained the found solutions.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Solver.sequenceSolutions(vararg stop: Criterion): Sequence<Solution> = streamSolutions(*stop).asSequence()

/**
 * Extension for [Solver.streamOptimalSolutions] for accessing optimal solutions as a [Sequence].
 *
 * @param objective the variable to optimize.
 * @param maximize set to `true` to solve a maximization problem, set to `false` to solve a minimization problem.
 * @param stop optional criterion to stop the search before finding all/best solutions.
 * @return a sequence that contained the found solutions.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Solver.sequenceOptimalSolutions(objective: IntVar, maximize: Boolean, vararg stop: Criterion): Sequence<Solution> = streamOptimalSolutions(objective, maximize, *stop).asSequence()
