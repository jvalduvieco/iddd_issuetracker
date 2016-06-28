package com.saasovation.issuetracker.domain.model.product.issue

import com.saasovation.issuetracker.domain.model.DomainEvent
import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import java.time.Instant
import java.util.*

data class IssueReported(
        val tenantId: TenantId,
        val productId: ProductId,
        val issueId: IssueId,
        val description: String,
        val summary: String,
        val type: IssueType,
        val severity: Severity) : DomainEvent {
    override val eventVersion: Int = 0
    override val occurredOn: Date = Date.from(Instant.now())
}
