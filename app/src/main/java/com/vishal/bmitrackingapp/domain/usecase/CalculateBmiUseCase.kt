package com.vishal.bmitrackingapp.domain.usecase

import com.vishal.bmitrackingapp.domain.model.BmiResult
import javax.inject.Inject


class CalculateBmiUseCase @Inject constructor(){
    operator fun invoke(
        weight: Double,
        height: Double
    ): Result<BmiResult>{
        if(weight<=0){
            return Result.failure(
                IllegalArgumentException(
                    "Weight must be greater than 0"
                )
            )
        }
        if(height <=0){
            return Result.failure(
                IllegalArgumentException(
                    "Height must be greater then 0"
                )
            )
        }

        val bmi = weight/ (height*height)
        val category = when {

            bmi < 18.5 ->
                "Underweight"

            bmi < 25.0 ->
                "Normal"

            bmi < 30.0 ->
                "Overweight"

            else ->
                "Obese"


        }

        return Result.success(
            BmiResult(
                bmi = bmi,
                category = category
            )
        )

    }
}