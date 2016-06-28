package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.product.issue.IssueId
import com.saasovation.issuetracker.domain.model.product.issue.Severity
import com.saasovation.issuetracker.domain.model.product.issue.UserId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

class ProductTest {
    val tenantId: TenantId = TenantId()
    val productId: ProductId = ProductId()

    fun createProduct(): Product {
        return Product(tenantId,
                productId,
                "A product name",
                "A product description",
                ProductManagerId(),
                IssueAssigner())
    }

    @Test
    fun reportDefect() {
        val product: Product = createProduct()

        val description = "The product does not work"
        val aDefect = product.reportDefect(IssueId(), description, "Nothing works", Severity.Medium)

        assertEquals(description, aDefect.description)
    }

    @Test
    fun featureRequest() {
        val product: Product = createProduct()
        val aDescription: String = "Paint it in yellow"

        val aFeatureRequest = product.featureRequest(IssueId(), aDescription, "yellow is a winderfull color", Severity.Medium)

        assertEquals(aDescription, aFeatureRequest.description)
    }

    @Test
    fun assignToUser() {
        val product: Product = createProduct()
        val aUser: UserId = UserId()
        val anIssueId: IssueId = IssueId()

        val aDefect = product.reportDefect(anIssueId, "bb", "aa", Severity.Low)
        aDefect.assignTo(aUser)

        assertTrue(aDefect.isAssigned())
    }

    @Test
    fun markAsDuplicated() {
        val product: Product = createProduct()
        val anIssueId: IssueId = IssueId()
        val anotherIssueId: IssueId = IssueId()

        val aDefect = product.reportDefect(anIssueId, "bb", "aa", Severity.Low)
        val anotherDefect = product.reportDefect(anotherIssueId, "bb", "aa", Severity.Low)
        aDefect.markDuplicate(anotherIssueId)

        assertEquals(anotherDefect.issueId, aDefect.duplicateOf)
    }

    @Test
    fun rejectAnIssue() {
        val product: Product = createProduct()
        val anIssueId: IssueId = IssueId()
        val aDefect = product.reportDefect(anIssueId, "aa", "bb", Severity.Low)

        aDefect.reject()

        assertTrue(aDefect.rejected)
    }

    @Test
    @Ignore
    fun determineProductStatisticsTest() {
        val product: Product = createProduct()

        repeat(1000, { a: Int -> product.reportDefect(IssueId(), "aa${a}", "bb", Severity.Low) })
        // val defectStatistics: DefectStatistics = product.determineDefectStatistics(120)

        //assertEquals(8.33, defectStatistics.defectDensity, 0.1)
    }
}