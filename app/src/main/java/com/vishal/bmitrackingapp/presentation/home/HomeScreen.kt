package com.vishal.bmitrackingapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.vishal.bmitrackingapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->

            if(event == Lifecycle.Event.ON_RESUME){
                viewModel.refreshBmi()
            }

        }

        lifecycleOwner.lifecycle.addObserver(observer )
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer )
        }
    }

    Scaffold(

        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "BMI Tracker",
                        fontWeight = FontWeight.Bold
                    )
                },

                actions = {

                    IconButton(
                        onClick = {
                            navController.navigate(Screens.ProfileScreen.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.logout()

                            navController.navigate(Screens.LoginScreen.route){
                                popUpTo(Screens.HomeScreen.route){
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout"
                        )
                    }
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddBmiScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add BMI"
                )
            }
        }

    ) { paddingValues ->


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {


            item {
                Text(
                    text = "Hello 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
            }

            item {
                Text(
                    text = "Track your health journey",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(24.dp)
                )
            }



            item {
                BmiCard(
                    bmi = uiState.bmi,
                    category = uiState.bmiCategory,
                    weight = uiState.weight,
                    height = uiState.height,
                    lastUpdated = uiState.lastUpdated,
                    isLoading = uiState.isLoading
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }



            item {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }

            // ✅ FIX: Quick Action Cards are now visible and scrollable
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Add BMI",
                        icon = Icons.Default.Add,
                        onClick = {
                            navController.navigate(Screens.AddBmiScreen.route)
                        }
                    )

                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        title = "History",
                        icon = Icons.Default.History,
                        onClick = {
                            navController.navigate(Screens.HistoryScreen.route)
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
