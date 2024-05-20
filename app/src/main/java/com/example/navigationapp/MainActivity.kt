package com.example.navigationapp

import LightOrangeColorScheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationapp.ui.theme.NavigationAppTheme
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun StopElement(
    modifier: Modifier = Modifier,
    @StringRes name: Int,
    @StringRes distance: Int,
    isMetricUnit: Boolean,
    isCurrentStop: Boolean,
    isCovered: Boolean
) {

    val backgroundColor = when {
        isCurrentStop -> Color(0xFFFFEB3B)  // Highlight current stop with yellow background.
        isCovered -> Color(0xFF4CAF50) // Mark covered stops with green background.
        else -> LightOrangeColorScheme.primary // Default background color.
    }
    Surface(
        color = backgroundColor,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = if (isMetricUnit) {
                    stringResource(distance) + " km"
                } else {
                    convertKmToMiles(stringResource(distance).toFloat()) + " miles"
                },
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

private fun convertKmToMiles(km: Float): String {
    // Convert kilometers to miles using the conversion factor.
    val miles = km * 0.621371
    // Format the result to two decimal place.
    return String.format("%.2f", miles)
}

@Composable
fun StopsColumn(
    modifier: Modifier = Modifier, isMetricUnit: Boolean, nextStopIndex: Int,
    distanceCovered: Float
) {
    if (stopsData.size > 10) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 20.dp),
        ) {
            items(stopsData.size) { index ->
                val (nameResId, distanceResId) = stopsData[index]
                val isCurrentStop = index == nextStopIndex
                val isCovered = index < nextStopIndex
                StopElement(
                    name = nameResId,
                    distance = distanceResId,
                    isMetricUnit = isMetricUnit,
                    isCurrentStop = isCurrentStop,
                    isCovered = isCovered,
                )
            }
        }

    } else {
        Column(
            modifier = modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            stopsData.forEachIndexed { index, (nameResId, distanceResId) ->
                val isCurrentStop = index == nextStopIndex
                val isCovered = index < nextStopIndex
                StopElement(
                    name = nameResId,
                    distance = distanceResId,
                    isMetricUnit = isMetricUnit,
                    isCurrentStop = isCurrentStop,
                    isCovered = isCovered,
                )
            }
        }
    }
}


@Composable
fun JourneyDetails(
    modifier: Modifier = Modifier, totalDistance: Float,
    distanceCovered: Float, isMetricUnit: Boolean,
) {

    val distanceLeft = totalDistance - distanceCovered
    Column(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (!isMetricUnit) {
            val distanceCoveredInMiles = convertKmToMiles(distanceCovered)
            val distanceLeftInMiles = convertKmToMiles(distanceLeft)
            Text(
                "Total Distance Covered: $distanceCoveredInMiles miles",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                "Total Distance Left: $distanceLeftInMiles miles",
                style = MaterialTheme.typography.titleMedium,
            )
        } else {
            Text(
                "Total Distance Covered: ${distanceCovered.toString()} km",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                "Total Distance Left: ${distanceLeft.toString()} km",
                style = MaterialTheme.typography.titleMedium,
            )
        }

        LinearProgressIndicator(
            progress = distanceCovered / totalDistance,
            modifier = Modifier.fillMaxWidth(),
            color = LightOrangeColorScheme.secondary,
        )
    }
}

@Composable
fun TopButtonsRow(
    modifier: Modifier = Modifier, isMetricUnit: Boolean,
    onUnitSwitchClick: () -> Unit,
    onReachedStopClick: () -> Unit,
    onRestartJourney: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(
                onClick = { onReachedStopClick() },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightOrangeColorScheme.secondary,
                    contentColor = LightOrangeColorScheme.onSecondaryContainer
                )

            ) {
                Text(
                    "Reached Stop",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            ElevatedButton(
                onClick = { onUnitSwitchClick() },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightOrangeColorScheme.secondary,
                    contentColor = LightOrangeColorScheme.onSecondaryContainer
                )
            ) {
                Text(
                    if (isMetricUnit) "Switch to Miles" else "Switch to Km",
                    modifier = Modifier.padding(vertical = 4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(
                onClick = { onRestartJourney() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightOrangeColorScheme.secondary,
                    contentColor = LightOrangeColorScheme.onSecondaryContainer
                )
            ) {
                Text(
                    "Restart Journey",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val distanceCovered = remember { mutableFloatStateOf(0.0f) }
        val nextStopIndex = remember { mutableIntStateOf(0) }
        val isMetricUnit = remember { mutableStateOf(true) }
        val journeyFinished = remember { mutableStateOf(false) }
        val totalJourneyDistance = getTotalDistance()
        val nextDistance: Float = if (!journeyFinished.value) {
            fetchNextStopDistance(index = nextStopIndex.intValue).toFloat()
        } else {
            0f
        }
        TopButtonsRow(modifier = Modifier.weight(1f), isMetricUnit = isMetricUnit.value,
            onUnitSwitchClick = { isMetricUnit.value = !isMetricUnit.value },
            onReachedStopClick = {
                if (!journeyFinished.value) {
                    if (nextStopIndex.intValue == stopsData.size - 1) {
                        journeyFinished.value = true
                    }
                    distanceCovered.floatValue += nextDistance
                    nextStopIndex.intValue++
                }
            },
            onRestartJourney = {
                // Reset journey variables
                distanceCovered.floatValue = 0.0f
                nextStopIndex.intValue = 0
                journeyFinished.value = false
            }
        )
        StopsColumn(
            modifier = Modifier
                .weight(2f)
                .padding(bottom = 8.dp),
            isMetricUnit = isMetricUnit.value, nextStopIndex = nextStopIndex.intValue,
            distanceCovered = distanceCovered.floatValue,
        )
        JourneyDetails(
            modifier = Modifier.weight(1f), totalDistance = totalJourneyDistance,
            distanceCovered = distanceCovered.floatValue, isMetricUnit = isMetricUnit.value

        )
    }
}

@Composable
private fun fetchNextStopDistance(index: Int): String {
    return stringResource(id = stopsData[index].second)
}

@Composable
private fun getTotalDistance(): Float {
    var totalDistance = 0f

    for ((_, distanceResId) in stopsData) {
        totalDistance += stringResource(distanceResId).toFloat()
    }

    return totalDistance
}


//private val stopsData = listOf(
//    R.string.stop1_name to R.string.stop1_distance,
//    R.string.stop2_name to R.string.stop2_distance,
//    R.string.stop3_name to R.string.stop3_distance,
//    R.string.stop4_name to R.string.stop4_distance,
//    R.string.stop5_name to R.string.stop5_distance,
//    R.string.stop6_name to R.string.stop6_distance,
//    R.string.stop7_name to R.string.stop7_distance,
//    R.string.stop8_name to R.string.stop8_distance,
//    R.string.stop9_name to R.string.stop9_distance,
//    R.string.stop10_name to R.string.stop10_distance,
//    R.string.stop11_name to R.string.stop11_distance,
//    R.string.stop12_name to R.string.stop12_distance,
//).map { (nameResId, distanceResId) ->
//    nameResId to distanceResId
//}

private val stopsData = listOf(
    R.string.stop1_name to R.string.stop1_distance,
    R.string.stop2_name to R.string.stop2_distance,
    R.string.stop3_name to R.string.stop3_distance,
    R.string.stop4_name to R.string.stop4_distance,
    R.string.stop5_name to R.string.stop5_distance,
    R.string.stop6_name to R.string.stop6_distance,
    R.string.stop7_name to R.string.stop7_distance,
    R.string.stop8_name to R.string.stop8_distance,
    R.string.stop9_name to R.string.stop9_distance,
).map { (nameResId, distanceResId) ->
    nameResId to distanceResId
}

@Preview(showBackground = true)
@Composable
fun StopElementPreview() {
    NavigationAppTheme {
        StopElement(
            name = R.string.stop1_name,
            distance = R.string.stop1_distance,
            modifier = Modifier.padding(8.dp),
            isMetricUnit = true,
            isCurrentStop = true,
            isCovered = false
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun StopsColumnPreview() {
    NavigationAppTheme {
        StopsColumn(
            isMetricUnit = true,
            nextStopIndex = 1,
            distanceCovered = 1.3f
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun TopButtonsRowPreview() {
    NavigationAppTheme {
        TopButtonsRow(
            isMetricUnit = true,
            onUnitSwitchClick = {},
            onReachedStopClick = {},
            onRestartJourney = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun JourneyDetailsPreview() {
    NavigationAppTheme {
        JourneyDetails(
            distanceCovered = 4.5f,
            totalDistance = 10.7f,
            isMetricUnit = true
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    NavigationAppTheme { MyApp() }
}
