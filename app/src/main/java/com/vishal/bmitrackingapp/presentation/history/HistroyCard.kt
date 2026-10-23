package com.vishal.bmitrackingapp.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vishal.bmitrackingapp.core.util.formatDate
import com.vishal.bmitrackingapp.domain.model.BmiRecord


@Composable
 fun BmiHistoryCard(
    record: BmiRecord
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "BMI ${
                    String.format(
                        "%.1f",
                        record.bmi
                    )
                }",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = record.category,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Weight: ${record.weight} kg"
            )

            Text(
                text = "Height: ${record.height} m"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = formatDate(record.timestamp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}