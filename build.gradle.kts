plugins {
    id("java")
    `java-library`
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}


group = "aaagt.moneytransferservice"


allprojects {
    repositories {
        mavenCentral()
    }
}


subprojects {
    version = "1.0-SNAPSHOT"

    tasks.withType<JavaCompile>().configureEach {
        //options.compilerArgs.add("--enable-preview")
        javaCompiler.set(javaToolchains.compilerFor {
            languageVersion.set(JavaLanguageVersion.of(17))
        })
    }
}
