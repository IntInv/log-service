
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "com.intinv"
version = "0.0.1"

application {
    mainClass.set("com.intinv.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("redis.clients:jedis:3.4.0")
}
