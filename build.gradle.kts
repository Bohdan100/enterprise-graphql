plugins {
    id("org.springframework.boot") version "4.0.2"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.spring") version "2.3.0"
}

group = "corp.enterprise"
version = "4.0.2"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Spring Data
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // GraphQL
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    // Flyway
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-mysql")
    // MySQL
    implementation("com.mysql:mysql-connector-j")
    //	jakarta library - validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("com.graphql-java:graphql-java-extended-scalars:24.0")
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
