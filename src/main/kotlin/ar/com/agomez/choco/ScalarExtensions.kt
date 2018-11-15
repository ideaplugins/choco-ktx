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

sealed class TermExpression(val terms: Array<Term>, val operator: Operator)

internal class TermConstantExpression(terms: Array<Term>, operator: Operator, val value: Int) : TermExpression(terms, operator)

internal class TermVariableExpression(terms: Array<Term>, operator: Operator, val value: IntVar) : TermExpression(terms, operator)

object TermExpressionScope {

    infix fun Array<Term>.eq(value: Int): TermExpression = TermConstantExpression(this, Operator.EQ, value)

    infix fun List<Term>.eq(value: Int): TermExpression = asTypedArray() eq value

    infix fun Array<Term>.eq(value: IntVar): TermExpression = TermVariableExpression(this, Operator.EQ, value)

    infix fun List<Term>.eq(value: IntVar): TermExpression = asTypedArray() eq value

    infix fun Array<Term>.ne(value: Int): TermExpression = TermConstantExpression(this, Operator.NQ, value)

    infix fun List<Term>.ne(value: Int): TermExpression = asTypedArray() ne value

    infix fun Array<Term>.ne(value: IntVar): TermExpression = TermVariableExpression(this, Operator.NQ, value)

    infix fun List<Term>.ne(value: IntVar): TermExpression = asTypedArray() ne value

    infix fun Array<Term>.lt(value: Int): TermExpression = TermConstantExpression(this, Operator.LT, value)

    infix fun List<Term>.lt(value: Int): TermExpression = asTypedArray() lt value

    infix fun Array<Term>.lt(value: IntVar): TermExpression = TermVariableExpression(this, Operator.LT, value)

    infix fun List<Term>.lt(value: IntVar): TermExpression = asTypedArray() lt value

    infix fun Array<Term>.le(value: Int): TermExpression = TermConstantExpression(this, Operator.LE, value)

    infix fun List<Term>.le(value: Int): TermExpression = asTypedArray() le value

    infix fun Array<Term>.le(value: IntVar): TermExpression = TermVariableExpression(this, Operator.LE, value)

    infix fun List<Term>.le(value: IntVar): TermExpression = asTypedArray() le value

    infix fun Array<Term>.gt(value: Int): TermExpression = TermConstantExpression(this, Operator.GT, value)

    infix fun List<Term>.gt(value: Int): TermExpression = asTypedArray() gt value

    infix fun Array<Term>.gt(value: IntVar): TermExpression = TermVariableExpression(this, Operator.GT, value)

    infix fun List<Term>.gt(value: IntVar): TermExpression = asTypedArray() gt value

    infix fun Array<Term>.ge(value: Int): TermExpression = TermConstantExpression(this, Operator.GE, value)

    infix fun List<Term>.ge(value: Int): TermExpression = asTypedArray() ge value

    infix fun Array<Term>.ge(value: IntVar): TermExpression = TermVariableExpression(this, Operator.GE, value)

    infix fun List<Term>.ge(value: IntVar): TermExpression = asTypedArray() ge value

    operator fun IntVar.unaryMinus(): Term = -1 to this

    operator fun Int.times(v: IntVar): Term = this to v

    operator fun Term.plus(expr: Term): List<Term> = listOf(this, expr)

    operator fun Term.plus(expr: IntVar): List<Term> = listOf(this, 1 * expr)

    operator fun Term.minus(expr: Term): List<Term> = listOf(this, -expr)

    operator fun Term.minus(expr: IntVar): List<Term> = listOf(this, -expr)

    operator fun List<Term>.plus(expr: IntVar): List<Term> = this + 1 * expr

    operator fun List<Term>.minus(expr: IntVar): List<Term> = this + -expr

    operator fun List<Term>.minus(expr: Term): List<Term> = this + -expr
}

/**
 * Extension for [Model.scalar] to declare a scalar constraint using arithmetic expressions.
 * Sample: `scalar { 2 * v -w + 3 * x -2 * y) ge z }`
 *
 * @param exprBuilder scoped block where operators are defined as infix functions.
 * @return A scalar constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.scalar(exprBuilder: TermExpressionScope.() -> TermExpression): Constraint = TermExpressionScope.exprBuilder().let { expr ->
    when (expr) {
        is TermConstantExpression -> scalar(expr.terms.map { it.second }.toTypedArray(), expr.terms.map { it.first }.toIntArray(), expr.operator.toString(), expr.value)
        is TermVariableExpression -> scalar(expr.terms.map { it.second }.toTypedArray(), expr.terms.map { it.first }.toIntArray(), expr.operator.toString(), expr.value)
    }
}

/**
 * Extension for [Model.scalar] to declare a sum constraint using arithmetic expressions.
 * Sample: `scalar(n) { 2 * v -w + 3 * x -2 * y) ge z }`
 *
 * @param exprBuilder scoped block where operators are defined as infix functions.
 * @param minCardForDecomp minimum number of cardinality threshold to a scalar constraint to be decomposed.
 * @return A scalar constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.scalar(minCardForDecomp: Int, exprBuilder: TermExpressionScope.() -> TermExpression): Constraint = TermExpressionScope.exprBuilder().let { expr ->
    when (expr) {
        is TermConstantExpression -> scalar(expr.terms.map { it.second }.toTypedArray(), expr.terms.map { it.first }.toIntArray(), expr.operator.toString(), expr.value, minCardForDecomp)
        is TermVariableExpression -> scalar(expr.terms.map { it.second }.toTypedArray(), expr.terms.map { it.first }.toIntArray(), expr.operator.toString(), expr.value, minCardForDecomp)
    }
}
