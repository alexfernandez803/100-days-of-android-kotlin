package xyz.partaslabs.kitpass.ui.home

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.partaslabs.kitpass.ui.pass.EmptyCard
import xyz.partaslabs.kitpass.ui.theme.KitPassTheme

@Composable
fun Home(
    fileOpenLauncher: ActivityResultLauncher<String>?
) {
    Surface(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Column {
            EmptyCard(fileOpenLauncher)
        }
    }

}


@Composable
@Preview
fun PreviewHome() {
    KitPassTheme {
        Home(null)
    }
}
