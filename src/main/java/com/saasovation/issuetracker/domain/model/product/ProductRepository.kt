package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.tenant.TenantId

interface ProductRepository {
    fun allProductsOfTenant(tenantId: TenantId): Collection<Product>

    fun save(product: Product)
}
