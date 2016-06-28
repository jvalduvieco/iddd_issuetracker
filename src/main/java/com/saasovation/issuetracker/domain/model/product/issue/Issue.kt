package com.saasovation.issuetracker.domain.model.product.issue

import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import java.com.saasovation.issuetracker.domain.model.DomainEventPublisher

class Issue(
        val tenantId: TenantId,
        val productId: ProductId,
        val issueId: IssueId,
        val description: String,
        val summary: String,
        val type: IssueType,
        val severity: Severity) {

    private val assignedBacklogItemId: String? = null

    init {
        DomainEventPublisher.instance().publish(
                IssueReported(
                        this.tenantId, this.productId, this.issueId,
                        this.description, this.summary, this.type, this.severity))
    }

    val isDefect: Boolean
        get() = this.type === IssueType.Defect

    val isFeatureRequest: Boolean
        get() = this.type === IssueType.FeatureRequest

    val isHighSeverity: Boolean
        get() = this.severity === Severity.High

    val isMediumSeverity: Boolean
        get() = this.severity === Severity.Medium

    val isLowSeverity: Boolean
        get() = this.severity === Severity.Low
}
