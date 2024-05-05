import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    js(IR) {
        browser {
            runTask {
                mainOutputFileName = "main.bundle.js"
                sourceMaps = false
                devServer = KotlinWebpackConfig.DevServer(
                    open = false,
                    port = 3000,
                    static = mutableListOf("${layout.buildDirectory.asFile.get()}/processedResources/js/main")
                )
            }
            webpackTask {
                mainOutputFileName = "main.bundle.js"
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }


    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
        val jsMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(libs.narbase.kunafa.core)
            }
        }
    }
}



