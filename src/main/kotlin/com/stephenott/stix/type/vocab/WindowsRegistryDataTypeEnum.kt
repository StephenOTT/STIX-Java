package com.stephenott.stix.type.vocab

class WindowsRegistryDataTypeEnum(private val datatype: String) : ClosedVocab {

    override fun getValue(): String {
        return datatype
    }

    companion object {
        const val vocabName = "windows-registry-datatype-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "REG_NONE", "REG_SZ", "REG_EXPAND_SZ",
            "REG_BINARY", "REG_DWORD", "REG_DWORD_BIG_ENDIAN",
            "REG_DWORD_LITTLE_ENDIAN", "REG_LINK", "REG_MULTI_SZ",
            "REG_RESOURCE_LIST", "REG_FULL_RESOURCE_DESCRIPTION", "REG_RESOURCE_REQUIREMENTS_LIST",
            "REG_QWORD", "REG_INVALID_TYPE"
        )
    }

    init {
        require(this.datatype in vocab)
    }
}