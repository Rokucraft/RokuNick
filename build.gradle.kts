plugins {
    `java-library`
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.rokucraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("me.clip:placeholderapi:2.11.5")

    implementation("org.incendo:cloud-paper:2.0.0-beta.1")

    library("com.google.dagger:dagger:2.50")
    annotationProcessor("com.google.dagger:dagger-compiler:2.50")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

bukkit {
    name = rootProject.name
    version = project.version.toString()
    main = "com.rokucraft.rokunick.RokuNickPlugin"
    apiVersion = "1.19"
    author = "Aikovdp"
    website = "https://rokucraft.com"
    depend = listOf("Vault")
    softDepend = listOf("PlaceholderAPI")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier = ""
        listOf(
            "org.incendo.cloud",
            "io.leangen.geantyref"
        ).forEach { relocate(it, "${rootProject.group}.${rootProject.name}.lib.$it") }
    }

    runServer {
        minecraftVersion("1.19.4")
        downloadPlugins {
            github("MilkBowl", "Vault", "1.7.3", "Vault.jar")
            hangar("PlaceholderAPI", "2.11.5")
            url("https://download.luckperms.net/1530/bukkit/loader/LuckPerms-Bukkit-5.4.117.jar")
        }
    }
}
