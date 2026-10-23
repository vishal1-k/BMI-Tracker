package com.vishal.bmitrackingapp.presentation.addbmi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vishal.bmitrackingapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBmiScreen(
    navController: NavController,
    viewModel: AddBmiViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    // ✅ FIX #4: Properly reset state after navigation
    LaunchedEffect(uiState.isSaved) {

        if (uiState.isSaved) {
            // Clear the saved state BEFORE navigating
            // This prevents automatic pop-back when returning to this screen
            viewModel.clearSavedState()

            // Small delay to ensure state is updated
            kotlinx.coroutines.delay(100)

            // Navigate back to home
            navController.popBackStack()
        }
    }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Add BMI",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {


            Text(
                text = "Gender"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FilterChip(
                    selected = uiState.gender == Gender.MALE,
                    onClick = {
                        viewModel.onGenderChange(Gender.MALE)
                    },
                    label = {
                        Text("Male")
                    }
                )

                FilterChip(
                    selected = uiState.gender == Gender.FEMALE,
                    onClick = {
                        viewModel.onGenderChange(Gender.FEMALE)
                    },
                    label = {
                        Text("Female")
                    }
                )

                FilterChip(
                    selected = uiState.gender == Gender.OTHER,
                    onClick = {
                        viewModel.onGenderChange(Gender.OTHER)
                    },
                    label = {
                        Text("Other")
                    }
                )
            }

            Text(
                text = "Weight Unit"
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FilterChip(
                    selected = uiState.weightUnit == WeightUnit.KG,
                    onClick = {
                        viewModel.onWeightUnitChange(
                            WeightUnit.KG
                        )
                    },
                    label = {
                        Text("KG")
                    }
                )

                FilterChip(
                    selected = uiState.weightUnit == WeightUnit.LB,
                    onClick = {
                        viewModel.onWeightUnitChange(
                            WeightUnit.LB
                        )
                    },
                    label = {
                        Text("LB")
                    }
                )
            }

            Text(
                text = "Height Unit"
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FilterChip(
                    selected = uiState.heightUnit == HeightUnit.CM,
                    onClick = {
                        viewModel.onHeightUnitChange(
                            HeightUnit.CM
                        )
                    },
                    label = {
                        Text("CM")
                    }
                )

                FilterChip(
                    selected = uiState.heightUnit == HeightUnit.INCH,
                    onClick = {
                        viewModel.onHeightUnitChange(
                            HeightUnit.INCH
                        )
                    },
                    label = {
                        Text("Inches")
                    }
                )
            }


            Text(
                text = "Enter your details",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            OutlinedTextField(

                value = uiState.weight,

                onValueChange = {
                    viewModel.onWeightChange(it)
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Weight")
                },

                placeholder = {
                    Text("Enter weight in ${uiState.weightUnit.name.lowercase()}")
                },


                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),

                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(

                value = uiState.height,

                onValueChange = {
                    viewModel.onHeightChange(it)
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Height")
                },

                placeholder = {
                    Text("Enter height in ${
                        if (uiState.heightUnit == HeightUnit.CM)
                            "cm"
                        else
                            "inches"
                    }")
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),

                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(

                onClick = {
                    viewModel.calculateBmi()
                },

                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Calculate BMI"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            uiState.bmi?.let { bmi ->

                Text(
                    text = "Your BMI",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = String.format("%.1f", bmi),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = uiState.category,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Button(

                    onClick = {
                        viewModel.saveBmi()

                            navController.navigate(Screens.HomeScreen.route)


                    },

                    modifier = Modifier.fillMaxWidth(),

                    enabled = !uiState.isLoading
                ) {



                        Text(
                            text = "Save BMI",

                        )

                }
            }


            uiState.errorMessage?.let { error ->

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
