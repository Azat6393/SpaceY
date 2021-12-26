package com.azatberdimyradov.spacey.feature_people_in_space.presentation.people_in_space

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.azatberdimyradov.spacey.R
import com.azatberdimyradov.spacey.core.toDp
import com.azatberdimyradov.spacey.feature_people_in_space.presentation.PeopleInSpaceViewModel
import kotlinx.coroutines.launch

@Composable
fun PeopleInSpaceScreen(
    scaffoldState: ScaffoldState,
    viewModel: PeopleInSpaceViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value

    val earthScrollSpeed = 0.8f

    val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
    val lazyListState = rememberLazyListState()

    val textAlpha = remember { mutableStateOf(0.0f) }

    var earthOffset by remember {
        mutableStateOf(0f)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val layoutInfo = lazyListState.layoutInfo

                textAlpha.value += 0.008f

                // Check if the first item is visible
                if (lazyListState.firstVisibleItemIndex == 0) {
                    return Offset.Zero
                }
                if (layoutInfo.visibleItemsInfo.firstOrNull()?.index != 1) {
                    return Offset.Zero
                }
                if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1) {
                    return Offset.Zero
                }
                earthOffset += delta * earthScrollSpeed
                return Offset.Zero
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .clipToBounds()
                .fillMaxSize()
                .height(imageHeight + earthOffset.toDp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_people_in_space),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .matchParentSize()
                    .padding(top = 50.dp)
                    .graphicsLayer {
                        translationY = earthOffset
                    }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection),
                state = lazyListState
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "People In Space Right Now", fontSize = 40.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 50.dp)
                        )
                        state.peopleInSpace?.number?.let {
                            Text(
                                text = it.toString(), fontSize = 90.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        }
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.padding(top = 20.dp))
                        }
                        if (state.error.isNotBlank()) {
                            Text(
                                text = state.error,
                                color = MaterialTheme.colors.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp)
                            )
                            println(state.error)
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(top = 400.dp))
                }
                state.peopleInSpace?.people?.let { peopleList ->
                    items(peopleList) { people ->
                        AstronautItem(
                            people = people,
                            modifier = Modifier.alpha(textAlpha.value)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                )
            }
        }
    }
}