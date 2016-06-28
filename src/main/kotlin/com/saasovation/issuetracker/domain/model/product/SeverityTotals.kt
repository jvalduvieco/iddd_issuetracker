package com.saasovation.issuetracker.domain.model.product

class SeverityTotals(val totalHigh: Int, val totalMedium: Int, val totalLow: Int) {

    fun adjustWith(deltas: SeverityDeltas): SeverityTotals {
        val totalHigh = this.totalHigh + deltas.highDelta
        val totalMedium = this.totalMedium + deltas.mediumDelta
        val totalLow = this.totalLow + deltas.lowDelta

        return SeverityTotals(totalHigh, totalMedium, totalLow)
    }

    fun total(): Double {
        return this.totalHigh + this.totalMedium + this.totalLow.toDouble()
    }

    fun weightedTotal(severityWeights: SeverityWeights): Double {
        val highValue = this.totalHigh * severityWeights.highWeight

        val mediumValue = this.totalMedium * severityWeights.mediumWeight

        val lowValue = this.totalLow * severityWeights.lowWeight

        return highValue + mediumValue + lowValue
    }
}
