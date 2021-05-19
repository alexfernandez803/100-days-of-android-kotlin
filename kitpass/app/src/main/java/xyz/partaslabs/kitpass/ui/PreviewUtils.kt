package xyz.partaslabs.kitpass.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import xyz.partaslabs.kitpass.ui.theme.KitPassTheme

@Composable
internal fun ThemedPreview(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    KitPassTheme(darkTheme = isDarkTheme) {
        Surface() {
            content()
        }
    }
}