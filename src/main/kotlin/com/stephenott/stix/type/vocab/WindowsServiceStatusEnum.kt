package com.stephenott.stix.type.vocab

class WindowsServiceStatusEnum(private val status: String) : ClosedVocab, CharSequence by status {

    companion object {
        val vocabName = "windows-service-status-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "SERVICE_CONTINUE_PENDING", "SERVICE_PAUSE_PENDING", "SERVICE_PAUSED",
            "SERVICE_RUNNING", "SERVICE_START_PENDING", "SERVICE_STOP_PENDING",
            "SERVICE_STOPPED"
        )
    }

    init {
        require(this.status in vocab)
    }
}