package xyz.partaslabs.kitpass

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.partaslabs.kitpass.ui.ThemedPreview
import xyz.partaslabs.kitpass.ui.home.Home
import xyz.partaslabs.kitpass.ui.theme.KitPassTheme
import xyz.partaslabs.kitpass.utils.PermissionUtility
import android.os.Build
import androidx.core.net.toFile
import androidx.documentfile.provider.DocumentFile
import java.io.*
import java.nio.charset.Charset
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class MainActivity : ComponentActivity() {
    private var TAG = "MainActivity"
    private var passUriState = mutableStateOf<Uri?>(null)
    private lateinit var permissionUtility: PermissionUtility
    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.MANAGE_DOCUMENTS, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
    )

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            checkPermissions()
            passUriState.value = uri
        }

    fun arePermissionsEnabled(): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) return false
        }
        return true
    }

    private fun checkPermissions() {
        if (!arePermissionsEnabled()) {
            requestMultiplePermissions.launch(
                permissions
            )
        } else {
            Log.d(TAG, "Permission Already Granted")
        }


    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.d(TAG, "${it.key} = ${it.value}")
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            KitPassTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Log.v("Test", passUriState.value.toString())
                    passUriState?.value?.let {
                        val type = getApplication().contentResolver.getType(passUriState.value!!)
                        GlobalScope.launch {
                            openZip(passUriState.value!!)
                        }

                        Log.v("Test", "File Type: ${type.toString()}")

                    }

                    Home(selectImageLauncher)
                }
            }
        }
    }

    suspend fun openZip(uri: Uri) {
        val resolver = getApplication().contentResolver
        withContext(Dispatchers.IO) {

            val fileToWrite = File(
                applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                ""
            )
            val folder = File(
                applicationContext.cacheDir,
                "/EmailClient/"
            )
            folder.mkdirs()
            val file = File(uri.getPath());


            Log.v("Test", "Folder: ${folder.absolutePath}")
            Log.v("Test", "file abs: ${file.absolutePath}")




            resolver.openInputStream(uri).use {
                var zipEntry: ZipEntry

                val bis = BufferedInputStream(it)
                val zin = ZipInputStream(bis)

                var ze: ZipEntry? = zin.nextEntry
                while (ze != null) {
                    if(ze.isDirectory) {
                        val f = constructFile(ze.name)
                        Log.v("Test", "Folder: ${f.absolutePath}")
                        if(!f.exists()) if(!f.isDirectory) f.mkdirs()
                    } else {
                        val file = constructFile(ze.name)
                        Log.v("Test", "File Unzip: ${file.absolutePath}")
                        FileOutputStream(file).use { fout ->
                            val buffer = ByteArray(8192)
                            var len = zin.read(buffer)
                            while (len != -1) {
                                fout.write(buffer, 0, len)
                                len = zin.read(buffer)
                            }
                            zin.closeEntry()
                        }

                    }

                    ze = zin.nextEntry
                }

                // Read each entry from the ZipInputStream until no
                // more entry found indicated by a null return value
                // of the getNextEntry() method.

                // Read each entry from the ZipInputStream until no
                // more entry found indicated by a null return value
                // of the getNextEntry() method.
                /**
                var entry: ZipEntry = zis.nextEntry
                while (zis.nextEntry != null) {
                    println("Unzipping: " + entry.name)
                    var size: Int
                    val buffer = ByteArray(2048)
                    FileOutputStream(constructFile(entry.name)).use { fos ->
                        BufferedOutputStream(fos, buffer.size).use { bos ->
                            while (zis.read(buffer, 0, buffer.size).also { size = it } != -1) {
                                bos.write(buffer, 0, size)
                            }
                            bos.flush()
                        }
                    }
                    entry = zis.nextEntry
                }
                */
            }

            /**
            resolver.openInputStream(uri).use {
            val bos = BufferedOutputStream(FileOutputStream(file.absolutePath))
            val bytesIn = ByteArray(4096)
            var read: Int
            while (it?.read(bytesIn).also { x -> read = x!! } != -1) {
            bos.write(bytesIn, 0, read)
            }
            bos.close()
            }
             **/
        }
    }
    fun constructFile(name: String) = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
    "/EmailClient/${name}" )

}
data class UnzippedFile(val filename: String, val content: ByteArray)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemedPreview() {
        Home(null)
    }
}