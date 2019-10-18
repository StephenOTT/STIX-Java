package com.stephenott.stix.type

import com.stephenott.stix.type.vocab.WindowsRegistryDataTypeEnum

class WindowsRegistryValueTypes(private val types: List<WindowsRegistryValueType>): List<WindowsRegistryValueType> by types{

    init {
        //@TODO
    }

}

data class WindowsRegistryValueType(
    val name: String?,
    val data: String?,
    val dataType: WindowsRegistryDataTypeEnum?
) {
    init {
        require(listOf(name, data, dataType).any { it != null })
    }
}