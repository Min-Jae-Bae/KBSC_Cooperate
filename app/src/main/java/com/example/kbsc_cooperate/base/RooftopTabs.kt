package com.example.kbsc_cooperate.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import com.example.kbsc_cooperate.home.OnExploreItemClicked
import com.example.kbsc_cooperate.home.RooftopBottomBarItem

@Preview
@Composable
fun PreviewCraneTabBar() {
    RooftopTabBar(onMenuClicked = { }) {

    }
}

@Composable
fun RooftopTabBar(
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        // Separate Row as the children shouldn't have the padding
        Row(Modifier.padding(top = 8.dp)) {
            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onMenuClicked),
                painter = painterResource(id = R.drawable.ic_reservationlist),
                contentDescription = stringResource(id = R.string.cd_reservationlist)
            )
            Spacer(Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onMenuClicked),
                painter = painterResource(id = R.drawable.search),
                contentDescription = stringResource(id = R.string.cd_search)
            )
            Spacer(Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onMenuClicked),
                painter = painterResource(id = R.drawable.like),
                contentDescription = stringResource(id = R.string.cd_like)
            )
            Spacer(Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onMenuClicked),
                painter = painterResource(id = R.drawable.myprofile),
                contentDescription = stringResource(id = R.string.cd_myprofile)
            )
        }
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun RooftopTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: RooftopBottomBarItem,
    onTabSelected: (RooftopBottomBarItem) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
        contentColor = MaterialTheme.colors.onSurface,
        indicator = { tabPositions: List<TabPosition> ->
            Box {
                Modifier
                    .tabIndicatorOffset(tabPositions[tabSelected.ordinal])
                    .fillMaxSize()
                    .padding(horizontal = 4.dp)
                    .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(16.dp))
            }
        },
        divider = { }
        ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal
            val textModifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)

            Tab(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(16.dp)),
                selected = selected,
                onClick = {
                    onTabSelected(RooftopBottomBarItem.values()[index])
                }
            ) {
                Text(
                    modifier = textModifier,
                    text = title.uppercase(
                        ConfigurationCompat.getLocales(LocalConfiguration.current)[0]
                    )
                )
            }
        }
    }
}
//미리보기
@Preview
@Composable
fun PreviewRooftopTabs() {
    RooftopTabs(titles = listOf("ReservationList", "Search", "Like", "MyProfile"),
        tabSelected = RooftopBottomBarItem.Search, onTabSelected = {})
}