package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.product.issue.Issue
import com.saasovation.issuetracker.domain.model.product.issue.IssueId
import com.saasovation.issuetracker.domain.model.product.issue.IssueType
import com.saasovation.issuetracker.domain.model.product.issue.Severity
import com.saasovation.issuetracker.domain.model.product.release.Release
import com.saasovation.issuetracker.domain.model.tenant.TenantId

class Product(
        val tenantId: TenantId,
        val productId: ProductId,
        val name: String,
        val description: String,
        val productManager: ProductManagerId,
        val issueAssigner: IssueAssigner) {
    var currentRelease: Release? = null
    var severityTotals: SeverityTotals


    init {
        this.severityTotals = SeverityTotals(0, 0, 0)
    }

    fun reportDefect(issueId: IssueId, description: String, summary: String, severity: Severity): Issue {
        return Issue(this.tenantId, this.productId, issueId, description, summary, IssueType.Defect, severity)
    }

    fun featureRequest(issueId: IssueId, description: String, summary: String, severity: Severity): Issue {
        return Issue(this.tenantId, this.productId, issueId, description, summary, IssueType.FeatureRequest, severity)
    }

    /* TODO: Move this to somewhere else
    fun determineDefectStatistics(KLOC: Int): DefectStatistics {
        val defectDensity = (issues.filter { it.value.isDefect() }.count() / KLOC).toDouble()
        val countIssues = issues.filter { it.value.isDefect() }.count()
        return DefectStatistics(
                defectsReported = 22,
                defectsFixed = 22,
                defectsKnown = issues.filter { it.value.isDefect() }.count(),
                KLOC = KLOC
        )
    }*/

}

