package com.stephenott.stix.type

class Product(private val product: String): CharSequence by product{
    init {
        require(product.none { it.isWhitespace() && it.isUpperCase() })
    }
}