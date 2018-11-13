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

import org.chocosolver.solver.variables.BoolVar

/**
 * Extension for [BoolVar.not] for invoking it as the unary operator `-`.
 *
 * @receiver The variable to negate.
 * @return The variable `-x`
 * @author Alejandro Gomez
 * @since 0.0.1
 */
operator fun BoolVar.unaryMinus(): BoolVar = not()
