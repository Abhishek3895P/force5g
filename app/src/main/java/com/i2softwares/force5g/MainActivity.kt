package com.i2softwares.force5g

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.i2softwares.force5g.ui.screens.Aboutscreen
import com.i2softwares.force5g.ui.screens.HomeScreen
import com.i2softwares.force5g.ui.theme.Force5gTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Force5gTheme {
                Force5gApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun Force5gApp() {

    val context = LocalContext.current
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var menuExpanded by remember { mutableStateOf(false) }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
                topBar = {

            var menuExpanded by remember { mutableStateOf(false) }

            TopAppBar(
                title = {
                    Text(currentDestination.label)
                },

                actions = {

                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {

                        DropdownMenuItem(
                            text = { Text("About Us") },
                            onClick = {
                                currentDestination= AppDestinations.PROFILE
                                menuExpanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Feedback") },
                            onClick = {
                                val email = "i234678i234@gmail.com"
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:")
                                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                                }
                                context.startActivity(intent, null)
                                menuExpanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Share App") },
                            onClick = {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        "Check out this awesome Permanent 5g app: " +
                                                "https://play.google.com/store/apps/details?id=${context.packageName}"
                                    )
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share App"), null)

                                menuExpanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Rate Us") },
                            onClick = {
                                val url = "https://play.google.com/store/apps/details?id=${context.packageName}"
                                context.startActivity(
                                    Intent(Intent.ACTION_VIEW, Uri.parse(url)), null
                                )
                                menuExpanded = false
                            }
                        )
                    }
                }
            )
        }
        ) { innerPadding ->
            when (currentDestination) {

                AppDestinations.HOME -> {
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }



                AppDestinations.PROFILE -> {
                    Aboutscreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("5G Only", Icons.Default.Home),
    PROFILE("About us", Icons.Default.AccountCircle),
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Force5gTheme {
        Greeting("Android")
    }
}




