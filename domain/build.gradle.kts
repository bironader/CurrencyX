import org.gradle.internal.impldep.com.fasterxml.jackson.core.JsonPointer.compile

plugins {
    id("kotlin")
}
buildscript {
    repositories {
        mavenCentral()

    }
    dependencies {
        classpath(Kotlin.KOTLIN_PLUGIN)
    }
}
dependencies {

    implementation(Kotlin.COROUTINES) // need for flows
    implementation("javax.inject:javax.inject:1")


}