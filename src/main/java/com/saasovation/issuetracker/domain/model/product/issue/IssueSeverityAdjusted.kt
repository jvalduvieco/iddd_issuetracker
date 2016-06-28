package com.saasovation.issuetracker.domain.model.product.issue

import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import java.com.saasovation.issuetracker.domain.model.DomainEvent
import java.time.Instant
import java.util.*

class IssueSeverityAdjusted(
        val tenantId: TenantId,
        val productId: ProductId,
        val issueId: IssueId,
        val currentSeverity: Severity,
        val previousSeverity: Severity) : DomainEvent {
    override val eventVersion: Int = 0
    override val occurredOn: Date = Date.from(Instant.now())
}
