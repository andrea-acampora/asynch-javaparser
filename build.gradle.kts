/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {

    implementation("io.vertx:vertx-core:4.2.6")
    implementation("io.vertx:vertx-web:4.2.6")
    implementation("io.vertx:vertx-web-client:4.2.6")
    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.24.2")

    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")
}

application {
    // Define the main class for the application.
    mainClass.set("pcd02.app.TestJavaParser")
}
