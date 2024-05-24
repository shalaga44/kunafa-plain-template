import org.gradle.internal.IoActions
import org.gradle.internal.util.PropertiesUtils
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.charset.Charset
import java.util.*

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.narbase.narcore"
val versionNumber = 1

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
    implementation(libs.ktor.client.apache)
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

tasks.test {
    environment["IS_TEST"] = true
    useJUnitPlatform()
}
application {
    mainClass.set("com.narbase.narcore.main.MainKt")
}
tasks.register("createProperties") {
    doLast {
        val charset = Charset.forName("UTF-8")

        val path = "${projects.server.dependencyProject.layout.buildDirectory.asFile.get()}/resources/main/version.properties"
        File(path).parentFile.mkdirs()
        val fileOutputStream = FileOutputStream(path)
        val out: OutputStream = BufferedOutputStream(fileOutputStream)
        try {
            val propertiesToWrite: Properties = Properties()
            propertiesToWrite["versionName"] = project.version.toString()
            propertiesToWrite["versionNumber"] = versionNumber.toString()
            PropertiesUtils.store(
                propertiesToWrite,
                out,
                "Version and name of project",
                charset,
                System.lineSeparator()
            )
        } finally {
            IoActions.closeQuietly(out)
        }
    }
}

tasks.processResources {
    dependsOn("createProperties")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "com.narbase.narcore.main.MainKt")
        attributes("Class-Path" to ".")
    }

    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    from(configurations.compileClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
}