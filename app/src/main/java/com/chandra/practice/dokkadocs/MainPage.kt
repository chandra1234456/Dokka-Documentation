package com.chandra.practice.dokkadocs

import java.security.MessageDigest
import java.util.UUID


/**
 * This Starting Page For Arthematic Operations
 *
 */
fun main(){
    println("Hello")
    println(multiply(2,2))
    println(generateRandomUUID())
}

/**
 * Multiplies two integers and returns the result
 *
 * @param value1
 * @param value2
 * @return Int
 */
fun multiply(value1 :Int,value2 :Int): Int {
    return value1 * value2

}

/**
 * Generates a random universally unique identifier (UUID).
 * @return A newly generated [UUID] value, for example:
 * `ed49c7d9-8f90-424f-8a46-6d8be3fb3678`.
 */

fun generateRandomUUID() : UUID = UUID.randomUUID()


/**
 * Generates the **MD5 hash** of the given input string.
 *
 * ⚠️ **Note:** MD5 is not cryptographically secure.
 * Use SHA-256 for security-sensitive use cases (e.g., passwords).
 *
 * @param input The text to be hashed.
 * @return The 32-character hexadecimal MD5 hash string.
 */
fun generateMD5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(input.toByteArray(Charsets.UTF_8))
    return digest.joinToString("") { "%02x".format(it) }
}
