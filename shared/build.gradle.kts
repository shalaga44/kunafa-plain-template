import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    js(IR) {
        browser {
            runTask(Action {
                mainOutputFileName = "main.bundle.js"
                sourceMaps = false
                devServer = KotlinWebpackConfig.DevServer(
                    open = false,
                    port = 3000,
                    static = mutableListOf("${layout.buildDirectory.asFile.get()}/processedResources/js/main")
                )
            })
            webpackTask(Action {
                mainOutputFileName = "main.bundle.js"
            })
            testTask(Action {
                useKarma {
                    useChromeHeadless()
                }
            })
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.narbase.kunafa.core)
        }
        val jsMain by getting {
            dependencies {
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
    }
}

