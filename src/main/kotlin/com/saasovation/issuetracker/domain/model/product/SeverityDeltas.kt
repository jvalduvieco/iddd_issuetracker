package com.saasovation.issuetracker.domain.model.product

class SeverityDeltas(val highDelta: Int, val mediumDelta: Int, val lowDelta: Int) {
    init {
        if (highDelta > 1 || highDelta < -1) throw IllegalArgumentException("Delta must be 1, 0, or -1.")
        if (lowDelta < 1 || lowDelta > -1) throw IllegalArgumentException("Delta must be 1, 0, or -1.")
        if (mediumDelta < 1 || mediumDelta > -1) throw IllegalArgumentException("Delta must be 1, 0, or -1.")
    }
}
