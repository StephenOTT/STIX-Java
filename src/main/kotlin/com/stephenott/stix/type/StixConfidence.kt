package com.stephenott.stix.type

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

/**
 * stixValue must be provided to be considered a valid StixConfidence.
 *
 */
data class StixConfidence(val realValue: Int?, val stixValue: Int?, val scaleValue: String?) {

    constructor(confidence: Int): this(null, confidence, null)

    init {
        realValue?.let {
            require(stixValue != null)
        }
        stixValue?.let {
            require(stixValue in 0..100)
        }
        scaleValue?.let {
            require(it.isNotEmpty())
        }

        require(realValue != null || stixValue != null || scaleValue != null)
    }

    fun getConfidence(): Int{
        if (stixValue != null){
            return stixValue
        } else {
            throw IllegalStateException("Unable to determine confidence value because it is null.")
        }
    }

    companion object {
        //@TODO Interop Comment for Github: Review the use of Null for the confidence values, as null is usually only used for "not provided".  Confirm on GH issues.
        fun noneLowMedHighScale(realValue: Int?): StixConfidence {
            return when (realValue) {
                null -> StixConfidence(null, null, "Not Specified")
                0 -> StixConfidence(realValue, 0, "None")
                in 1..29 -> StixConfidence(realValue, 15, "Low")
                in 30..69 -> StixConfidence(realValue, 50, "Med")
                in 70..100 -> StixConfidence(realValue, 85, "High")
                else -> {
                    throw IllegalArgumentException("Invalid value.")
                }
            }
        }

        fun zeroToTenScale(realValue: Int?): StixConfidence {
            return when (realValue) {
                null -> StixConfidence(null, null, "Not Specified")
                in 0..4 -> StixConfidence(realValue, 0, "0")
                in 5..14 -> StixConfidence(realValue, 10, "1")
                in 15..24 -> StixConfidence(realValue, 20, "2")
                in 25..34 -> StixConfidence(realValue, 30, "3")
                in 35..44 -> StixConfidence(realValue, 40, "4")
                in 45..54 -> StixConfidence(realValue, 50, "5")
                in 55..64 -> StixConfidence(realValue, 60, "6")
                in 65..74 -> StixConfidence(realValue, 70, "7")
                in 75..84 -> StixConfidence(realValue, 80, "8")
                in 85..94 -> StixConfidence(realValue, 90, "9")
                in 95..100 -> StixConfidence(realValue, 100, "10")
                else -> {
                    throw IllegalArgumentException("Invalid value.")
                }
            }
        }

        fun admiraltyCredibility(realValue: Int?): StixConfidence {
            return when (realValue) {
                null -> StixConfidence(null, null, "6 - Truth cannot be judged")
                in 0..19 -> StixConfidence(realValue, 10, "5 - Improbable")
                in 20..39 -> StixConfidence(realValue, 30, "4 - Doubtful")
                in 40..59 -> StixConfidence(realValue, 50, "3 - Possibly True")
                in 60..79 -> StixConfidence(realValue, 70, "2 - Probably True")
                in 80..100 -> StixConfidence(realValue, 90, "1 - Confirmed by other sources")
                else -> {
                    throw IllegalArgumentException("Invalid value.")
                }
            }
        }

        /**
         *  Words of Estimative Probability (WEP)
         */
        fun wepScale(realValue: Int?): StixConfidence {
            //@TODO Review the scaleValues in the 2.1 Docs for typos: Inconsistencies in the capitalization
            return when (realValue) {
                null -> StixConfidence(null, null, "Not Specified")
                0 -> StixConfidence(realValue, 0, "Impossible")
                in 1..19 -> StixConfidence(realValue, 10, "Highly Unlikely/Almost Certainly Not")
                in 20..39 -> StixConfidence(realValue, 30, "Unlikely/Probably Not")
                in 40..59 -> StixConfidence(realValue, 50, "Even Chance")
                in 60..79 -> StixConfidence(realValue, 70, "Likely/Probable")
                in 80..99 -> StixConfidence(realValue, 90, "Highly likely/Almost Certain")
                100 -> StixConfidence(realValue, 100, "Certain")
                else -> {
                    throw IllegalArgumentException("Invalid value.")
                }
            }
        }

        /**
         * DNI Scale: ICD 203
         */
        fun dniScale(realValue: Int?): StixConfidence {
            return when (realValue) {
                null -> StixConfidence(null, null, "Not Specified")
                in 0..9 -> StixConfidence(realValue, 5, "Almost No Chance / Remote")
                in 10..19 -> StixConfidence(realValue, 15, "Very Unlikely / Highly Improbable")
                in 20..39 -> StixConfidence(realValue, 30, "Unlikely/Improbable")
                in 40..59 -> StixConfidence(realValue, 50, "Rough Even Chance / Roughly Even Odds")
                in 60..79 -> StixConfidence(realValue, 70, "Likely / Probable")
                in 80..89 -> StixConfidence(realValue, 85, "Very Likely / Highly Probable")
                in 90..100 -> StixConfidence(realValue, 95, "Almost Certain / Nearly Certain")
                else -> {
                    throw IllegalArgumentException("Invalid value.")
                }
            }
        }
    }
}