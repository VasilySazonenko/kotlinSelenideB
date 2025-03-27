plugins {
    kotlin("jvm") version "2.1.20" // Make sure to use the correct Kotlin version
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Selenide for UI testing
    implementation("com.codeborne:selenide:7.7.3")

    // Explicitly declare JUnit 5 dependencies
    testImplementation(platform("org.junit:junit-bom:5.10.0"))  // Ensures compatibility
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // Explicit runtime dependency

    // Selenide WebDriver Manager (Optional but recommended for automatic WebDriver management)
    implementation("io.github.bonigarcia:webdrivermanager:6.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform(){
    includeTags("shop") // You can specify the tag to include
    }
}