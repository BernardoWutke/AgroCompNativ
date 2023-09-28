package com.example.agrocompnativ

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrocompnativ.models.SetoresViewModel
import com.example.agrocompnativ.ui.theme.AgroCompNativTheme
import com.example.agrocompnativ.views.ModoView
import com.example.agrocompnativ.views.Home.homeView
import com.example.agrocompnativ.views.viewConfiguracoes


data class BottomNavigationItem(
    val selctIcon: ImageVector,
    val unselectIcon: ImageVector,
    val label: String,
    val selected: Boolean,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: SetoresViewModel = SetoresViewModel()
            AgroCompNativTheme {
                val bottomNavigationItens = listOf(
                    BottomNavigationItem(
                        selctIcon = ImageVector.vectorResource(id = R.drawable.baseline_home_24),
                        unselectIcon = ImageVector.vectorResource(id = R.drawable.baseline_home_24),
                        label = "home",
                        selected = true
                    ) {},
                    BottomNavigationItem(
                        selctIcon = Icons.Filled.Build,
                        unselectIcon = Icons.Filled.Build,
                        label = "temporizador",
                        selected = false
                    ) {},
                    BottomNavigationItem(
                        selctIcon = ImageVector.vectorResource(id = R.drawable.baseline_back_hand_24),
                        unselectIcon = ImageVector.vectorResource(id = R.drawable.baseline_back_hand_24),
                        label = "manual",
                        selected = false
                    ) {}
                )

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF007099),
                    ) {
                    Scaffold(
                        containerColor = Color(0xFF007099),
                        bottomBar = {
                            NavigationBar (
                                containerColor = Color(0xFF338AAA),
                            ) {
                                bottomNavigationItens.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = index == selectedItemIndex,
                                        onClick = {
                                            selectedItemIndex = index
                                            item.onClick()
                                            navController.navigate(item.label)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (item.selected) item.selctIcon else item.unselectIcon,
                                                contentDescription = item.label,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    ) {

                        //navegação
                        paddingValues ->
                        NavHost(navController = navController, startDestination = "home"){
                            composable(
                                route = "manual"
                            ){
                                ModoView(paddingValues, navController, viewModel)
                            }
                            composable(
                                route = "temporizador"
                            ){
                                viewConfiguracoes(paddingValues, navController, viewModel)
                            }
                            composable(
                                route = "home"
                            ){
                                homeView(paddingValues,navController, viewModel)
                            }

                        }
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AgroCompNativTheme {
       // viewConfiguracoes()
    }
}