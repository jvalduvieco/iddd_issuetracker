package com.saasovation.issuetracker.domain.model.product.issue

import com.saasovation.issuetracker.domain.model.DomainEventPublisher
import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId

class Issue(
        val tenantId: TenantId,
        val productId: ProductId,
        val issueId: IssueId,
        val description: String,
        val summary: String,
        val type: IssueType,
        val severity: Severity) {

    private val assignedBacklogItemId: String? = null
    var rejected: Boolean = false
        private set
    var assignedTo: UserId? = null
        private set
    var duplicateOf: IssueId? = null
        private set

    init {
        DomainEventPublisher().publish(
                IssueReported(
                        this.tenantId, this.productId, this.issueId,
                        this.description, this.summary, this.type, this.severity))
    }

    fun isDefect(): Boolean = this.type === IssueType.Defect

    fun isFeatureRequest(): Boolean = this.type === IssueType.FeatureRequest

    fun isHighSeverity(): Boolean = this.severity === Severity.High

    fun isMediumSeverity(): Boolean = this.severity === Severity.Medium

    fun isLowSeverity(): Boolean = this.severity === Severity.Low

    fun reject() {
        this.rejected = true
    }

    fun markDuplicate(issue: IssueId) {
        duplicateOf = issue
    }

    fun markRejected() {
        rejected = true
    }

    fun assignTo(userId: UserId) {
        assignedTo = userId
    }

    fun isAssigned(): Boolean {
        return assignedTo != null
    }
}

