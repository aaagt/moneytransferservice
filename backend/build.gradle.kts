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
    //implementation("ch.qos.logback:logback-core:1.4.11")
    // https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<Test> {
    useJUnitPlatform()
}


tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}
