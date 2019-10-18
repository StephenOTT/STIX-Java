package com.stephenott.stix.type

class HttpRequestHeaderDictionary(private val headers: Map<String, List<String>>):
    Map<String, List<String>> by headers {

    init {
        //note that this dictionary allows for a value in the List<String> to be a empty non-null string such as "".
    }
}