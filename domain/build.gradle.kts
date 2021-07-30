plugins {
    id ("kotlin")
}
buildscript {
    repositories {
        mavenCentral()

    }
    dependencies {
        classpath (Build.KOTLIN_PLUGIN)
    }
}