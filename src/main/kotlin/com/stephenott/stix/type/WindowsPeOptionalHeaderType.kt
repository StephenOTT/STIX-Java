package com.stephenott.stix.type

import kotlin.reflect.KVisibility
import kotlin.reflect.full.*


data class WindowsPeOptionalHeaderType(
    val magicHex: StixHex? = null,
    val majorLinkerVersion: StixInteger? = null,
    val minorLinkerVersion: StixInteger? = null,
    val sizeOfCode: StixInteger? = null,
    val sizeOfInitializedData: StixInteger? = null,
    val sizeOfUninitializedData: StixInteger? = null,
    val addressOfEntryPoint: StixInteger? = null,
    val baseOfCode: StixInteger? = null,
    val baseOfData: StixInteger? = null,
    val imageBase: StixInteger? = null,
    val sectionAlignment: StixInteger? = null,
    val fileAlignment: StixInteger? = null,
    val majorOsVersion: StixInteger? = null,
    val minorOsVersion: StixInteger? = null,
    val majorImageVersion: StixInteger? = null,
    val minorImageVersion: StixInteger? = null,
    val majorSubsystemVersion: StixInteger? = null,
    val minorSubsystemVersion: StixInteger? = null,
    val win32VersionValueHex: StixHex? = null,
    val sizeOfImage: StixInteger? = null,
    val sizeOfHeaders: StixInteger? = null,
    val checksumHex: StixHex? = null,
    val subsystemHex: StixHex? = null,
    val dllCharacteristics: StixHex? = null,
    val sizeOfStackReserve: StixInteger? = null,
    val sizeOfStackCommit: StixInteger? = null,
    val sizeOfHeapReserve: StixInteger? = null,
    val sizeOfHeapCommit: StixInteger? = null,
    val loaderFlagsHex: StixHex? = null,
    val numberOfRvaAndSizes: StixInteger? = null,
    val hashes: HashesDictionary? = null

) {

    init {

        require(this::class.memberProperties
            .filter {it.visibility == KVisibility.PUBLIC}
            .any { it.getter.call() != null },
            lazyMessage = {"At least one property must be provided/be not null."})

            require(sizeOfCode?.value!! >= 0)
            require(sizeOfInitializedData?.value!! >= 0)
            require(sizeOfUninitializedData?.value!! >= 0)
            require(sizeOfImage?.value!! >= 0)
            require(sizeOfHeaders?.value!! >= 0)
            require(sizeOfStackReserve?.value!! >= 0)
            require(sizeOfStackCommit?.value!! >= 0)
            require(sizeOfHeapReserve?.value!! >= 0)
            require(sizeOfHeapCommit?.value!! >= 0)
        }

    }