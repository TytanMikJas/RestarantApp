package com.example.androidapp.Utils

fun lerp(from: Float, to: Float, factor: Float): Float {
    return from.plus(to.minus(from)).times(factor)
}