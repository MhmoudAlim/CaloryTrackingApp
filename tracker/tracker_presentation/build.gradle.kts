apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(configs.Modules.core))
    "implementation"(project(configs.Modules.coreUi))
    "implementation"(project(configs.Modules.trackerDomain))

    "implementation"(dependencies.Coil.coilCompose)
}