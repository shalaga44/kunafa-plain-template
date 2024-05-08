plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.narbase.narcore"
version = "1.0.0"
application {
    mainClass.set("com.narbase.narcore.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)


    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.jodatime)
    implementation(libs.postgresql)
    implementation(libs.hikariCP)

    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)

    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.gson)

    implementation(libs.ktor.server.websockets)
    implementation(libs.ktor.server.status.pages)

    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.forwarded.header)
    implementation(libs.ktor.server.call.id)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.partial.content)
    implementation(libs.ktor.server.compression)

    implementation(libs.ktor.server.jetty)
    implementation(libs.ktor.client.gson)
    implementation(libs.ktor.server.double.receive)
    implementation(libs.logback.classic)
    implementation(libs.kotlinx.html.jvm)
    implementation(libs.javax.mail)
    implementation(libs.reflections)

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.assertj.core)
    testImplementation(libs.mockk)


}