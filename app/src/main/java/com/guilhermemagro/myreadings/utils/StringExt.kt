package com.guilhermemagro.myreadings.utils

fun String.filterNumbers(): String {
    return this.filter{ it.isDigit() }
}