allprojects {
    version = "0.0.1"
}


plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

tasks.register("buildRelease") {
    val webAppProject = projects.webApp

    val outputJarName = project.name
    val serverProjectName = projects.server.name
    val webProjectName = webAppProject.name
    dependsOn(":$serverProjectName:jar")
    dependsOn(":$webProjectName:assemble")
    doLast {
        println("Building release")
        val releaseDir = File("./releases/$version/")
        val webReleaseDir = File("./releases/$version/web/")
        releaseDir.deleteRecursively()
        releaseDir.mkdirs()
        webReleaseDir.mkdirs()//fixme: Doesn't work!
        File("${webAppProject.dependencyProject.layout.buildDirectory.asFile.get()}/dist/js/productionExecutable").copyRecursively(webReleaseDir, overwrite = true)
        File("${projects.server.dependencyProject.layout.buildDirectory.asFile.get()}/libs/$serverProjectName-$version.jar").copyTo(
            File("${releaseDir.path}/$outputJarName.jar"),
            overwrite = true
        )
    }
}
