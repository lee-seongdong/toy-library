plugins{
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21" // @Component, @Transactional 등 붙을때 open을 자동으로 붙여줌
    kotlin("plugin.jpa") version "2.2.21" // @Entity, @Embeddable 등 default 생성자가 필요한 경우 자동으로 생성해줌
    id("org.springframework.boot") version "4.0.0-M3"
    id("io.spring.dependency-management") version "1.1.7"
}

kotlin {
    jvmToolchain(21)
}

apply(plugin = "io.spring.dependency-management")

group = "toy.lsd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    implementation("org.postgresql:postgresql:42.7.8")


    testImplementation(kotlin("test")) // The Kotlin test library
    // platform: BOM (Bill of Materials)을 가져오는 Gradle 함수
    // BOM: 여러 라이브러리의 호환되는 버전들을 미리 정의해놓은 메타데이터 파일
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
