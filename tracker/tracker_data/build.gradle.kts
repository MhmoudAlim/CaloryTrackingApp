apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(configs.Modules.core))
    "implementation"(project(configs.Modules.trackerDomain))

    "implementation"(dependencies.Retrofit.okHttp)
    "implementation"(dependencies.Retrofit.retrofit)
    "implementation"(dependencies.Retrofit.okHttpLoggingInterceptor)
    "implementation"(dependencies.Retrofit.moshiConverter)

    "kapt"(dependencies.Room.roomCompiler)
    "implementation"(dependencies.Room.roomKtx)
    "implementation"(dependencies.Room.roomRuntime)
}