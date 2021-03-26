package com.example.first_android

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.first_android.data.database.FilmDatabase
import org.junit.After
import org.junit.Before

abstract class BaseTest {
    var myDatabase: FilmDatabase? = null

    @Before
    open fun setUp() {
        this.configureMock()
    }

    @After
    open fun tearDown() {
        this.stopMock()
    }

    open fun configureMock() {
        myDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            FilmDatabase::class.java
        ).build()
    }

    open fun stopMock() {
        myDatabase?.close()
    }
}