plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "corp.enterprise"
version = "3.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Spring Data
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.2")
    // GraphQL
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    // Flyway
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    // MySQL
    implementation("com.mysql:mysql-connector-j")
    //	jakarta library - validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("com.graphql-java:graphql-java-extended-scalars:22.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
