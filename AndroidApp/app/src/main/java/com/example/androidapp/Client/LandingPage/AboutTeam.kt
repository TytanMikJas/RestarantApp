package com.example.androidapp.Client.LandingPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp.R
import com.example.androidapp.Utils.SatisfyText
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun AboutTeam(descriptions: List<String>) {
    Box(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.TEAM),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onBackground
            )
            TeamElement(image = R.drawable.pudzian, signature = "Mariusz Pudzianowski", description = descriptions[0])
            TeamElement(image = R.drawable.pudzian2, signature = "Mariusz Pudzianowski", description = descriptions[1])
        }
    }
}

@Composable
fun TeamElement(image: Int, signature: String, description: String) {
    Image(
        modifier = Modifier
            .size(220.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(100)),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = image),
        contentDescription = ""
    )

    SatisfyText(
        text = signature,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Text(
        text = description,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp,
        fontFamily = FontFamily.SansSerif,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAboutTeam(){
    AndroidAppTheme {
        AboutChef(ChefDescription)
    }
}