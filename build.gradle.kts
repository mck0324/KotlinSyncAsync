plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("io.projectreactor:reactor-core:3.6.2")
    implementation ("ch.qos.logback:logback-classic:1.4.14")
    implementation ("io.github.microutils:kotlin-logging:3.0.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}