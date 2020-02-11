package com.stephenott.stix.type.vocab

class ThreatActorRoles(private val roles: LinkedHashSet<ThreatActorRole> = linkedSetOf()) :
    Set<ThreatActorRole> by roles {
}

class ThreatActorRole(private val role: String) : OpenVocab {

    override fun getValue(): String {
        return role
    }

    companion object {

        const val vocabName = "threat-actor-role-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "agent", "director", "independent",
            "infrastructure-architect", "infrastructure-operator", "malware-author",
            "sponsor"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.role in vocab)
    }
}