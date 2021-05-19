package xyz.partaslabs.kitpass.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.partaslabs.kitpass.data.PassDetails
import xyz.partaslabs.kitpass.ui.ThemedPreview
import xyz.partaslabs.kitpass.ui.pass.EmptyCard
import xyz.partaslabs.kitpass.ui.pass.PassCardSimple
import xyz.partaslabs.kitpass.ui.pass.passes

@Composable
fun PassCardsSimple(
    passes: List<PassDetails>,
    navigateToPassDetails: (String) -> Unit,
) {
    Column {
        if (passes.isEmpty()) {
            EmptyCard(null)
        } else {
            passes.forEach{ details ->
                PassCardSimple(details )
                PostListDivider()
            }
        }

    }
}


@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Preview(name = "PassCards - Light Theme")
@Composable
fun PassCardsPreviewLight() {
    ThemedPreview {
        PassCardsSimple(passes) {}
    }
}

@Preview(name = "EmptyCards - Light Theme")
@Composable
fun EmptyPassCardPreviewLight() {
    ThemedPreview {
        PassCardsSimple(listOf()) {}
    }
}



@Preview(name = "PassCards - Dark Theme")
@Composable
fun PassCardsPreviewDark() {
    ThemedPreview(isDarkTheme = true) {
        PassCardsSimple(passes) {}
    }
}