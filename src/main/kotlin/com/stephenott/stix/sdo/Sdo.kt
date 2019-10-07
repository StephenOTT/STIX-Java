package com.stephenott.stix.sdo

import com.stephenott.stix.serialization.JsonReader
import com.stephenott.stix.serialization.JsonWriter
import com.stephenott.stix.ValidatorManager

class Sdo<T:StixDomainObject>
    (data: T, validateOnCreation: Boolean = true):
    JsonReader<T>,
    JsonWriter,
    ValidatorManager<T, Sdo<T>> {
    override fun readJson(jsonString: String): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toJson(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var isValid: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun validate(data: T): Sdo<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}