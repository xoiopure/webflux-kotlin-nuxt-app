plugins {
  `java-library`
  id("com.github.node-gradle.node")
}

val projectNpmVersion: String by project
val projectNodeVersion: String by project
val projectYarnVersion: String by project

tasks {
  build {
    dependsOn("npm_run_generate")
  }
}

node {
  download = true
  version = projectNodeVersion
  npmVersion = projectNpmVersion
  yarnVersion = projectYarnVersion
  workDir = file("$buildDir/nodejs")
  npmWorkDir = file("$buildDir/npm")
  yarnWorkDir = file("$buildDir/yarn")
  nodeModulesDir = file("$projectDir")
  // distBaseUrl = "https://nodejs.org/dist"
}
