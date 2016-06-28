package com.saasovation.issuetracker.domain.model.product.release

import com.saasovation.issuetracker.domain.model.product.SeverityTotals
import com.saasovation.issuetracker.domain.model.product.SeverityWeights

class DefectDensitiesCalculator(val severityTotals: SeverityTotals, val severityWeights: SeverityWeights) {

    internal fun defectDensity(measurement: Measurement): DefectDensity? {
        // TODO: ...
        return null
    }
}
