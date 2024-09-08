plugins {
    id("java")
}

group = "com.mokkachocolata"
version = "1.0"

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.mokkachocolata.pcsimsaveeditorserver.ServerClass"
    }
}

tasks.test {
    useJUnitPlatform()
}