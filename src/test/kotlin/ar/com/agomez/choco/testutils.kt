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
import org.assertj.core.data.Offset
import org.chocosolver.solver.expression.continuous.arithmetic.BiCArExpression
import org.chocosolver.solver.expression.continuous.arithmetic.UnCArExpression
import org.chocosolver.solver.expression.continuous.relational.BiCReExpression
import org.chocosolver.solver.expression.discrete.logical.BiLoExpression
import org.chocosolver.solver.expression.discrete.logical.NaLoExpression
import org.chocosolver.solver.expression.discrete.relational.BiReExpression
import org.chocosolver.solver.expression.discrete.relational.NaReExpression
import org.chocosolver.solver.variables.impl.FixedRealVarImpl
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * @author Alejandro Gomez
 */
inline fun <reified T : Any> getProperty(obj: T, name: String) = T::class.memberProperties
        .first { it.name == name }
        .also { it.isAccessible = true }
        .get(obj)

fun reflectiveAssert(actual: BiReExpression, expected: BiReExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "e1")).isEqualTo(getProperty(expected, "e1"))
    Assertions.assertThat(getProperty(actual, "e2")).isEqualTo(getProperty(expected, "e2"))
}

fun reflectiveAssert(actual: NaReExpression, expected: NaReExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "es")).isEqualTo(getProperty(expected, "es"))
}

fun reflectiveAssert(actual: BiLoExpression, expected: BiLoExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "e1")).isEqualTo(getProperty(expected, "e1"))
    Assertions.assertThat(getProperty(actual, "e2")).isEqualTo(getProperty(expected, "e2"))
}

fun reflectiveAssert(actual: NaLoExpression, expected: NaLoExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "es")).isEqualTo(getProperty(expected, "es"))
}

fun reflectiveAssert(actual: UnCArExpression, expected: UnCArExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "e")).isEqualTo(getProperty(expected, "e"))
}

fun reflectiveAssert(actual: BiCArExpression, expected: BiCArExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "e1")).isEqualTo(getProperty(expected, "e1"))
    val actualE2 = getProperty(actual, "e2")
    val expectedE2 = getProperty(expected, "e2")
    if (actualE2 is FixedRealVarImpl && expectedE2 is FixedRealVarImpl) {
        reflectiveAssert(actualE2, expectedE2)
    } else {
        Assertions.assertThat(actualE2).isEqualTo(expectedE2)
    }
}

fun reflectiveAssert(actual: BiCReExpression, expected: BiCReExpression) {
    Assertions.assertThat(getProperty(actual, "op")).isEqualTo(getProperty(expected, "op"))
    Assertions.assertThat(getProperty(actual, "e1")).isEqualTo(getProperty(expected, "e1"))
    val actualE2 = getProperty(actual, "e2")
    val expectedE2 = getProperty(expected, "e2")
    if (actualE2 is FixedRealVarImpl && expectedE2 is FixedRealVarImpl) {
        reflectiveAssert(actualE2, expectedE2)
    } else {
        Assertions.assertThat(actualE2).isEqualTo(expectedE2)
    }
}

private val offset = Offset.offset(0.001)

fun reflectiveAssert(e1: FixedRealVarImpl, e2: FixedRealVarImpl) {
    Assertions.assertThat(e1.lb).isEqualTo(e2.lb, offset)
    Assertions.assertThat(e1.ub).isEqualTo(e2.ub, offset)
    Assertions.assertThat(e1.precision).isEqualTo(e2.precision, offset)
}
