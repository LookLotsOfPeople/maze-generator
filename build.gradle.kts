import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
	kotlin("jvm") version "1.3.11"
}

group = "com.beyondbell"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}