package com.example.lessonproject

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val toyota = CarClass("Toyota", "Crown",220)
    private val nissan = CarClass("Nissan", "Fuga",210)
    private val porsche = CarClass("Porsche", "911",320)

    private var myCarCollection = listOf(toyota, nissan, porsche)


    @Test
    fun start() {

        println(myCarCollection.sort())
    }

    private fun List<CarClass>.sort(): List<CarClass> = this.sortedBy { it.speed }
}