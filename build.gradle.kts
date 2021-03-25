plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("io.micronaut.library")
}

allprojects {
    version = "0.1.0"
    group = "com.asarkar.seshat"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "io.micronaut.library")

    java {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
        runtimeOnly("ch.qos.logback:logback-classic")
        testImplementation("org.assertj:assertj-core")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        kapt("io.micronaut:micronaut-inject-java")
        kaptTest("io.micronaut:micronaut-inject-java")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    val micronautVersion: String by project
    micronaut {
//    runtime("netty")
        testRuntime("junit5")
        version.set(micronautVersion)
    }
}
