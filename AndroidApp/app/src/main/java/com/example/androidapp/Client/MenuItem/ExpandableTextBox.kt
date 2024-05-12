package com.example.androidapp.Client.MenuItem

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp.R
import com.example.androidapp.ui.theme.AndroidAppTheme

const val DEFAULT_MINIMUM_TEXT_LINE = 3
@Composable
fun ExpandableTextBox(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "...  ",
    textAlign: TextAlign? = TextAlign.Start,
    fontSize: TextUnit = 20.sp
) {
    val isExpanded = remember { mutableStateOf(false) }
    val clickable = remember { mutableStateOf(false) }
    val lastCharIndex = remember { mutableIntStateOf(0) }
    val trimmedText = text.trim()

    Box(modifier = Modifier
        .shadow(
            elevation = 5.dp,
            shape = RoundedCornerShape(16.dp)
        )
        .background(color = MaterialTheme.colorScheme.background)
        .clip(RoundedCornerShape(16.dp))
        .padding(10.dp)
        .clickable(clickable.value) {
            isExpanded.value = !isExpanded.value
        }
        .then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (clickable.value) {
                    if (isExpanded.value) {
                        append(trimmedText)
                    } else {
                        // Display truncated text and "Show More" button when collapsed.
                        val adjustText = text.substring(0..<lastCharIndex.intValue)
                            .dropLast(showMoreText.length)
                        append(adjustText)
                        append(showMoreText)
                    }
                } else {
                    append(trimmedText)
                }
            },
            // Set max lines based on the expanded state.
            maxLines = if (isExpanded.value) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            // Callback to determine visual overflow and enable click ability.
            onTextLayout = { textLayoutResult ->
                if (!isExpanded.value && textLayoutResult.hasVisualOverflow) {
                    clickable.value = true
                    lastCharIndex.intValue = textLayoutResult.getLineEnd((collapsedMaxLine - 1)
                        .coerceAtMost(textLayoutResult.lineCount - 1)
                        .coerceAtLeast(0))
                }
            },
            style = style,
            textAlign = textAlign,
            fontSize = fontSize
        )
        if (clickable.value) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = if (isExpanded.value) R.drawable.chevron_circle_down_f else R.drawable.chevron_circle_down_f),
                    contentDescription = "Expand icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableTextBoxPreview() {
    AndroidAppTheme {
        ExpandableTextBox(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas viverra, libero vitae consequat consequat, lorem enim egestas turpis, sed porttitor ligula felis et ligula. Pellentesque rutrum eros lectus. Integer imperdiet vehicula quam in lobortis. Nunc luctus ante nunc, eget pharetra est laoreet nec. Maecenas condimentum nisl in posuere sagittis. In sollicitudin convallis velit sed condimentum. Sed ac posuere augue. Nam a posuere tellus. Nunc pretium enim non magna hendrerit consectetur. Praesent vitae nisl nec erat ullamcorper tempor.\n" +
                "\n" +
                "Suspendisse dapibus enim eu lorem sagittis, non venenatis erat consequat. Ut mollis mi diam, quis dictum felis pulvinar id. Ut a justo arcu. Nunc ac lectus pharetra, commodo nibh id, pulvinar mi. Vivamus lectus lectus, venenatis quis venenatis quis, iaculis eget diam. Nam volutpat faucibus tincidunt. Aliquam ac lacus ultricies, mollis ipsum eget, feugiat nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Ut ultricies ultricies pretium. Nunc lobortis vitae ligula."
        )
    }
}