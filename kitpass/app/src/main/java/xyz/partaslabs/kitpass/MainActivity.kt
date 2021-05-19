package xyz.partaslabs.kitpass

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import xyz.partaslabs.kitpass.ui.ThemedPreview
import xyz.partaslabs.kitpass.ui.home.Home
import xyz.partaslabs.kitpass.ui.theme.KitPassTheme
import xyz.partaslabs.kitpass.utils.UnzipUtils
import java.io.File
import java.net.URI
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {

    private var passUriState = mutableStateOf<Uri?>(null)

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            passUriState.value = uri
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitPassTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Log.v("Test", passUriState.value.toString())
                    passUriState?.value?.let {
                        val newFile = File(RealPathUtil.getRealPath(
                            applicationContext,
                            it)
                        )
                        UnzipUtils.unzip(newFile, application.cacheDir.absolutePath)
                    }

                    Home(selectImageLauncher)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemedPreview() {
        Home(null)
    }
}