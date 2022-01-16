package com.guilhermemagro.myreadings.utils

object BookValidator {
    fun isTotalPagesGreaterOrEqualsCurrentPage(totalPages: Int, currentPage: Int): Boolean {
        return totalPages >= currentPage
    }
}