import dev.aga.gradle.versioncatalogs.Generator.generate

pluginManagement {
    repositories {
        maven {
            url = uri("https://nexus.kuku.me/repository/maven-public/")
        }
    }
}

plugins {
    id("dev.aga.gradle.version-catalog-generator") version ("2.1.1")
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://nexus.kuku.me/repository/maven-public/")
        }
    }
    versionCatalogs {
        generate("springLibs") {
            from(toml("spring-boot-dependencies"))
        }
    }
}

rootProject.name = "kiss"
