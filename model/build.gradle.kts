val jaxb: Configuration by configurations.creating

val schemaDir = "src/main/resources"
val xjcOutputDir = "$buildDir/generated/source/xjc/main"
val jaxbVersion: String by project

dependencies {
    jaxb("org.glassfish.jaxb:jaxb-xjc:$jaxbVersion")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:$jaxbVersion")
    runtimeOnly("org.glassfish.jaxb:jaxb-runtime:$jaxbVersion")
}

val createXjcOutputDir by tasks.register("createXjcOutputDir") {
    doLast {
        mkdir(xjcOutputDir)
    }
}

fun createXjcTask(schemaFile: String, pkg: String): TaskProvider<JavaExec> {
    val xjc = tasks.register<JavaExec>("xjc-${file(schemaFile).nameWithoutExtension}") {
        inputs.dir(schemaDir)
        outputs.dir(xjcOutputDir)
        dependsOn(createXjcOutputDir)
        classpath = jaxb
        mainClass.set("com.sun.tools.xjc.XJCFacade")
        args = listOf(
            "-d",
            xjcOutputDir,
            "-p",
            pkg,
            "-encoding",
            "UTF-8",
            "-no-header",
            "-quiet",
            "$schemaDir/$schemaFile"
        )
    }
    tasks.withType<JavaCompile>().configureEach {
        dependsOn(xjc)
    }
    return xjc
}

val jenkinsJunit4Xjc = createXjcTask("jenkins-junit4.xsd", "com.asarkar.seshat.model.junit4")

sourceSets {
    main {
        java {
            srcDirs(file(xjcOutputDir))
        }
    }
}
