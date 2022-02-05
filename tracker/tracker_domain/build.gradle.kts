apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(configs.Modules.core))
    "implementation"(dependencies.Coroutines.coroutines)
}