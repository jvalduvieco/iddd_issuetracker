package com.saasovation.issuetracker.domain.model.product

import java.util.*

class DefectSeverityCalculator() {
    private val defectsByProduct: HashMap<ProductId, Int> = hashMapOf()

    fun issueReported(productId: ProductId) {
        defectsByProduct[productId] = defectsByProduct.getOrDefault(productId, 0) + 1
    }
    fun defectsByProduct(productId: ProductId): Int {
        return defectsByProduct[productId] ?: throw IllegalArgumentException()
    }
}