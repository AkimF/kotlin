/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.js

/**
 * Exposes the JavaScript [Math object](https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Global_Objects/Math) to Kotlin.
 */
@PublishedApi
internal external object Math {
    internal val PI: Double
    internal fun random(): Double
    @PublishedApi
    internal fun abs(value: Double): Double
    @PublishedApi
    internal fun acos(value: Double): Double
    @PublishedApi
    internal fun asin(value: Double): Double
    @PublishedApi
    internal fun atan(value: Double): Double
    @PublishedApi
    internal fun atan2(y: Double, x: Double): Double
    @PublishedApi
    internal fun cos(value: Double): Double
    @PublishedApi
    internal fun sin(value: Double): Double
    @PublishedApi
    internal fun exp(value: Double): Double
    @PublishedApi
    internal fun max(vararg values: Int): Int
    @PublishedApi
    internal fun max(vararg values: Float): Float
    @PublishedApi
    internal fun max(vararg values: Double): Double
    @PublishedApi
    internal fun min(vararg values: Int): Int
    @PublishedApi
    internal fun min(vararg values: Float): Float
    @PublishedApi
    internal fun min(vararg values: Double): Double
    @PublishedApi
    internal fun sqrt(value: Double): Double
    @PublishedApi
    internal fun tan(value: Double): Double
    @PublishedApi
    internal fun log(value: Double): Double
    @PublishedApi
    internal fun pow(base: Double, exp: Double): Double
    internal fun round(value: Number): Int
    @PublishedApi
    internal fun floor(value: Number): Int
    @PublishedApi
    internal fun ceil(value: Number): Int

    @PublishedApi
    internal fun trunc(value: Number): Double
    @PublishedApi
    internal fun sign(value: Number): Double

    @PublishedApi
    internal fun sinh(value: Double): Double
    @PublishedApi
    internal fun cosh(value: Double): Double
    @PublishedApi
    internal fun tanh(value: Double): Double
    @PublishedApi
    internal fun asinh(value: Double): Double
    @PublishedApi
    internal fun acosh(value: Double): Double
    @PublishedApi
    internal fun atanh(value: Double): Double

    @PublishedApi
    internal fun hypot(x: Double, y: Double): Double

    @PublishedApi
    internal fun expm1(value: Double): Double

    @PublishedApi
    internal fun log10(value: Double): Double
    @PublishedApi
    internal fun log2(value: Double): Double
    @PublishedApi
    internal fun log1p(value: Double): Double

    @PublishedApi
    internal fun clz32(value: Int): Int
}

/**
 * Returns the smaller of two values.
 */
internal fun Math.min(a: Long, b: Long): Long = if (a <= b) a else b

/**
 * Returns the greater of two values.
 */
internal fun Math.max(a: Long, b: Long): Long = if (a >= b) a else b
