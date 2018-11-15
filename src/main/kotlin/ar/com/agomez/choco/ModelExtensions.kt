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
import org.chocosolver.solver.constraints.nary.alldifferent.conditions.Condition
import org.chocosolver.solver.variables.BoolVar
import org.chocosolver.solver.variables.IntVar
import org.chocosolver.solver.variables.RealVar
import org.chocosolver.solver.variables.Variable

typealias DoubleRange = ClosedFloatingPointRange<Double>

typealias Matrix<T> = Array<out Array<out T>>

typealias Term = Pair<Int, IntVar>

operator fun Term.unaryMinus() = Term(-first, second)

/**
 *  Top level function acting as a Kotlin shortcut allowing to create a [Model] and execute some code in its context.
 *
 * @param name The name of the model.
 * @param block a lambda with the model as receiver.
 * @return The created [Model]
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun model(name: String, block: Model.() -> Unit): Model = Model(name).also(block)

/**
 *  Top level function acting as a Kotlin shortcut allowing to create a [Model] and execute some code in its context.
 *
 * @param T the returning type.
 * @param name The name of the model.
 * @param block a lambda with the model as receiver.
 * @return The result of the `block` execution.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun <T> runWithModel(name: String, block: Model.() -> T): T = Model(name).block()

/**
 * Extension for [Model.intVar] to define an integer variable with values in 0..9 range.
 *
 * @param name The name of the variable.
 * @param boundedDomain Specifies whether to use a bounded domain or an enumerated domain.
 * @return The created integer variable instance.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.digit(name: String, boundedDomain: Boolean = false): IntVar = intVar(name, 0, 9, boundedDomain)

/**
 * Extension for [Model.intVar] to define an integer variable with values in 1..9 range.
 *
 * @param name The name of the variable.
 * @param boundedDomain Specifies whether to use a bounded domain or an enumerated domain.
 * @return The created integer variable instance.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.digitNonZero(name: String, boundedDomain: Boolean = false): IntVar = intVar(name, 1, 9, boundedDomain)

/**
 * Extension for [Model.boolVar] to define a boolean variable with value true.
 *
 * @return The created boolean variable instance.
 * @author Alejandro Gomez
 * @since 0.0.7
 */
fun Model.trueVar(): BoolVar = boolVar(true)

/**
 * Extension for [Model.boolVar] to define a boolean variable with value true.
 *
 * @param name The name of the variable.
 * @return The created boolean variable instance.
 * @author Alejandro Gomez
 * @since 0.0.7
 */
fun Model.trueVar(name: String): BoolVar = boolVar(name, true)

/**
 * Extension for [Model.boolVar] to define a boolean variable with value false.
 *
 * @return The created boolean variable instance.
 * @author Alejandro Gomez
 * @since 0.0.7
 */
fun Model.falseVar(): BoolVar = boolVar(false)

/**
 * Extension for [Model.boolVar] to define a boolean variable with value false.
 *
 * @param name The name of the variable.
 * @return The created boolean variable instance.
 * @author Alejandro Gomez
 * @since 0.0.7
 */
fun Model.falseVar(name: String): BoolVar = boolVar(name, false)

/**
 * Extension for [Model.setObjective] to specify the objective variable to minimize.
 *
 * @param v The objective variable to minimize.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.minimize(v: Variable) = setObjective(false, v)

/**
 * Extension for [Model.setObjective] to specify the objective variable to maximize.
 *
 * @param v The objective variable to maximize.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.maximize(v: Variable) = setObjective(true, v)

/**
 * Extension for [Model.intVar] to declare a variable using an [IntRange].
 *
 * @param values initial domain bounds.
 * @return An IntVar of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVar(values: IntRange): IntVar = intVar(values.first, values.last)

/**
 * Extension for [Model.intVar] to declare a variable using an [IntRange].
 *
 * @param values initial domain bounds.
 * @param boundedDomain specifies whether to use a bounded domain or an enumerated domain.
 * @return An IntVar of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVar(values: IntRange, boundedDomain: Boolean): IntVar = intVar(values.first, values.last, boundedDomain)

/**
 * Extension for [Model.intVar] to declare a variable using an [IntRange].
 *
 * @param name name of the variable.
 * @param values initial domain bounds.
 * @return An IntVar of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVar(name: String, values: IntRange): IntVar = intVar(name, values.first, values.last)

/**
 * Extension for [Model.intVar] to declare a variable using an [IntRange].
 *
 * @param name name of the variable.
 * @param values initial domain bounds.
 * @param boundedDomain specifies whether to use a bounded domain or an enumerated domain.
 * @return An IntVar of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVar(name: String, values: IntRange, boundedDomain: Boolean): IntVar = intVar(name, values.first, values.last, boundedDomain)

/**
 * Extension for [Model.intVarArray] to declare a variable array using an [IntRange].
 *
 * @param size number of variables.
 * @param values initial domain bounds.
 * @return An array of `size` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarArray(size: Int, values: IntRange): Array<out IntVar> = intVarArray(size, values.first, values.last)

/**
 * Extension for [Model.intVarArray] to declare a variable array using an [IntRange].
 *
 * @param size number of variables.
 * @param values initial domain bounds.
 * @param boundedDomain specifies whether to use a bounded domain or an enumerated domain.
 * @return An array of `size` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarArray(size: Int, values: IntRange, boundedDomain: Boolean): Array<out IntVar> = intVarArray(size, values.first, values.last, boundedDomain)

/**
 * Extension for [Model.intVarArray] to declare a variable array using an [IntRange].
 *
 * @param size number of variables.
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @return An array of `size` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarArray(name: String, size: Int, values: IntRange): Array<out IntVar> = intVarArray(name, size, values.first, values.last)

/**
 * Extension for [Model.intVarArray] to declare a variable array using an [IntRange].
 *
 * @param size number of variables.
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @param boundedDomain specifies whether to use a bounded domain or an enumerated domain.
 * @return An array of `size` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarArray(name: String, size: Int, values: IntRange, boundedDomain: Boolean): Array<out IntVar> = intVarArray(name, size, values.first, values.last, boundedDomain)

/**
 * Extension for [Model.intVarMatrix] to declare a variable array using an [IntRange].
 *
 * @param dim1 number of rows in the matrix.
 * @param dim2 number of columns in the matrix.
 * @param values initial domain bounds.
 * @return A matrix of `dim1 * dim2` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarMatrix(dim1: Int, dim2: Int, values: IntRange): Matrix<IntVar> = intVarMatrix(dim1, dim2, values.first, values.last)

/**
 * Extension for [Model.intVarMatrix] to declare a variable array using an [IntRange].
 *
 * @param dim1 number of rows in the matrix.
 * @param dim2 number of columns in the matrix.
 * @param values initial domain bounds.
 * @param boundedDomain specifies whether to use a bounded domain or an enumerated domain.
 * @return A matrix of `dim1 * dim2` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarMatrix(dim1: Int, dim2: Int, values: IntRange, boundedDomain: Boolean): Matrix<IntVar> = intVarMatrix(dim1, dim2, values.first, values.last, boundedDomain)

/**
 * Extension for [Model.intVarMatrix] to declare a variable array using an [IntRange].
 *
 * @param dim1 number of rows in the matrix.
 * @param dim2 number of columns in the matrix.
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @return A matrix of `dim1 * dim2` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarMatrix(name: String, dim1: Int, dim2: Int, values: IntRange): Matrix<IntVar> = intVarMatrix(name, dim1, dim2, values.first, values.last)

/**
 * Extension for [Model.intVarMatrix] to declare a variable array using an [IntRange].
 *
 * @param dim1 number of rows in the matrix.
 * @param dim2 number of columns in the matrix.
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @param boundedDomain specifies whether to use a bounded domain or an enumerated domain.
 * @return A matrix of `dim1 * dim2` [IntVar] of domain `[values.first, values.last]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.intVarMatrix(name: String, dim1: Int, dim2: Int, values: IntRange, boundedDomain: Boolean): Matrix<IntVar> = intVarMatrix(name, dim1, dim2, values.first, values.last, boundedDomain)

/**
 * Extension for [Model.realVar] to declare a variable array using a [DoubleRange].
 *
 * @param values initial domain bounds.
 * @param precision double precision.
 * @return A [RealVar] of domain `[values.start, values.endInclusive]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.realVar(values: DoubleRange, precision: Double): RealVar = realVar(values.start, values.endInclusive, precision)

/**
 * Extension for [Model.realVar] to declare a variable array using a [DoubleRange].
 *
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @param precision double precision.
 * @return A [RealVar] of domain `[values.start, values.endInclusive]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.realVar(name: String, values: DoubleRange, precision: Double): RealVar = realVar(name, values.start, values.endInclusive, precision)

/**
 * Extension for [Model.realVarArray] to declare a variable array using an [DoubleRange].
 *
 * @param size number of variables.
 * @param values initial domain bounds.
 * @return An array of `size` [RealVar] of domain `[values.start, values.endInclusive]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.realVarArray(size: Int, values: DoubleRange, precision: Double): Array<out RealVar> = realVarArray(size, values.start, values.endInclusive, precision)

/**
 * Extension for [Model.realVarArray] to declare a variable array using an [DoubleRange].
 *
 * @param size number of variables.
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @return An array of `size` [RealVar] of domain `[values.start, values.endInclusive]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.realVarArray(name: String, size: Int, values: DoubleRange, precision: Double): Array<out RealVar> = realVarArray(name, size, values.start, values.endInclusive, precision)

/**
 * Extension for [Model.realVarMatrix] to declare a variable array using an [DoubleRange].
 *
 * @param dim1 number of rows in the matrix.
 * @param dim2 number of columns in the matrix.
 * @param values initial domain bounds.
 * @return A matrix of `dim1 * dim2` [RealVar] of domain `[values.start, values.endInclusive]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.realVarMatrix(dim1: Int, dim2: Int, values: DoubleRange, precision: Double): Matrix<RealVar> = realVarMatrix(dim1, dim2, values.start, values.endInclusive, precision)

/**
 * Extension for [Model.realVarMatrix] to declare a variable array using an [DoubleRange].
 *
 * @param dim1 number of rows in the matrix.
 * @param dim2 number of columns in the matrix.
 * @param name name of the variables.
 * @param values initial domain bounds.
 * @return A matrix of `dim1 * dim2` [RealVar] of domain `[values.start, values.endInclusive]`.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.realVarMatrix(name: String, dim1: Int, dim2: Int, values: DoubleRange, precision: Double): Matrix<RealVar> = realVarMatrix(name, dim1, dim2, values.start, values.endInclusive, precision)

/**
 * Extension for [Model.scalar] to declare a scalar constraint using pairs of coefficients and variables.
 * Sample: `scalar(listOf(2 to v, -1 to w, 3 to x, -2 to y), Operator.GE, 10)`
 *
 * @param terms pairs of coefficients and variables.
 * @param operator the operator for the comparison.
 * @param c the rhs of the comparison.
 * @return A scalar constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.scalar(terms: List<Term>, operator: Operator, c: Int): Constraint =
        scalar(terms.map { it.second }.toTypedArray(), terms.map { it.first }.toIntArray(), operator.toString(), c)

/**
 * Extension for [Model.scalar] to declare a scalar constraint using pairs of coefficients and variables.
 * Sample: `scalar(listOf(2 to v, -1 to w, 3 to x, -2 to y), Operator.GE, z)`
 *
 * @param terms pairs of coefficients and variables.
 * @param operator the operator for the comparison.
 * @param v the rhs of the comparison.
 * @return A scalar constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.scalar(terms: List<Term>, operator: Operator, v: IntVar): Constraint =
        scalar(terms.map { it.second }.toTypedArray(), terms.map { it.first }.toIntArray(), operator.toString(), v)

/**
 * Extension for [Model.scalar] to declare a scalar constraint using pairs of coefficients and variables.
 * Sample: `scalar(listOf(2 to v, -1 to w, 3 to x, -2 to y), Operator.GE, 0, 4)`
 *
 * @param terms pairs of coefficients and variables.
 * @param operator the operator for the comparison.
 * @param c the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A scalar constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.scalar(terms: List<Term>, operator: Operator, c: Int, minCardForDecomp: Int): Constraint =
        scalar(terms.map { it.second }.toTypedArray(), terms.map { it.first }.toIntArray(), operator.toString(), c, minCardForDecomp)

/**
 * Extension for [Model.scalar] to declare a scalar constraint using pairs of coefficients and variables.
 * Sample: `scalar(listOf(2 to v, -1 to w, 3 to x, -2 to y), Operator.GE, z, 4)`
 *
 * @param terms pairs of coefficients and variables.
 * @param operator the operator for the comparison.
 * @param v the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A scalar constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.scalar(terms: List<Term>, operator: Operator, v: IntVar, minCardForDecomp: Int): Constraint =
        scalar(terms.map { it.second }.toTypedArray(), terms.map { it.first }.toIntArray(), operator.toString(), v, minCardForDecomp)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out IntVar>, operator: Operator, sum: Int): Constraint = sum(vars, operator.toString(), sum)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.sum(vars: Iterable<IntVar>, operator: Operator, sum: Int): Constraint = sum(vars.asTypedArray(), operator.toString(), sum)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out IntVar>, operator: Operator, sum: Int, minCardForDecomp: Int): Constraint = sum(vars, operator.toString(), sum, minCardForDecomp)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.sum(vars: Iterable<IntVar>, operator: Operator, sum: Int, minCardForDecomp: Int): Constraint = sum(vars.asTypedArray(), operator.toString(), sum, minCardForDecomp)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out IntVar>, operator: Operator, sum: IntVar): Constraint = sum(vars, operator.toString(), sum)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.sum(vars: Iterable<IntVar>, operator: Operator, sum: IntVar): Constraint = sum(vars.asTypedArray(), operator.toString(), sum)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out IntVar>, operator: Operator, sum: IntVar, minCardForDecomp: Int): Constraint = sum(vars, operator.toString(), sum, minCardForDecomp)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.sum(vars: Iterable<IntVar>, operator: Operator, sum: IntVar, minCardForDecomp: Int): Constraint = sum(vars.asTypedArray(), operator.toString(), sum, minCardForDecomp)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out BoolVar>, operator: Operator, sum: Int): Constraint = sum(vars, operator.toString(), sum)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out BoolVar>, operator: Operator, sum: IntVar): Constraint = sum(vars, operator.toString(), sum)

/**
 * Extension for [Model.sum] to declare a sum constraint using the [Operator] enum.
 *
 * @param vars the variables to sum.
 * @param operator the operator for the comparison.
 * @param sum the rhs of the comparison.
 * @param minCardForDecomp minimum number of cardinality threshold to a sum constraint to be decomposed.
 * @return A sum constraint.
 * @author Alejandro Gomez
 * @since 0.0.1
 */
fun Model.sum(vars: Array<out BoolVar>, operator: Operator, sum: IntVar, minCardForDecomp: Int): Constraint = sum(vars, operator.toString(), sum, minCardForDecomp)

/**
 * Extension for [Model.allDifferent] that accepts the variables as [Iterable].
 *
 * @param vars the list of variables.
 * @return An `allDifferent` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.allDifferent(vars: Iterable<IntVar>): Constraint = allDifferent(*vars.asTypedArray())

/**
 * Consistency level for allDifferent constraint.
 */
enum class Consistency {
    AC, BC, FC, NEQS, DEFAULT
}

/**
 * Extension for [Model.allDifferent] that accepts the variables as [Iterable].
 *
 * @param vars the list of variables.
 * @param consistency the consistency.
 * @return An `allDifferent` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.allDifferent(vars: Iterable<IntVar>, consistency: Consistency): Constraint = allDifferent(vars.asTypedArray(), consistency.name)

/**
 * Extension for [Model.allDifferentUnderCondition] that accepts the variables as [Iterable].
 *
 * @param vars the list of variables.
 * @param condition condition defining which variables should be constrained.
 * @param singleCondition specifies how to apply filtering.
 * @return An `allDifferent` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.allDifferentUnderCondition(vars: Iterable<IntVar>, condition: Condition, singleCondition: Boolean): Constraint = allDifferentUnderCondition(vars.asTypedArray(), condition, singleCondition)

/**
 * Extension for [Model.allDifferentExcept0] that accepts the variables as [Iterable].
 *
 * @param vars the list of variables.
 * @return An `allDifferent` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.allDifferentExcept0(vars: Iterable<IntVar>): Constraint = allDifferentExcept0(vars.asTypedArray())

/**
 * Extension for [Model.allEqual] that accepts the variables as [Iterable].
 *
 * @param vars the list of variables.
 * @return An `allEqual` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.allEqual(vars: Iterable<IntVar>): Constraint = allEqual(*vars.asTypedArray())

/**
 * Extension for [Model.notAllEqual] that accepts the variables as [Iterable].
 *
 * @param vars the list of variables.
 * @return An `notAllEqual` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.notAllEqual(vars: Iterable<IntVar>): Constraint = notAllEqual(*vars.asTypedArray())

/**
 * Extension for [Model.among] that accepts the variables as [Iterable].
 *
 * @param nbVar a variable.
 * @param vars the list of variables.
 * @param values the list of values.
 * @return An `allDifferent` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.among(nbVar: IntVar, vars: Iterable<IntVar>, values: IntArray): Constraint = among(nbVar, vars.asTypedArray(), values)

/**
 * Extension for [Model.among] that accepts the variables and the values as [Iterable].
 *
 * @param nbVar a variable.
 * @param vars the list of variables.
 * @param values the list of values.
 * @return An `allDifferent` constraint.
 * @author Alejandro Gomez
 * @since 0.0.3
 */
fun Model.among(nbVar: IntVar, vars: Iterable<IntVar>, values: Iterable<Int>): Constraint = among(nbVar, vars.asTypedArray(), values.asIntArray())

/**
 * Extension for [Model.globalCardinality] that accepts the values as [Iterable].
 *
 * @param vars collection of variables.
 * @param values collection of constrained values.
 * @param occurrences collection of cardinality variables
 * @param closed restricts domains of vars to values if set to true
 * @return A `globalCardinality` constraint.
 * @author Alejandro Gomez
 * @since 0.0.5
 */
fun Model.globalCardinality(vars: Array<out IntVar>, values: Iterable<Int>, occurrences: Array<out IntVar>, closed: Boolean = true): Constraint =
        globalCardinality(vars, values.asIntArray(), occurrences, closed)

/**
 * Extension for [Model.globalCardinality] that accepts the variables and the values as [Iterable].
 *
 * @param vars collection of variables.
 * @param values collection of constrained values.
 * @param occurrences collection of cardinality variables
 * @param closed restricts domains of vars to values if set to true
 * @return A `globalCardinality` constraint.
 * @author Alejandro Gomez
 * @since 0.0.5
 */
fun Model.globalCardinality(vars: Iterable<IntVar>, values: Iterable<Int>, occurrences: Iterable<IntVar>, closed: Boolean = true): Constraint =
        globalCardinality(vars.asTypedArray(), values.asIntArray(), occurrences.asTypedArray(), closed)
