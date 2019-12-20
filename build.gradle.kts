plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.springframework.boot")
  id("com.github.ben-manes.versions")
  id("io.spring.dependency-management")
  id("com.bmuschko.docker-spring-boot-application")
}

val projectGroup: String by project
val projectVersion: String by project
val freeCompilerArg: String by project
val projectJvmTarget: String by project
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

if ("" == project.findProperty("docker") ?: "nope") defaultTasks("dockerBuildImage")
else defaultTasks("build")

docker {
  springBootApplication {
    jvmArgs.set(listOf("-Xms64m", "-Xmx2048m", "-Djava.net.preferIPv4Stack=true", "-XX:+UnlockExperimentalVMOptions",
        "-XX:+UseCGroupMemoryLimitForHeap", "-XshowSettings:vm", "-Dspring.devtools.add-properties=false"))
    images.set(listOf("daggerok/${project.name}:latest", "daggerok/${project.name}:${project.version}"))
    maintainer.set("Maksim Kostromin https://github.com/daggerok")
    baseImage.set("adoptopenjdk:8u232-b09-jre-hotspot-bionic")
    ports.set(listOf(8080))
  }
}

tasks {
  register<Exec>("dockerRun") {
    //// 0:
    //executable = "docker"
    //args("run", "--rm", "-i", "-p", "8080:8080", "--name", "run-${project.name}-${project.version}", "daggerok/${project.name}:${project.version}")
    //// 1:
    // executable = "bash"
    // args("-c", "docker run --rm -i -p 8080:8080 --name run-${project.name}-${project.version} daggerok/${project.name}:${project.version}")
    //// 2:
    //commandLine("bash", "-c", "docker run --rm -i -p 8080:8080 --name run-${project.name}-${project.version} daggerok/${project.name}:${project.version}")
    commandLine("docker", "run", "--rm", "-i", "-p", "8080:8080", "--name", "run-${project.name}-${project.version}", "daggerok/${project.name}:${project.version}")
  }
  register<Exec>("dockerRm") {
    commandLine("bash", "-c", "docker rm -v -f run-${project.name}-${project.version} || echo nothing to remove")
  }
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
    dependsOn(":ui:build")
  }
}
