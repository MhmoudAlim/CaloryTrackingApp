apply {
    from("$rootDir/base-compose-module.gradle")
}

dependencies {
    "implementation"(project(configs.Modules.core))
    "implementation"(project(configs.Modules.coreUi))
    "implementation"(project(configs.Modules.onboardingDomain))
}