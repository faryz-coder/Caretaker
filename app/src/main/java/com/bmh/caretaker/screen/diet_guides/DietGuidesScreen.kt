package com.bmh.caretaker.screen.diet_guides

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.model.ConstantDietGuide.dietGuideDescription
import com.bmh.caretaker.model.ConstantDietGuide.listDietGuide
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun DietGuidesScreen(
    viewModel: MainViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(listDietGuide) {
                    if (listDietGuide.indexOf(it) == 0) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = "Cancer Diet Guide", fontWeight = FontWeight.Bold)
                            Text(text = dietGuideDescription)
                        }
                        Box(modifier = Modifier.height(20.dp)) {}
                    }
                    ItemColumn(
                        icon = it.icon,
                        title = it.title,
                        list = it.list
                    )
                }
            }

        }
    }
}

@Composable
fun ItemColumn(icon: Int, title: String, list: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier.size(150.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = icon),
                    contentDescription = "fruits_and_vegetable_icon"
                )
            }
        }
        list.forEach {
            Row {
                Canvas(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                        .size(6.dp)
                ) {
                    drawCircle(Color.Black)
                }
                Text(text = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDietGuidesScreen() {
    DietGuidesScreen(viewModel = MainViewModel())
}