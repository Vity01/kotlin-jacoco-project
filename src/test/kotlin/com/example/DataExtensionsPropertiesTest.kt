package com.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class DataExtensionsPropertiesTest {

    private lateinit var testDataExtensionsProperties: DataExtensionsProperties
    private lateinit var testTable: Table
    private lateinit var testTemplate: Template
    private lateinit var testClientId: ClientId
    private lateinit var testDoubleOptIn: DoubleOptIn
    private lateinit var testAppConsent: AppConsent

    @BeforeEach
    fun setUp() {
        testTable = Table(
            subscription = "subscription_table",
            subscriptionBall = "subscription_ball_table",
            log = "log_table",
            doubleOptIn = "double_opt_in_table"
        )

        testDoubleOptIn = DoubleOptIn(
            toy = "toy_double_opt_in",
            ball = "ball_double_opt_in",
            doll = "doll_double_opt_in"
        )

        testAppConsent = AppConsent(
            mobile = mapOf("mobile_key1" to "mobile_value1", "mobile_key2" to "mobile_value2"),
            consumer = mapOf("consumer_key" to "consumer_value", "another_key" to "another_value")
        )

        testTemplate = Template(
            doubleOptIn = testDoubleOptIn,
            appConsent = testAppConsent
        )

        testClientId = ClientId(
            ball = 100,
            toy = 200,
            doll = 300
        )

        testDataExtensionsProperties = DataExtensionsProperties(
            table = testTable,
            template = testTemplate,
            clientId = testClientId
        )
    }

    @Test
    fun `test Table data class creation and properties`() {
        assertEquals("subscription_table", testTable.subscription)
        assertEquals("subscription_ball_table", testTable.subscriptionBall)
        assertEquals("log_table", testTable.log)
        assertEquals("double_opt_in_table", testTable.doubleOptIn)
    }

    @Test
    fun `test ClientId data class creation and properties`() {
        assertEquals(100, testClientId.ball)
        assertEquals(200, testClientId.toy)
        assertEquals(300, testClientId.doll)
    }

    @Test
    fun `test DoubleOptIn data class creation and properties`() {
        assertEquals("toy_double_opt_in", testDoubleOptIn.toy)
        assertEquals("ball_double_opt_in", testDoubleOptIn.ball)
        assertEquals("doll_double_opt_in", testDoubleOptIn.doll)
    }

    @Test
    fun `test AppConsent data class creation and properties`() {
        assertEquals(2, testAppConsent.mobile.size)
        assertEquals("mobile_value1", testAppConsent.mobile["mobile_key1"])
        assertEquals("mobile_value2", testAppConsent.mobile["mobile_key2"])

        assertEquals(2, testAppConsent.consumer.size)
        assertEquals("consumer_value", testAppConsent.consumer["consumer_key"])
        assertEquals("another_value", testAppConsent.consumer["another_key"])
    }

    @Test
    fun `test Template data class creation and properties`() {
        assertEquals(testDoubleOptIn, testTemplate.doubleOptIn)
        assertEquals(testAppConsent, testTemplate.appConsent)
    }

    @Test
    fun `test DataExtensionsProperties data class creation and properties`() {
        assertEquals(testTable, testDataExtensionsProperties.table)
        assertEquals(testTemplate, testDataExtensionsProperties.template)
        assertEquals(testClientId, testDataExtensionsProperties.clientId)
    }

    @Test
    fun `test tables extension function returns correct list`() {
        val expectedTables = listOf(
            "log_table",
            "subscription_table",
            "subscription_ball_table",
            "double_opt_in_table"
        )

        val actualTables = testDataExtensionsProperties.tables()

        assertEquals(4, actualTables.size)
        assertEquals(expectedTables, actualTables)
    }

    @Test
    fun `test tables extension function with different data`() {
        val differentTable = Table(
            subscription = "sub_test",
            subscriptionBall = "sub_ball_test",
            log = "log_test",
            doubleOptIn = "doi_test"
        )

        val differentProps = testDataExtensionsProperties.copy(table = differentTable)
        val tables = differentProps.tables()

        assertEquals(listOf("log_test", "sub_test", "sub_ball_test", "doi_test"), tables)
    }

    @Test
    fun `test data class equality and copy functionality`() {
        val copy1 = testDataExtensionsProperties.copy()
        val copy2 = testDataExtensionsProperties.copy(
            clientId = testClientId.copy(ball = 999)
        )

        assertEquals(testDataExtensionsProperties, copy1)
        assertNotEquals(testDataExtensionsProperties, copy2)
        assertEquals(999, copy2.clientId.ball)
    }

    @Test
    fun `test AppConsent with empty maps`() {
        val emptyAppConsent = AppConsent(
            mobile = emptyMap(),
            consumer = emptyMap()
        )

        assertTrue(emptyAppConsent.mobile.isEmpty())
        assertTrue(emptyAppConsent.consumer.isEmpty())
    }

    @Test
    fun `test ClientId with zero values`() {
        val zeroClientId = ClientId(
            ball = 0,
            toy = 0,
            doll = 0
        )

        assertEquals(0, zeroClientId.ball)
        assertEquals(0, zeroClientId.toy)
        assertEquals(0, zeroClientId.doll)
    }

    @Test
    fun `test DoubleOptIn with empty strings`() {
        val emptyDoubleOptIn = DoubleOptIn(
            toy = "",
            ball = "",
            doll = ""
        )

        assertTrue(emptyDoubleOptIn.toy.isEmpty())
        assertTrue(emptyDoubleOptIn.ball.isEmpty())
        assertTrue(emptyDoubleOptIn.doll.isEmpty())
    }

    @Test
    fun `test toString methods work correctly`() {
        assertNotNull(testDataExtensionsProperties.toString())
        assertNotNull(testTable.toString())
        assertNotNull(testTemplate.toString())
        assertNotNull(testClientId.toString())
        assertNotNull(testDoubleOptIn.toString())
        assertNotNull(testAppConsent.toString())
    }

    @Test
    fun `test hashCode consistency`() {
        val copy = testDataExtensionsProperties.copy()
        assertEquals(testDataExtensionsProperties.hashCode(), copy.hashCode())

        val modified = testDataExtensionsProperties.copy(
            clientId = testClientId.copy(toy = 999)
        )
        assertNotEquals(testDataExtensionsProperties.hashCode(), modified.hashCode())
    }

    @Test
    fun `test component functions for data classes`() {
        val (table, template, clientId) = testDataExtensionsProperties
        assertEquals(testTable, table)
        assertEquals(testTemplate, template)
        assertEquals(testClientId, clientId)

        val (ball, toy, doll) = testClientId
        assertEquals(100, ball)
        assertEquals(200, toy)
        assertEquals(300, doll)
    }
}