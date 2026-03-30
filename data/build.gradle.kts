plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {

    implementation(project(":domain"))

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //coroutine
    implementation(libs.kotlinx.coroutines.core)

    //inject
    implementation(libs.javax.inject)

    testImplementation(libs.junit)
}