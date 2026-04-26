import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

apply<DokkaPlugin>()

tasks {
    register<DokkaTask>("cDokkaHtml") {
        outputDirectory.set(file("${project.rootDir}/docs/${project.name}"))
        suppressObviousFunctions.set(false)
        suppressInheritedMembers.set(true)
        offlineMode.set(false)

        dokkaSourceSets {
            val projectTitle = project.name.replaceFirstChar { it.uppercase() }
            moduleName.set("$projectTitle - Module")

            // Detect whether this module uses kotlin or java source dir
            val mainSrcDir = when {
                file("src/main/kotlin").exists() -> file("src/main/kotlin")
                file("src/main/java").exists()   -> file("src/main/java")
                else -> null
            }

            val testSrcDir = when {
                file("src/test/kotlin").exists() -> file("src/test/kotlin")
                file("src/test/java").exists()   -> file("src/test/java")
                else -> null
            }

            named("main") {
                displayName.set("main")
                platform.set(org.jetbrains.dokka.Platform.jvm)

                if (mainSrcDir != null) sourceRoots.from(mainSrcDir)

                suppress.set(false)
                includeNonPublic.set(false)
                skipDeprecated.set(false)
                reportUndocumented.set(true)
                skipEmptyPackages.set(true)
                suppressGeneratedFiles.set(true)

                if (mainSrcDir != null) {
                    sourceLink {
                        localDirectory.set(mainSrcDir)
                        remoteUrl.set(
                            java.net.URL("https://github.com/OmarEbnKhaled/AdvancedMultiModularArchitecture-Android-with-Kotlin")
                        )
                        remoteLineSuffix.set("#L")
                    }
                }

                jdkVersion.set(8)
                noStdlibLink.set(false)
                noJdkLink.set(false)
                noAndroidSdkLink.set(false)

                perPackageOption {
                    matchingRegex.set("kotlin($|\\.).*")
                    skipDeprecated.set(false)
                    reportUndocumented.set(true)
                    includeNonPublic.set(false)
                }
                perPackageOption {
                    matchingRegex.set(""".*\.internal.*""")
                    suppress.set(true)
                }
            }

            named("test") {
                displayName.set("test")
                platform.set(org.jetbrains.dokka.Platform.jvm)

                if (testSrcDir != null) sourceRoots.from(testSrcDir)

                suppress.set(false)
                includeNonPublic.set(true)
                skipEmptyPackages.set(true)
                suppressGeneratedFiles.set(true)
                dependsOn("main")
            }
        }
    }
}