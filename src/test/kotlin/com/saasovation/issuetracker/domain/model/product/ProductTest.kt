package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.product.issue.IssueId
import com.saasovation.issuetracker.domain.model.product.issue.Severity
import com.saasovation.issuetracker.domain.model.product.issue.UserId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
        product.reportDefect(IssueId(), description, "Nothing works", Severity.Medium)

        assertTrue(product.issues.count() == 1)
        assertEquals(description, product.issues.toList().first().second.description)
    }

    @Test
    fun featureRequest() {
        val product: Product = createProduct()
        val aDescription: String = "Paint it in yellow"

        product.featureRequest(IssueId(), aDescription, "yellow is a winderfull color", Severity.Medium)

        assertTrue(product.issues.count() == 1)
        assertEquals(aDescription, product.issues.toList().first().second.description)
    }

    @Test
    fun assignToUser() {
        val product: Product = createProduct()
        val aUser: UserId = UserId()
        val anIssueId: IssueId = IssueId()

        product.reportDefect(anIssueId, "bb", "aa", Severity.Low)
        product.assignIssue(anIssueId, aUser)

        assertTrue(product.issues[anIssueId]!!.isAssigned())
    }

    @Test
    fun markAsDuplicated() {
        val product: Product = createProduct()
        val anIssueId: IssueId = IssueId()
        val anotherIssueId: IssueId = IssueId()

        product.reportDefect(anIssueId, "bb", "aa", Severity.Low)
        product.reportDefect(anotherIssueId, "bb", "aa", Severity.Low)
        product.markIssueAsDuplicate(anIssueId, anotherIssueId)

        assertEquals(anotherIssueId, product.issues[anIssueId]!!.duplicateOf)
    }
}