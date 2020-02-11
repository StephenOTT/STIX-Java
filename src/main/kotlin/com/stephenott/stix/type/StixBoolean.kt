package com.stephenott.stix.type

data class StixBoolean(
    val value: Boolean = false,
    val isDefinedValue: Boolean = false
) {

    /**
     * Defaults to StixBoolean value to false.  Sets isDefinedValue to false.
     * Essentially if the no arg constructor was used, we assume that its a default boolean value rather than explicitly being provided.
     * This mainly has implications for JSON processing.
     */
    constructor() : this(false, false)

    constructor(value: Boolean) : this(value, true)

    constructor(stixBoolean: StixBoolean) : this(stixBoolean.value, stixBoolean.isDefinedValue)

    companion object {
        fun parse(booleanString: String): StixBoolean {
            return StixBoolean(booleanString.toBoolean(), true)
        }
    }

    override fun toString(): String {
        return this.value.toString()
    }

}