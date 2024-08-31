plugins {
    kotlin("jvm") version "2.0.10"
}

group = "io.github.okamt"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://mvn.bladehunt.net/releases")
}

val minestomVersion = "f53625f35b"
val kotstomVersion = "0.4.0-alpha.0"
val adventureApiVersion = "4.17.0"
val kotlinxVersion = "1.7.1"
val mageVersion = "0.0.1"

dependencies {
    implementation("net.minestom:minestom-snapshots:$minestomVersion")

    implementation("net.bladehunt:kotstom:$kotstomVersion")
    implementation("net.bladehunt:kotstom-adventure-serialization:$kotstomVersion")

    implementation("net.kyori:adventure-text-minimessage:$adventureApiVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxVersion")

    implementation("io.github.okamt:mage:$mageVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.github.okamt.minestomjam2024.MainKt"
    }

    // https://stackoverflow.com/a/71094727
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
