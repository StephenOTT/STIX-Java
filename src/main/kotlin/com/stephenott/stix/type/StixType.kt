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

        fun parse(typeString: String): StixType{
            val isCustomObject: Boolean = typeString.startsWith(customObjectPrefix)
            val type: String = if (isCustomObject){
                typeString.substringAfter(customObjectPrefix)
            } else {
                typeString
            }

            return StixType(type, isCustomObject)
        }

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