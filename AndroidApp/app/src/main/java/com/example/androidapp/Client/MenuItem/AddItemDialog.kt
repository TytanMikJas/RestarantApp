package com.example.androidapp.Client.MenuItem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.androidapp.api.dto.Category
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun AddItemDialog(menuItem: MenuDto, onDismiss: () -> Unit, onConfirm: (Int) -> Unit) {
    var quantity by remember { mutableIntStateOf(1) }
    val totalPrice = (if (quantity > 0) quantity else 0) * menuItem.price

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .wrapContentHeight()){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)) {

                Row() {
                    TextField(
                        value = if (quantity > 0) quantity.toString() else "",
                        onValueChange = {
                            quantity = it.toIntOrNull() ?: -1
                        },
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            errorContainerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp,
                        text = "$totalPrice,-"
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.tertiary,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.tertiary
                        ),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary),
                        onClick = { onConfirm(if (quantity > 0) quantity else 0) }) {
                        Text(
                            text = "Dodaj"
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddItemDialog() {
    val onDismiss: () -> Unit = {println()}
    val onConfirm: (Int) -> Unit = {_ -> println()}
    val menuItem = MenuDto(
        3,
        "Kotlet schabowy",
        "Kotlet schabowy z ziemniakami i surówką",
        listOf("gluten"),
        18.50,
        Category.LUNCH,
        "https://example.com/video.mp4",
        listOf("https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg", "https://staticsmaker.iplsc.com/smaker_prod_2019_03_09/fa3c2e12df66513037181b9a3e32181a-lg.jpg"),
    )
    AndroidAppTheme {
        AddItemDialog(menuItem, onDismiss, onConfirm)
    }
}