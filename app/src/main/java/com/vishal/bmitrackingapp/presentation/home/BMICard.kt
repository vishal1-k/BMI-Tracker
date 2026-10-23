package com.vishal.bmitrackingapp.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Updater
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vishal.bmitrackingapp.domain.error.AuthError.EmailAlreadyInUse.message
import com.vishal.bmitrackingapp.presentation.home.homeviewmodel.HealthValue

@Composable

fun BmiCard(
    bmi: Double?,
    category: String,
    weight: Double?,
    height: Double?,
    lastUpdated: String,
    isLoading: Boolean
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            val message = when (category) {

                "Underweight" ->
                    "Your BMI is below the healthy range."

                "Normal" ->
                    "Your BMI is within the healthy range."

                "Overweight" ->
                    "Your BMI is above the healthy range."

                "Obese" ->
                    "Your BMI is in the obesity range."

                else ->
                    "Calculate your BMI to see your health status."
            }

            Text(
                text =message,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Healthy BMI range: 18.5 - 24.9",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if(isLoading){

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }

            }else{
                Text(
                    text = bmi?.let{
                        String.format("%.1f",it)
                    }?: "--",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = category.ifEmpty {
                        "No BMI recorded"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(26.dp)
                )

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    HealthValue(
                        title = "Weight",
                        value = weight?.let {
                            "$it kg"
                        } ?: "__"

                    )

                    HealthValue(
                        title  = "Height",
                        value = height?.let {
                            "$it m"
                        }?: "--"
                    )
                }


                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                if (lastUpdated.isNotEmpty()) {

                    Text(
                        text = "Last updated: $lastUpdated",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }


        }
    }
}