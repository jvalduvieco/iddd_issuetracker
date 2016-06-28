package com.saasovation.issuetracker.domain.model.product.issue

import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId

interface IssueRepository {
    fun allOutstandingDefectsOfProduct(tenantId: TenantId, productId: ProductId): Collection<Issue>
}
