package com.felipe.treinofacil

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class WorkoutMockDataTest {

    @Test
    fun findById_returnsTheRightWorkout() {
        val workout = WorkoutMockData.findById("w1")

        assertNotNull(workout)
        assertEquals("Supino reto", workout?.name)
    }

    @Test
    fun findById_returnsNullForUnknownOrNullId() {
        assertNull(WorkoutMockData.findById("nao-existe"))
        assertNull(WorkoutMockData.findById(null))
    }

    @Test
    fun mockIds_areUnique() {
        val ids = WorkoutMockData.workouts.map { it.id }

        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun everyWorkout_hasAtLeastOneSet() {
        assertTrue(WorkoutMockData.workouts.all { it.totalSets > 0 })
    }

    @Test
    fun optionalFields_canBeMissing() {
        val plank = WorkoutMockData.findById("w5")

        assertNull(plank?.description)
        assertNull(plank?.restSeconds)
        assertNull(plank?.tip)
    }
}
