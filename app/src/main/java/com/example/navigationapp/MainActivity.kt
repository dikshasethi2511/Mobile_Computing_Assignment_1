package com.example.navigationapp

import android.os.Bundle
import android.widget.ProgressBar
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationapp.ui.theme.NavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text("Android")
                }
            }
        }
    }
}

@Composable
fun StopElement(modifier: Modifier = Modifier, @StringRes name: Int, @StringRes distance: Int) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
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
                text = stringResource(distance),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun StopsColumn(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 20.dp),
    ) {
        items(stopsData.size) { index ->
            val (nameResId, distanceResId) = stopsData[index]
            StopElement(
                name = nameResId,
                distance = distanceResId,
            )
        }
    }
}


@Composable
fun JourneyDetails(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            "Total Distance Covered: 10.5 km",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            "Total Distance Left: 10.5 km",
            style = MaterialTheme.typography.bodyMedium
        )
        LinearProgressIndicator(
            progress = 0.5f,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun TopButtonsRow(modifier: Modifier = Modifier) {
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
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text(
                    "Reached Stop",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            ElevatedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text(
                    "Switch Units",
                    modifier = Modifier.padding(vertical = 8.dp)
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
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp) // Adjust the padding as needed
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
        TopButtonsRow(modifier = Modifier.weight(1f))
        StopsColumn(modifier = Modifier.weight(2f).padding(bottom = 8.dp))
        JourneyDetails(modifier = Modifier.weight(1f))
    }
}


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
    R.string.stop10_name to R.string.stop10_distance,
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
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun StopsColumnPreview() {
    NavigationAppTheme { StopsColumn() }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun TopButtonsRowPreview() {
    NavigationAppTheme { TopButtonsRow() }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun JourneyDetailsPreview(){
    NavigationAppTheme { JourneyDetails() }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    NavigationAppTheme { MyApp() }
}
