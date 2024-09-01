/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.spring.io/milestone")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-web:3.2.2")
    api("org.springframework.boot:spring-boot-starter-webflux:3.2.2")
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.2.2")
    api("org.springframework.boot:spring-boot-starter-data-r2dbc:3.2.2")
    api("io.r2dbc:r2dbc-h2:1.0.0.RELEASE")
    api("org.postgresql:r2dbc-postgresql:1.0.4.RELEASE")
    api("io.r2dbc:r2dbc-spi:1.0.0.RELEASE")
    api("org.postgresql:postgresql:42.5.1")
    api("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:3.2.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
    testImplementation("io.projectreactor:reactor-test:3.6.2")
    compileOnly("javax.servlet:servlet-api:2.5")
}

group = "org.up"
version = "0.0.1-SNAPSHOT"
description = "kotlin-springboot-virtualthread"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
kotlin {
    jvmToolchain(21)
}