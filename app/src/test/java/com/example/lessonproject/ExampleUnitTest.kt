package com.example.lessonproject

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {



    @Test
    fun start() {

      println(myCarCollection.sort())
    }

    private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale("ru"))

    @Test
    fun testDate() {
        val time = 1560507488
        println(getDateString(time)) // 14 June 2019, 13:18:08
    }

    private fun getDateString(time: Long) : String = simpleDateFormat.format(time * 1000L)

    private fun getDateString(time: Int) : String = simpleDateFormat.format(time * 1000L)

}