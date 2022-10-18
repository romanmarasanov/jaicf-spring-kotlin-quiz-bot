plugins {
    application
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.justai.jaicf.jaicp-build-plugin") version "0.1.1"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "1.2.4"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.just-ai.jaicf:telegram:$jaicf")
    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    bootJar {
        archiveFileName.set("app.jar")
        mainClass.set("com.justai.jaicf.spring.Application")
    }
}

tasks.create("stage") {
    dependsOn("bootJar")
}