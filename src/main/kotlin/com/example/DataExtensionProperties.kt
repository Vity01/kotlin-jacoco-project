package com.example

data class DataExtensionsProperties(
    val table: Table,
    val template: Template,
    val clientId: ClientId,
)

data class Table(
    val subscription: String,
    val subscriptionBall: String,
    val log: String,
    val doubleOptIn: String,
)

data class Template(
    val doubleOptIn: DoubleOptIn,
    val appConsent: AppConsent
)

data class ClientId(
    val ball: Int,
    val toy: Int,
    val doll: Int
)

data class DoubleOptIn(
    val toy: String,
    val ball: String,
    val doll: String
)

data class AppConsent(
    val mobile: Map<String, String>,
    val consumer: Map<String, String>
)

fun DataExtensionsProperties.tables() = listOf(table.log, table.subscription, table.subscriptionBall, table.doubleOptIn)