plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}

val micronautDataVersion: String by project
dependencies {
    kapt("io.micronaut.data:micronaut-data-processor:$micronautDataVersion")
    implementation(platform("io.micronaut.data:micronaut-data-bom:$micronautDataVersion"))
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    runtimeOnly("io.micronaut.sql:micronaut-jdbc-hikari")
    runtimeOnly("com.h2database:h2")
}
