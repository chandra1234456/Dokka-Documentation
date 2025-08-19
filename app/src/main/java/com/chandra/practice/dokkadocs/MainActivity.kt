package com.chandra.practice.dokkadocs


/**
 * https://kotlinlang.org/docs/dokka-gradle.html#multi-project-configuration
 * @author balachandra
 * @since 18-08-2024
 */
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
/**
 * Main Activity of the app.
 *
 * @constructor Creates an empty MainActivity
 */
class MainActivity : AppCompatActivity() {
    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState - If the activity is being re-initialized
     * after previously being shut down then this Bundle contains the data.
     */
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    /**
     * Adds two integers.
     *
     * @param a first number
     * @param b second number
     * @return the sum of [a] and [b]
     */
    fun addNumbers(a: Int, b: Int): Int {
        return a + b
    }
    /**
     * Called when the system is about to put the activity into the background.
     *
     * This override adds a debug log for tracking lifecycle events.
     */

    override fun onPause() {
        super.onPause()
        Log.d("TAG" , "onPause: onPause")
    }

}