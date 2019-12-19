plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.springframework.boot")
  id("com.github.node-gradle.node")
  id("com.github.ben-manes.versions")
  id("io.spring.dependency-management")
}

val projectGroup: String by project
val projectVersion: String by project
val freeCompilerArg: String by project
val projectJvmTarget: String by project
val projectNpmVersion: String by project
val projectNodeVersion: String by project
val projectYarnVersion: String by project
val projectGradleVersion: String by project
val kotlinCoroutinesReactor: String by project

group = projectGroup
version = projectVersion

java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinCoroutinesReactor"))
  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions") // toMono
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-mustache")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("io.projectreactor:reactor-test")
}

tasks {
  withType<Test> {
    useJUnitPlatform()
    testLogging {
      showExceptions = true
      showStandardStreams = true
      events(
          org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
          org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
          org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
      )
    }
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = projectJvmTarget
      freeCompilerArgs = listOf(freeCompilerArg)
    }
  }
  withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    launchScript()
  }
  withType<Wrapper> {
    gradleVersion = projectGradleVersion
  }
  clean {
    delete("$projectDir/src/main/resources/static")
  }
  processResources {
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
  // distBaseUrl = "https://nodejs.org/dist"
  nodeModulesDir = file("$projectDir/ui")
}

sourceSets {
  main {
    java.srcDirs(
        "src/main/java",
        "src/main/kotlin"
    )
  }
  test {
    java.srcDirs(
        "src/test/java",
        "src/test/kotlin"
    )
  }
}

defaultTasks("build")
