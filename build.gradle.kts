import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version KOTLIN_VERSION
    kotlin("plugin.spring") version KOTLIN_VERSION
    id("org.springframework.boot") version SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version SPRING_DEPENDENCY_MANAGEMENT_VERSION
    id("org.jlleitschuh.gradle.ktlint") version KTLINT_GRADLE_VERSION
}

apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")

tasks.wrapper {
    gradleVersion = "6.6.1"
}

allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("io.kotest:kotest-runner-junit5-jvm:$KOTEST_VERSION")
        testImplementation("io.kotest:kotest-property-jvm:$KOTEST_VERSION")
        testImplementation("io.kotest:kotest-extensions-spring-jvm:$KOTEST_VERSION")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            javaParameters = true
            allWarningsAsErrors = true
            freeCompilerArgs = listOf("-Xjvm-default=all", "-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
