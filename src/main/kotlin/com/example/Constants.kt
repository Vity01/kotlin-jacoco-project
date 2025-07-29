package com.example

/**
 * Constants class containing application-wide constants
 */
class Constants {

    // Instance properties
    val instanceId: String = generateInstanceId()
    val createdAt: Long = System.currentTimeMillis()


    class InnerClass {
        // Inner class properties
        val innerProperty: String = "Inner Property Value"

        // Inner class method
        fun getInnerInfo(): String {
            return "Inner Class Info: $innerProperty"
        }
    }

    // Instance methods
    fun getInstanceInfo(): String {
        return "Instance $instanceId created at $createdAt"
    }

    fun isOlderThan(milliseconds: Long): Boolean {
        return (System.currentTimeMillis() - createdAt) > milliseconds
    }

    private fun generateInstanceId(): String {
        return "CONST_${System.nanoTime().toString(36).uppercase()}"
    }

    // Instance enum for configuration levels
    enum class LogLevel(val priority: Int) {
        DEBUG(0),
        INFO(1),
        WARN(2),
        ERROR(3);

        fun isHigherThan(other: LogLevel): Boolean {
            return this.priority > other.priority
        }
    }

    companion object {
        // Application constants
        const val APP_NAME = "Kotlin Jacoco Project"
        const val VERSION = "1.0.0"
        const val DEFAULT_TIMEOUT = 5000L

        // Configuration constants
        const val MAX_RETRY_ATTEMPTS = 3
        const val DEFAULT_BUFFER_SIZE = 1024

        // Status codes
        const val SUCCESS_CODE = 200
        const val ERROR_CODE = 500
        const val NOT_FOUND_CODE = 404

        // Default values
        val DEFAULT_CONFIG = mapOf(
            "debug" to false,
            "verbose" to true,
            "autoSave" to true
        )

        /**
         * Returns formatted application info
         */
        fun getAppInfo(): String {
            return "$APP_NAME v$VERSION"
        }

        /**
         * Checks if the given code is a success code
         */
        fun isSuccessCode(code: Int): Boolean {
            return code in 200..299
        }
    }
}

object CreateOptionsFactory {
    fun createCreateOption(saveOption: String) =
        when (saveOption) {
            "auto" -> Constants.DEFAULT_CONFIG["autoSave"] ?: true
            "manual" -> false
            else -> throw IllegalArgumentException("Unknown save option: $saveOption")
        }
}


