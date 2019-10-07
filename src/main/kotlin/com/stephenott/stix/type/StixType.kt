package com.stephenott.stix.type

data class StixType(
    val type: String,
    val isCustomType: Boolean = false)
{

    init {
        require(type.length in IntRange(3, 250))
        require(Regex("^\\-?[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\\-?$").matches(type))
    }

    companion object{
        var customObjectPrefix: String = "x-" //@TODO refactor to lazy init and a outside factory
    }

    constructor(stixType: StixType): this(stixType.type, stixType.isCustomType)

    override fun toString(): String {
        return if (isCustomType){
            customObjectPrefix + type
        } else {
            type
        }
    }
}