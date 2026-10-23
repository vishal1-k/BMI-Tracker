package com.vishal.bmitrackingapp.domain.util



object BmiUnitConverter {

    fun kgToKg(weight: Double): Double {
        return weight
    }

    fun lbToKg(weight: Double): Double {
        return weight * 0.45359237
    }

    fun cmToMeter(height: Double): Double {
        return height / 100.0
    }

    fun inchToMeter(height: Double): Double {
        return height * 0.0254
    }
}