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
import org.chocosolver.solver.variables.BoolVar
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class BoolVarExtensionsTest {

    private lateinit var model: Model
    private lateinit var trueVar: BoolVar
    private lateinit var falseVar: BoolVar

    @BeforeEach
    internal fun setUp() {
        model = Model()
        trueVar = model.boolVar(true)
        falseVar = model.boolVar(false)
    }

    @Test
    fun testNegateTrueVar() {
        val negated = -trueVar
        Assertions.assertThat(trueVar.value).isEqualTo(1)
        Assertions.assertThat(negated.value).isEqualTo(0)
    }

    @Test
    fun testNegateFalseVar() {
        val negated = -falseVar
        Assertions.assertThat(falseVar.value).isEqualTo(0)
        Assertions.assertThat(negated.value).isEqualTo(1)
    }
}
