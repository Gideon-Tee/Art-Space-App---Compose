package com.gideon.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gideon.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceTheme {
                        ArtSpaceApp()
                    }
                }
            }
        }
    }
}

@Composable
fun ArtImage(
    image: Int,
    modifier: Modifier = Modifier

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .border(width = 2.dp, color = Color.DarkGray, shape = RectangleShape)
                .padding(10.dp)

        )
    }
}

@Composable
fun DescriptionText(
    @StringRes imageDescription: Int,
    @StringRes imageAuthor: Int
) {
    Card(
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = stringResource(id = imageDescription),
                fontSize = 22.sp,
                fontWeight = FontWeight.Light

            )

            Text(
                text = stringResource(id = imageAuthor),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Composable
fun Buttons(
    previous_onClick: () -> Unit,
    next_onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick =
//                if (num == 0) num = 4 else num--
            previous_onClick,
            Modifier.weight(1f)
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(50.dp))

        Button(
            onClick =
//                      if (num==4) num =0 else num++
            next_onClick,
            Modifier.weight(1f)
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var num by remember { mutableStateOf(0) }

    val pictures = arrayListOf(
        R.drawable.art1,
        R.drawable.art2,
        R.drawable.art3,
        R.drawable.art4,
        R.drawable.art5
    )
    val descriptions = arrayListOf(
        R.string.art1_desc,
        R.string.art2_desc,
        R.string.art3_desc,
        R.string.art4_desc,
        R.string.art5_desc
    )
    val authors = arrayListOf(
        R.string.art1_author,
        R.string.art2_author,
        R.string.art3_author,
        R.string.art4_author,
        R.string.art5_author,
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        ArtImage(image = pictures[num])
        Spacer(modifier = Modifier.height(25.dp))
        DescriptionText(descriptions[num], authors[num])
        Spacer(modifier = Modifier.height(50.dp))
        Buttons(
            previous_onClick = {
                if (num == 0) num = 4 else num--
            },
            next_onClick = {
                if (num == 4) num = 0 else num++
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}