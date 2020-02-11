package com.stephenott.stix.common

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object StixDataFormats {

    /**
     * Default pattern for deserialization of date/times into a STIX compliant timestamp.
     */
    val TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]X"

    /**
     * Default Timezone used for Serialization and Deserialization.
     */
    val TIMEZONE = "UTC"

    /**
     * Supports 0-9 digits of Sub-Second precision storage.  (Nano Second Support)
     * @return
     */
    val readerStixDateTimeFormatter: DateTimeFormatter
        get() {
            val formatterBuilder = DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                .optionalEnd()
                .appendPattern("X")

            return formatterBuilder.toFormatter().withZone(ZoneId.of("UTC"))
        }

    fun getWriterStixDateTimeFormatter(subSecondPrecision: Int): DateTimeFormatter {
        require(subSecondPrecision <= 9) { "Sub-Second Precision can only be from 0 to 9 digits" }
        val formatterBuilder = DateTimeFormatterBuilder()

        formatterBuilder.appendPattern("yyyy-MM-dd'T'HH:mm:ss")

        if (subSecondPrecision > 0) {
            formatterBuilder.appendFraction(ChronoField.NANO_OF_SECOND, subSecondPrecision, subSecondPrecision, true)
        }

        formatterBuilder.appendPattern("X")

        return formatterBuilder.toFormatter().withZone(ZoneId.of("UTC"))
    }

}
