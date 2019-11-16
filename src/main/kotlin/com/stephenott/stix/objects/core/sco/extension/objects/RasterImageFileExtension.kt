package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.ExifTagsDictionary
import com.stephenott.stix.type.StixInteger

interface RasterImageFileExtensionExt: ScoExtension{

    val imageHeight: StixInteger?
    val imageWidth: StixInteger?
    val bitsPerPixel: StixInteger?
    val exifTags: ExifTagsDictionary?

    companion object:
        CompanionExtensionType,
        BusinessRulesExtensionValidator<RasterImageFileExtensionExt> {

        override val extensionType: String = "raster-image-ext"

        override fun objectValidationRules(obj: RasterImageFileExtensionExt) {
            require(obj.imageHeight != null ||
                    obj.imageWidth != null ||
                    obj.bitsPerPixel != null ||
                    obj.exifTags != null,
                lazyMessage = {"At least 1 property must be provided for RasterImage File Extension"})
        }
    }
}

data class RasterImageFileExtension(
    override val imageHeight: StixInteger? = null,
    override val imageWidth: StixInteger? = null,
    override val bitsPerPixel: StixInteger? = null,
    override val exifTags: ExifTagsDictionary? = null
): RasterImageFileExtensionExt {

    init {
        RasterImageFileExtensionExt.objectValidationRules(this)
    }
}