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
import org.chocosolver.solver.constraints.Constraint
import org.chocosolver.solver.constraints.Operator
import org.chocosolver.solver.variables.IntVar

sealed class IntVarExpression(val vars: Array<IntVar>, val operator: Operator)

internal class IntVarConstantExpression(vars: Array<IntVar>, operator: Operator, val value: Int) : IntVarExpression(vars, operator)

internal class IntVarVariableExpression(vars: Array<IntVar>, operator: Operator, val value: IntVar) : IntVarExpression(vars, operator)

object IntVarExpressionScope {

    infix fun Array<IntVar>.eq(value: Int): IntVarExpression = IntVarConstantExpression(this, Operator.EQ, value)

    infix fun Iterable<IntVar>.eq(value: Int): IntVarExpression = asTypedArray() eq value

    infix fun Array<IntVar>.eq(value: IntVar): IntVarExpression = IntVarVariableExpression(this, Operator.EQ, value)

    infix fun Iterable<IntVar>.eq(value: IntVar): IntVarExpression = asTypedArray() eq value

    infix fun Array<IntVar>.ne(value: Int): IntVarExpression = IntVarConstantExpression(this, Operator.NQ, value)

    infix fun Iterable<IntVar>.ne(value: Int): IntVarExpression = asTypedArray() ne value

    infix fun Array<IntVar>.ne(value: IntVar): IntVarExpression = IntVarVariableExpression(this, Operator.NQ, value)

    infix fun Iterable<IntVar>.ne(value: IntVar): IntVarExpression = asTypedArray() ne value

    infix fun Array<IntVar>.lt(value: Int): IntVarExpression = IntVarConstantExpression(this, Operator.LT, value)

    infix fun Iterable<IntVar>.lt(value: Int): IntVarExpression = asTypedArray() lt value

    infix fun Array<IntVar>.lt(value: IntVar): IntVarExpression = IntVarVariableExpression(this, Operator.LT, value)

    infix fun Iterable<IntVar>.lt(value: IntVar): IntVarExpression = asTypedArray() lt value

    infix fun Array<IntVar>.le(value: Int): IntVarExpression = IntVarConstantExpression(this, Operator.LE, value)

    infix fun Iterable<IntVar>.le(value: Int): IntVarExpression = asTypedArray() le value

    infix fun Array<IntVar>.le(value: IntVar): IntVarExpression = IntVarVariableExpression(this, Operator.LE, value)

    infix fun Iterable<IntVar>.le(value: IntVar): IntVarExpression = asTypedArray() le value

    infix fun Array<IntVar>.gt(value: Int): IntVarExpression = IntVarConstantExpression(this, Operator.GT, value)

    infix fun Iterable<IntVar>.gt(value: Int): IntVarExpression = asTypedArray() gt value

    infix fun Array<IntVar>.gt(value: IntVar): IntVarExpression = IntVarVariableExpression(this, Operator.GT, value)

    infix fun Iterable<IntVar>.gt(value: IntVar): IntVarExpression = asTypedArray() gt value

    infix fun Array<IntVar>.ge(value: Int): IntVarExpression = IntVarConstantExpression(this, Operator.GE, value)

    infix fun Iterable<IntVar>.ge(value: Int): IntVarExpression = asTypedArray() ge value

    infix fun Array<IntVar>.ge(value: IntVar): IntVarExpression = IntVarVariableExpression(this, Operator.GE, value)

    infix fun Iterable<IntVar>.ge(value: IntVar): IntVarExpression = asTypedArray() ge value

    operator fun IntVar.plus(v: IntVar): List<IntVar> = listOf(this, v)

    operator fun List<IntVar>.plus(v: IntVar): List<IntVar> = ArrayList<IntVar>(size + 1).apply {
        addAll(this@plus)
        add(v)
    }
}

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 * Sample: `sum { listOf(v, w, x, y) ge z }`
 * Sample: `sum { (v + w + x + y) ge z }`
 *
 * @param exprBuilder scoped block where operators are defined as infix functions.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.sum(exprBuilder: IntVarExpressionScope.() -> IntVarExpression): Constraint = IntVarExpressionScope.exprBuilder().let {
    when (it) {
        is IntVarConstantExpression -> sum(it.vars, it.operator.toString(), it.value)
        is IntVarVariableExpression -> sum(it.vars, it.operator.toString(), it.value)
    }
}

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 * Sample: `sum(n) { listOf(v, w, x, y) ge z }`
 * Sample: `sum(n) { (v + w + x + y) ge z }`
 *
 * @param exprBuilder scoped block where operators are defined as infix functions.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.sum(minCardForDecomp: Int, exprBuilder: IntVarExpressionScope.() -> IntVarExpression): Constraint = IntVarExpressionScope.exprBuilder().let {
    when (it) {
        is IntVarConstantExpression -> sum(it.vars, it.operator.toString(), it.value, minCardForDecomp)
        is IntVarVariableExpression -> sum(it.vars, it.operator.toString(), it.value, minCardForDecomp)
    }
}
