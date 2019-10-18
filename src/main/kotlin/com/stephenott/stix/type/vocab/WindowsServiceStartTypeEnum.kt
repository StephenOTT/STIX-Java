package com.stephenott.stix.type.vocab

class WindowsServiceStartTypeEnum(private val type: String) : ClosedVocab, CharSequence by type {

    companion object {
        val vocabName = "windows-service-start-type-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "SERVICE_AUTO_START", "SERVICE_BOOT_START", "SERVICE_DEMAND_START",
            "SERVICE_DISABLED", "SERVICE_SYSTEM_ALERT"
        )
    }

    init {
        require(this.type in vocab)
    }
}