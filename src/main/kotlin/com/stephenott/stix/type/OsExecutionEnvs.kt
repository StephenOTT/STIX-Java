package com.stephenott.stix.type

class OsExecutionEnvs(private val envs: LinkedHashSet<OsExecutionEnv>):
    Set<OsExecutionEnv> by envs{
}

data class OsExecutionEnv(val env: String){}