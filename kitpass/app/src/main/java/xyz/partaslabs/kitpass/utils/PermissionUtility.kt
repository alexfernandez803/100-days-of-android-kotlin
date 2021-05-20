package xyz.partaslabs.kitpass.utils

import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.app.Activity
import android.content.Context
import java.util.ArrayList

class PermissionUtility(private val context: Context, permissions: List<String>) {
    private val PERMISSIONS: List<String>
    fun arePermissionsEnabled(): Boolean {
        for (permission in PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) return false
        }
        return true
    }

    fun requestMultiplePermissions() {
        val remainingPermissions: MutableList<String> = ArrayList()
        for (permission in PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                remainingPermissions.add(permission)
            }
        }
        ActivityCompat.requestPermissions(
            (context as Activity),
            remainingPermissions.toTypedArray(),
            101
        )
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode == 101) {
            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            (context as Activity),
                            permissions[i]!!
                        )
                    ) {
                        requestMultiplePermissions()
                    }
                    return false
                }
            }
        }
        return true
    }

    init {
        PERMISSIONS = permissions
    }
}