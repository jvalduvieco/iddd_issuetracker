package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.product.release.Release
import com.saasovation.issuetracker.domain.model.tenant.TenantId

class Product(
        val tenantId: TenantId,
        val productId: ProductId,
        val name: String,
        val description: String,
        val productManager: ProductManager,
        val issueAssigner: IssueAssigner) {
    var currentRelease: Release? = null
    var severityTotals: SeverityTotals

    init {
        this.severityTotals = SeverityTotals(0, 0, 0)
    }
}
