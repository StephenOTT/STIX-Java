package com.stephenott.stix.type

data class Product(private val product: String){
    init {
        require(product.none { it.isWhitespace() && it.isUpperCase() })
    }

    override fun toString(): String {
        return product
    }
}