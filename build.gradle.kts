import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.31"
    war
}

group = "net.swahome.example.springmvc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("http://repo.spring.io/release/")
}

val springVersion = "4.3.23.RELEASE"
val springFoxVersion = "2.9.2"
val kotlinLoggingVersion = "1.6.26"
val log4j2Version = "2.11.2"
val slf4jVersion = "1.7.26"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework", "spring-webmvc", springVersion)
    implementation("io.springfox", "springfox-swagger2", springFoxVersion)
    implementation("io.springfox", "springfox-swagger-ui", springFoxVersion)
    implementation("io.github.microutils", "kotlin-logging", kotlinLoggingVersion)
    implementation("org.slf4j", "jcl-over-slf4j", slf4jVersion)


    compileOnly("javax", "javaee-api", "6.0")

    runtimeOnly("org.apache.logging.log4j", "log4j-core", log4j2Version)
    runtimeOnly("org.apache.logging.log4j", "log4j-slf4j-impl", log4j2Version)

    testCompile("org.testng", "testng", "6.10")
    testCompile("org.springframework", "spring-test", springVersion)
    testCompile("org.jboss.spec", "jboss-javaee-all-6.0", "3.0.3.Final")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useTestNG()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val explodedWar by tasks.creating {
    dependsOn(tasks.named("compileKotlin"))

    doLast {
        val warDir = "$buildDir/libs/exploded/${project.name}-${project.version}.war"

        copy {
            into(warDir)
            from("$projectDir/src/main/webapp")
        }

        copy {
            into("$warDir/WEB-INF/classes")
            from(sourceSets["main"].output)
        }

        copy {
            into("$warDir/WEB-INF/lib")
            from(configurations["runtimeClasspath"])
        }
    }
}
