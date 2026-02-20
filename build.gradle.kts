plugins {
    id("java")
    id("application")
}

group = "org.lychee"
version = "1.0-SNAPSHOT"

application {
    mainClass = "org.lychee.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.quiltmc:syntaxpain:internal")
    implementation("com.google.guava:guava:33.5.0-jre")
    implementation("org.jspecify:jspecify:1.0.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}