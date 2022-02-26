package com.guilhermemagro.myreadings.data.wrappers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DateWrapperTest {

    private lateinit var dateWrapper: DateWrapper
    private val date = "2022-02-26"

    @Before
    fun setUp() {
        dateWrapper = DateWrapper.from(date)
    }

    @Test
    fun `WHEN getDate called THEN return the date formatted as yyyy-MM-dd`() {
        assertEquals(date, dateWrapper.getDate())
    }

    @Test
    fun `WHEN getDay called THEN return the three initial letters of the day`() {
        val dayLetters = "SÁB"
        assertEquals(dayLetters, dateWrapper.getDay())
    }

    @Test
    fun `WHEN getDayAndMonthDDMM called THEN return the date formatted as ddMM`() {
        val formattedDate = "26/02"
        assertEquals(formattedDate, dateWrapper.getDayAndMonthDDMM())
    }

    @Test
    fun `WHEN checking that two objects with the same date are equal THEN return true`() {
        val secondDateWrapper = DateWrapper.from(date)
        assertTrue(dateWrapper == secondDateWrapper)
    }

    @Test
    fun `WHEN checking that two objects with the different date are equal THEN return false`() {
        val secondDateWrapper = DateWrapper.from("2022-02-01")
        assertFalse(dateWrapper == secondDateWrapper)
    }
}
