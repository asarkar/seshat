pluginManagement {
    val kotlinVersion: String by settings
    val micronautPluginVersion: String by settings
    val shadowPluginVersion: String by settings
    val ktlintPluginVersion: String by settings
    val dokkaPluginVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        `maven-publish`
        id("org.jetbrains.kotlin.kapt") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
        id("io.micronaut.library") version micronautPluginVersion
//        id("io.micronaut.application") version micronautVersion
        id("com.github.johnrengelman.shadow") version shadowPluginVersion
        id("org.jlleitschuh.gradle.ktlint") version ktlintPluginVersion
        id("org.jetbrains.dokka") version dokkaPluginVersion
    }
}

rootProject.name="seshat"
include("model", "persistence")
