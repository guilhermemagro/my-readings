package com.guilhermemagro.myreadings.utils

fun String.filterNumbers(): String {
    return this.filter{ it.isDigit() }
}

fun String.trimStartAndEnd(): String {
    return this.trimStart().trimEnd()
}