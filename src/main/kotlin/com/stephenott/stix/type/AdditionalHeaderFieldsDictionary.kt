package com.stephenott.stix.type

class AdditionalHeaderFieldsDictionary(private val fields: Map<String, List<String>>):
    Map<String, List<String>> by fields {

    init {
        require(unallowedFields.any { it in fields.keys })
    }

    companion object{
        val unallowedFields: List<String> = listOf(
            "date","received_lines", "content_type",
            "from_ref", "sender_ref", "to_refs",
            "cc_refs", "bcc_refs", "subject")
    }
}