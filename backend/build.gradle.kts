plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.jimmer)
}

jimmer {
    version = "latest.release"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(springLibs.spring.springBootStarterWeb)
    implementation(springLibs.jackson.jacksonModuleKotlin)
    implementation(springLibs.kotlin.kotlinReflect)
    testImplementation(springLibs.spring.springBootStarterTest)
    testImplementation(springLibs.kotlin.kotlinTestJunit5)
    testRuntimeOnly(springLibs.junit.junitPlatformLauncher)

    implementation(springLibs.spring.springBootStarterSecurity)
    implementation(springLibs.spring.springBootStarterJdbc)
    implementation(springLibs.postgresql.postgresql)
    implementation(springLibs.spring.springBootStarterValidation)
    implementation(libs.kotlin.logging)
}

tasks.named("compileJava") {
    inputs.files(tasks.named("processResources"))
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
