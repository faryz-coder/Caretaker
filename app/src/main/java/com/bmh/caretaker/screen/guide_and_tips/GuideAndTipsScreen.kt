package com.bmh.caretaker.screen.guide_and_tips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.model.ConstantGuideAndTips.guideAndTipsTitle
import com.bmh.caretaker.model.ConstantGuideAndTips.listGuideAndTips
import com.bmh.caretaker.screen.diet_guides.ItemColumn
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun GuideAndTipsScreen(
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
                items(listGuideAndTips) {
                    if (listGuideAndTips.indexOf(it) == 0) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = guideAndTipsTitle, fontWeight = FontWeight.Bold)
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
@Preview(showBackground = true)
fun PreviewGuideAndTipsScreen() {
    GuideAndTipsScreen(viewModel = MainViewModel())
}