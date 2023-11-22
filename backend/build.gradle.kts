plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}


group = "${rootProject.group}.backend"


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:1.15.1")
    testImplementation("com.jayway.jsonpath:json-path:2.8.0")
    testImplementation("org.hamcrest:hamcrest-library:2.2")

}


tasks.withType<Test> {
    useJUnitPlatform()
}


tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}
