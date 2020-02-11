package com.stephenott.stix.type.vocab

class IdentityRoles(private val roles: LinkedHashSet<IdentityRole> = linkedSetOf(), enforceVocab: Boolean = false) :
    Set<IdentityRole> by roles {

    init {
        if (enforceVocab) {
            roles.all { IdentityRole.vocab.contains(it.toString()) }
        }
    }
}

class IdentityRole(private val role: String, enforceVocab: Boolean = false) : OpenVocab {

    override fun getValue(): String {
        return role
    }

    init {
        if (enforceVocab) {
            require(vocab.contains(role))
        }
    }

    companion object {

        val vocabName = "identity-roles"

        var vocab: LinkedHashSet<String> = linkedSetOf(
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }
}