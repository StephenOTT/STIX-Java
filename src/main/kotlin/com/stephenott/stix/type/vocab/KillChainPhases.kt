package com.stephenott.stix.type.vocab

class KillChainPhases(private val phases: LinkedHashSet<KillChainPhase>) :
    Set<KillChainPhase> by phases {}

data class KillChainPhase(val killChainName: String, val phaseName: String) {}

object CommonKillChainPhases {

    val lockheedMartinKillChainName = "lockheed-martin-cyber-kill-chain"
    enum class LockheedMartinKillChainPhases(val phaseName: String) {
        RECONNAISSANCE("reconnaissance"),
        WEAPONIZATION("weaponization"),
        DELIVERY("delivery"),
        EXPLOITATION("exploitation"),
        INSTALLATION("installation"),
        COMMAND_AND_CONTROL("command-and-control"),
        ACTIONS_ON_OBJECTIVE("actions-on-objective");
    }
}

