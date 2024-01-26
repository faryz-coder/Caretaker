package com.bmh.caretaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmh.caretaker.navigation.MainNavGraph
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.ui.theme.CaretakerTheme
import com.bmh.caretaker.utils.AuthManager
import com.bmh.caretaker.utils.SharedPreferenceManager
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val sharedPref =
            this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        mainViewModel.sharedPreferenceManager = SharedPreferenceManager(sharePref = sharedPref)
        setContent {
            CaretakerTheme {
                navHostController = rememberNavController()
                mainViewModel.navController = navHostController

                MainBody(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBody(mainViewModel: MainViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isHome by remember {
        mutableStateOf(true)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                DrawerContent(mainViewModel, { scope.launch { drawerState.close() } })
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Caretaker")
                    },
                    navigationIcon = {
                        if (isHome)
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        else
                            IconButton(onClick = {
                                scope.launch {
                                    mainViewModel.navController.popBackStack()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.ArrowBackIosNew,
                                    contentDescription = "Back Button"
                                )
                            }
                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Rounded.MoreVert,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        focusManager.clearFocus()
                    }, color = MaterialTheme.colorScheme.background
            ) {
                MainNavGraph(mainViewModel = mainViewModel) { isHome = it }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(mainViewModel: MainViewModel, closeDrawer: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("Test@gmail.com") }
    try {
        email = AuthManager().auth.currentUser?.email ?: ""
    } catch (e: Exception) { }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card {
                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = email.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.ROOT
                                    ) else it.toString()
                                }
                            )
                        }
                        Box( Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(width = 1.dp, color = Color.Black, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Rounded.Person, contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "",
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ElevatedCard(
                    onClick = {
                        mainViewModel.navController.navigate(Screen.PatientInformationScreen.route)
                        closeDrawer.invoke()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Patient Information")
                    }
                }
                ElevatedCard(
                    onClick = {
                        mainViewModel.navController.navigate(Screen.DailyMonitoring.route)
                        closeDrawer.invoke()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Daily Monitoring")
                    }
                }
                ElevatedCard(
                    onClick = {
                        mainViewModel.navController.navigate(Screen.ReminderScreen.route)
                        closeDrawer.invoke()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Reminder")
                    }
                }
                ElevatedCard(
                    onClick = {
                        mainViewModel.navController.navigate(Screen.MedicalNotesScreen.route)
                        closeDrawer.invoke()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Medical Notes")
                    }
                }
                ElevatedCard(
                    onClick = {
                        mainViewModel.navController.navigate(Screen.DietGuideScreen.route)
                        closeDrawer.invoke()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Diet Guides")
                    }
                }
                ElevatedCard(
                    onClick = {
                        mainViewModel.navController.navigate(Screen.GuideAndTipsScreen.route)
                        closeDrawer.invoke()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Guide & Tips")
                    }
                }
            }
            ElevatedCard(
                onClick = {
                    try {
                        AuthManager().logout {
                            val intent = Intent(context, LoginActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        }
                    } catch (e: Exception) {
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Rounded.Logout, contentDescription = "LOGOUT")
                        Text(text = "Logout")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    val mainViewModel = MainViewModel()
    mainViewModel.navController = rememberNavController()
    MainBody(mainViewModel = mainViewModel)
}