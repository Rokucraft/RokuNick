plugins {
    `java-library`
}

group = "com.rokucraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io" )
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("me.clip:placeholderapi:2.11.5")
    implementation("cloud.commandframework:cloud-paper:2.0.0-SNAPSHOT")

    implementation("com.google.dagger:dagger:2.50")
    annotationProcessor("com.google.dagger:dagger-compiler:2.50")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}
