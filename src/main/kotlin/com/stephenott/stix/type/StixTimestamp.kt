package com.stephenott.stix.type

import com.stephenott.stix.common.StixDataFormats
import java.time.Instant
import java.util.regex.Pattern

data class StixTimestamp(
    val instant: Instant,
    val subSecondPrecision: Int) {

    init {
        //@TODO how to detect what parameters were used
        require(!(subSecondPrecision < 0 || subSecondPrecision > 9)) { "Sub-second precision must be between 0 and 9" }
    }

    companion object {
        var defaultSubSecondPrecision = 3
        var REGEX_SUBSECOND: Pattern =
            Pattern.compile("(?<fullDate>\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(.(?<subSecond>[0-9]+))?Z)")

        fun parse(dateString: String): StixTimestamp {
            val instant = Instant.from(StixDataFormats.readerStixDateTimeFormatter.parse(dateString))
            val subSecondPrecision = getSubSecondDigitCount(dateString)

            return StixTimestamp(instant, subSecondPrecision)
        }

        private fun getSubSecondDigitCount(dateString: String): Int {
            val matcher = REGEX_SUBSECOND.matcher(dateString)
            if (matcher.find()) {
                val subSeconds: String? = matcher.group("subSecond")
                // If no sub seconds were provided then return 0 as the precision
                return subSeconds?.length ?: 0

            } else {
                throw IllegalStateException("Unable to parse date")
            }
        }
    }

    constructor(stixTimestamp: StixTimestamp) : this(stixTimestamp.instant, stixTimestamp.subSecondPrecision)

    constructor() : this(Instant.now(), defaultSubSecondPrecision){}

    constructor(instant: Instant) : this(instant, instant.nano.toString().length)

    override fun toString(): String {
        return StixDataFormats.getWriterStixDateTimeFormatter(subSecondPrecision).format(this.instant)
    }

    fun toString(subSecondPrecision: Int): String {
        return StixDataFormats.getWriterStixDateTimeFormatter(subSecondPrecision).format(this.instant)
    }
}