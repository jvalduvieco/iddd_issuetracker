package com.saasovation.issuetracker.domain.model.product

import java.util.*

import com.saasovation.issuetracker.domain.model.tenant.TenantId

class ProductDefectivenessRanker(private val productRepository: ProductRepository) {

    fun mostDefectiveProduct(
            tenantId: TenantId,
            severityWeights: SeverityWeights): ProductDefectiveness? {

        return null
    }

    fun rankedDefectiveProducts(
            tenantId: TenantId,
            severityWeights: SeverityWeights): Set<ProductDefectiveness>? {

        return null
    }

    private fun allProductsOfTenant(tenantId: TenantId): Collection<Product> {
        return this.productRepository.allProductsOfTenant(tenantId)
    }
}
