package com.vishal.bmitrackingapp.domain.repository

import com.vishal.bmitrackingapp.domain.model.BmiRecord

interface BmiRepository{

    suspend fun getLatestBmi(): Result<BmiRecord?>

    suspend fun saveBmi(
        record: BmiRecord
    ): Result<Unit>

    suspend fun getBmiHistory(): Result<List<BmiRecord>>
}