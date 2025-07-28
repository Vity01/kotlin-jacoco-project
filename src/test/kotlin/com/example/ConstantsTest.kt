package com.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class ConstantsTest {

    private lateinit var constants: Constants
    
    @BeforeEach
    fun setUp() {
        constants = Constants()
    }

    // Tests for instance properties and methods
    @Test
    fun `test instance properties are initialized`() {
        assertNotNull(constants.instanceId)
        assertTrue(constants.instanceId.startsWith("CONST_"))
        assertTrue(constants.createdAt > 0)
        assertTrue(constants.createdAt <= System.currentTimeMillis())
    }
    
    @Test
    fun `test getInstanceInfo returns formatted string`() {
        val info = constants.getInstanceInfo()
        assertTrue(info.contains("Instance CONST_"))
        assertTrue(info.contains("created at"))
    }
    
    @Test
    fun `test isOlderThan method`() {
        // Should not be older than 1000ms right after creation
        assertFalse(constants.isOlderThan(1000))
        
        // Should be older than negative time
        assertTrue(constants.isOlderThan(-1))
    }
    
    @Test
    fun `test each instance has unique instanceId`() {
        val constants1 = Constants()
        val constants2 = Constants()
        
        assertNotEquals(constants1.instanceId, constants2.instanceId)
    }

    // Tests for LogLevel enum
    @Test
    fun `test LogLevel enum values and priorities`() {
        assertEquals(0, Constants.LogLevel.DEBUG.priority)
        assertEquals(1, Constants.LogLevel.INFO.priority)
        assertEquals(2, Constants.LogLevel.WARN.priority)
        assertEquals(3, Constants.LogLevel.ERROR.priority)
    }
    
    @Test
    fun `test LogLevel isHigherThan method`() {
        assertTrue(Constants.LogLevel.ERROR.isHigherThan(Constants.LogLevel.WARN))
        assertTrue(Constants.LogLevel.WARN.isHigherThan(Constants.LogLevel.INFO))
        assertTrue(Constants.LogLevel.INFO.isHigherThan(Constants.LogLevel.DEBUG))
        
        assertFalse(Constants.LogLevel.DEBUG.isHigherThan(Constants.LogLevel.INFO))
        assertFalse(Constants.LogLevel.ERROR.isHigherThan(Constants.LogLevel.ERROR))
    }

    // Tests for companion object (static) properties and methods
    @Test
    fun `test app constants`() {
        assertEquals("Kotlin Jacoco Project", Constants.APP_NAME)
        assertEquals("1.0.0", Constants.VERSION)
        assertEquals(5000L, Constants.DEFAULT_TIMEOUT)
    }

    @Test
    fun `test configuration constants`() {
        assertEquals(3, Constants.MAX_RETRY_ATTEMPTS)
        assertEquals(1024, Constants.DEFAULT_BUFFER_SIZE)
    }

    @Test
    fun `test status codes`() {
        assertEquals(200, Constants.SUCCESS_CODE)
        assertEquals(500, Constants.ERROR_CODE)
        assertEquals(404, Constants.NOT_FOUND_CODE)
    }

    @Test
    fun `test default config`() {
        val config = Constants.DEFAULT_CONFIG
        assertEquals(false, config["debug"])
        assertEquals(true, config["verbose"])
        assertEquals(true, config["autoSave"])
        assertEquals(3, config.size)
    }

    @Test
    fun `test getAppInfo function`() {
        val appInfo = Constants.getAppInfo()
        assertEquals("Kotlin Jacoco Project v1.0.0", appInfo)
    }

    @Test
    fun `test isSuccessCode function`() {
        // Test success codes
        assertTrue(Constants.isSuccessCode(200))
        assertTrue(Constants.isSuccessCode(201))
        assertTrue(Constants.isSuccessCode(299))
        
        // Test non-success codes
        assertFalse(Constants.isSuccessCode(199))
        assertFalse(Constants.isSuccessCode(300))
        assertFalse(Constants.isSuccessCode(404))
        assertFalse(Constants.isSuccessCode(500))
    }
}
