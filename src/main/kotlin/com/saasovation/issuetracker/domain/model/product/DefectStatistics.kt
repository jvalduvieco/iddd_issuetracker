package com.saasovation.issuetracker.domain.model.product

data class DefectStatistics(
        val defectsReported: Int,
        val defectsFixed: Int,
        val defectsKnown: Int,
        val KLOC: Int) {
    val defectDensity: Double = defectsKnown.toDouble() / KLOC.toDouble()
}
