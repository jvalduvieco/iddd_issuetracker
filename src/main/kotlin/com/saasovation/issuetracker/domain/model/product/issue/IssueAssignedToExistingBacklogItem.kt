package com.saasovation.issuetracker.domain.model.product.issue

import com.saasovation.issuetracker.domain.model.DomainEvent
import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import java.time.Instant
import java.util.*

data class IssueAssignedToExistingBacklogItem(
        val tenantId: TenantId,
        val productId: ProductId,
        val issueId: IssueId,
        val issueType: IssueType,
        val backlogItemId: String) : DomainEvent {
    override val eventVersion: Int = 0
    override val occurredOn: Date = Date.from(Instant.now())
}
