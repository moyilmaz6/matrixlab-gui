import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.matrixlab"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    jvmToolchain(21)
}

sourceSets {
    main {
        java.srcDirs("src/main/java", "src/main/kotlin")
    }
}



dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.scijava:native-lib-loader:2.5.0")
    implementation("org.slf4j:slf4j-nop:2.0.13")
}

compose.desktop {
    application {
        mainClass = "matrixlab.ui.MainKt"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )
            packageName = "MatrixLab"
            packageVersion = "1.0.0"

            appResourcesRootDir.set(project.layout.projectDirectory.dir("src/main/resources"))

            macOS {
                iconFile.set(project.file("src/main/resources/icons/icon.icns"))
                signing {
                    bundleID = "com.matrixlab.gui"
                }
            }
//            windows {
//                iconFile.set(project.file("src/main/resources/icons/icon.ico"))
//            }
//            linux {
//                iconFile.set(project.file("src/main/resources/icons/icon.png"))
//            }
        }
    }
}
