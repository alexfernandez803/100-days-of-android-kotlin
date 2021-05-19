package xyz.partaslabs.kitpass.ui.pass

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.partaslabs.kitpass.data.PassDetails
import xyz.partaslabs.kitpass.ui.ThemedPreview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier


@Composable
fun PassCardSimple(
    passDetails: PassDetails,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = { })
            .padding(16.dp)
            .semantics {
                // By defining a custom action, we tell accessibility services that this whole
                // composable has an action attached to it. The accessibility service can choose
                // how to best communicate this action to the user.
                customActions = listOf()
            }
    ) {
        PassImage(passDetails, Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            PassTitle(passDetails)
            BriefDetails(passDetails)
        }
        NavigateButton(false, onClick = {})
    }
}

@Composable
fun EmptyCard(
    fileOpenLauncher: ActivityResultLauncher<String>?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Looks Like you don't have pass",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row(

        ) {
            Button(onClick = {
                fileOpenLauncher?.launch("application/vnd.apple.pkpass")
            }) {
                Text(text = "Add a pass", modifier = Modifier.padding(end = 16.dp))
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null // handled by click label of parent
                )
            }

        }
    }
}

@Composable
fun PassTitle(passDetails: PassDetails) {
    Text(passDetails.title, style = MaterialTheme.typography.subtitle1)
}

@Composable
fun BriefDetails(
    passDetails: PassDetails,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            val textStyle = MaterialTheme.typography.body2
            Text(
                text = "Use by ",
                style = textStyle
            )
            Text(
                text = "March 21 2021 10:00pm",
                style = textStyle
            )
        }
    }
}


@Composable
fun NavigateButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickLabel = ""
    IconToggleButton(
        checked = isBookmarked,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            // Use a custom click label that accessibility services can communicate to the user.
            // We only want to override the label, not the actual action, so for the action we pass null.
            this.onClick(label = clickLabel, action = null)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = null // handled by click label of parent
        )
    }
}


@Composable
fun PassImage(passDetails: PassDetails, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(passDetails.imageThumbId),
        contentDescription = null,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
@Preview(name = "Empty pass card")
fun PreviewEmptyCards() {
    ThemedPreview {
        EmptyCard(null)
    }
}


@Composable
@Preview(name = "Simple pass card")
fun PreviewCard() {
    ThemedPreview {
        PassCardSimple(pass1)
    }
}

@Composable
@Preview(name = "Simple pass card dark theme")
fun DarkPreviewCard() {
    ThemedPreview(isDarkTheme = true) {
        PassCardSimple(pass1)
    }
}