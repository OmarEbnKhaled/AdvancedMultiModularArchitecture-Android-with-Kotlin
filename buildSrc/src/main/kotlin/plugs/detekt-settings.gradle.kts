import io.gitlab.arturbosch.detekt.DetektPlugin

val DETEKT_VERSION = "1.23.3"
apply<DetektPlugin>()

configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
    toolVersion = DETEKT_VERSION
    source.from("src/main/java", "src/main/kotlin")
    parallel = false
    config.from("${rootProject.projectDir}/detekt/detekt-config.yml")
    buildUponDefaultConfig = false
    allRules = false
    baseline = file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-baseline.xml")
    disableDefaultRuleSets = false
    debug = true
    ignoreFailures = false
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("huawei")
    ignoredVariants = listOf("googleRelease")
    basePath = projectDir.absolutePath

}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    include("**/*.kt", "**/*.kts")
    exclude(
        "**/build/**",
        ".*/resources/.*",
        ".*test.*",
        ".*/tmp/.*",
        "**/generated/**"
    )
    reports {
        xml {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-report.xml"))
        }
        html {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-report.html"))
        }
        sarif {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-report.sarif"))
        }
        md {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-report.md"))
        }
        txt {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-report.txt"))
        }
    }
    jvmTarget = JavaVersion.VERSION_1_8.toString()
    dependencies {
        "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${DETEKT_VERSION}")
    }
}

tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    group = "reporting"
    description = "Merges all detekt reports into one report file."
    dependsOn("detekt") // Add this line to declare the dependency
}


// ✅ Register a standalone task to patch the HTML report title
val patchDetektHtmlTitle by tasks.registering {
    group = "reporting"
    description = "Patches the Detekt HTML report title and logo to use the project name."

    doLast {
        val projectTitle = project.name.replaceFirstChar { it.uppercase() }

        val styledTitle = """
            <div style="
                display: inline-block;
                padding: 12px 24px;
                background: linear-gradient(90deg, rgba(105, 33, 224, 1), rgba(247, 44, 67, 1));
                border-radius: 8px;
                margin: 8px 0;
            ">
                <span style="
                    font-family: 'JetBrains Mono', monospace;
                    font-size: 2em;
                    font-weight: 700;
                    color: #ffffff;
                    letter-spacing: 2px;
                ">( $projectTitle ) - Report</span>
            </div>
        """.trimIndent()

        val htmlReport = file("${rootProject.projectDir}/detekt/${project.name}/${project.name}-report.html")
        if (htmlReport.exists()) {
            val content = htmlReport.readText()
            val patched = content
                .replace(Regex("<title>.*?</title>"), "<title>( $projectTitle ) - Report</title>")
                .replace(Regex("<svg[\\s\\S]*?</svg>"), styledTitle)
            htmlReport.writeText(patched)
            println("✅ Patched HTML title and logo to '( $projectTitle ) - Report'")
        } else {
            println("⚠️ HTML report not found: ${htmlReport.absolutePath}")
        }
    }
}


/*
In Gradle with Detekt, the detektBaseline task is used to create or update a baseline file.
 The baseline file is a record of existing issues in the codebase that you want to exclude
  from being reported as new issues in future Detekt runs.
   This is particularly useful when you are introducing Detekt to an existing codebase,
 as it allows you to focus on new issues rather than addressing existing ones immediately
 */
tasks.named("detekt") {
    finalizedBy(patchDetektHtmlTitle)   // <-- runs after detekt, even if detekt fails

    dependsOn("detektBaseline")

    dependsOn(":core:data:detektBaseline")
    dependsOn(":core:domain:detektBaseline")
    dependsOn(":core:presentation:detektBaseline")

    dependsOn(":features:login:detektBaseline")
    dependsOn(":features:home:detektBaseline")
}

tasks.named("preBuild") {
    dependsOn("detekt")
}
