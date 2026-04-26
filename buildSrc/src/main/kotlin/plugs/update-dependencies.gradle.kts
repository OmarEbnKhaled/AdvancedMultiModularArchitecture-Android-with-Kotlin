import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// https://github.com/ben-manes/gradle-versions-plugin/blob/master/README.md
apply<VersionsPlugin>()

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "${project.rootDir}/reports/dependencyUpdates"
    reportfileName = "report"
}