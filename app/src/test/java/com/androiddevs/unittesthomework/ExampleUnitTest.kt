package com.androiddevs.unittesthomework

import android.content.SharedPreferences
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.eq

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ExampleUnitTest {
    private val TEST_NAME = "Test name"
    private lateinit var calculator: Calculator
    private var homeWork = Homework
    private lateinit var mainActivity: MainActivity
    private lateinit var mSharedPreferenceEntry: SharedPreferenceEntry
    private lateinit var mMockSharedPreferencesHelper: SharedPreferencesHelper

    @Mock
    private lateinit var mMockSharedPreferences: SharedPreferences
    @Before
    fun setup() {
        calculator = Calculator()
        mainActivity = MainActivity()
        MockitoAnnotations.initMocks(this)
        mSharedPreferenceEntry = SharedPreferenceEntry(
            TEST_NAME
        )
        mMockSharedPreferencesHelper = createMockSharedPreference()

    }

    @Test
    fun plus() {
        val actual = calculator.add(3, 2)
        val expected = 5
        assertEquals(expected, actual)
    }

    @Test
    fun homeLong() {
        val long = homeWork.fib(1)
        assertEquals(1, long)
    }

    /**
     *
     * 1.mMockSharedPreferences에서 getString 메서드가 호출될 때 특정한 키(KEY_NAME)를 받으면 그 키에 대한 값을 반환하도록 설정합니다. 여기서 KEY_NAME은 SharedPreferences에서 사용될 키를 나타냅니다.
     *
     * 2. SharedPreferencesHelper 객체를 생성하고 생성자에 mMockSharedPreferences를 전달하여 SharedPreferences를 사용할 수 있도록 합니다.
     *
     * 3. SharedPreferencesHelper 객체를 반환합니다.
     *
     * Mockito의 when 함수는 목 객체인 mMockSharedPreferences에서 getString 메서드가 특정한 인수를 받았을 때 어떻게 동작해야 하는지를 정의합니다
     */
    private fun createMockSharedPreference(): SharedPreferencesHelper {
        Mockito.`when`(
            mMockSharedPreferences.getString(
                eq(KEY_NAME),
                ArgumentMatchers.anyString()
            )
        )
            .thenReturn(mSharedPreferenceEntry.name)

            return SharedPreferencesHelper(mMockSharedPreferences)
    }
    @Test
    fun homeString() {
        val savedSharedPreferenceEntry = mMockSharedPreferencesHelper.personalInfo

        assertEquals(
            mSharedPreferenceEntry.name,
            savedSharedPreferenceEntry.name)
    }
}