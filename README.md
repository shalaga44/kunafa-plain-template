This is a Kotlin Multiplatform project targeting Web: Kotlin/Js and Server: JVM.

* `/webApp` is for code that will be shared across your Js Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Kotlin/Js](https://kotlinlang.org/docs/js-overview.html)…


You can open the web application by running the `:webApp:jsBrowserDevelopmentRun` Gradle task.
