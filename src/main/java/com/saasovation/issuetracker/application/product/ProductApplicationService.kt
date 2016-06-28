package com.saasovation.issuetracker.application.product

import com.saasovation.issuetracker.domain.model.product.*
import com.saasovation.issuetracker.domain.model.tenant.TenantId

class ProductApplicationService(private val productRepository: ProductRepository) {

    fun createProduct(tenantId: String, name: String, description: String, productManagerId: String, issueAssignerId: String) {
        val product = Product(
                TenantId(tenantId),
                ProductId(),
                name,
                description,
                ProductManager(productManagerId),
                IssueAssigner(issueAssignerId))

        this.productRepository.save(product)
    }
}
