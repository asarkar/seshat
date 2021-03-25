plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}

val micronautDataVersion: String by project
dependencies {
    kapt("io.micronaut.data:micronaut-data-processor:$micronautDataVersion")
    implementation("io.micronaut:micronaut-runtime")
    implementation(platform("io.micronaut.data:micronaut-data-bom:$micronautDataVersion"))
    kapt(platform("io.micronaut.data:micronaut-data-bom:$micronautDataVersion"))
    kapt("io.micronaut:micronaut-validation")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut:micronaut-validation")
    runtimeOnly("com.h2database:h2")
}
