plugins {
    kotlin("jvm") version "2.0.0"
}

group = "technology.zim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    // https://mvnrepository.com/artifact/it.unimi.dsi/dsiutils
    implementation("it.unimi.dsi:dsiutils:2.7.3")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}