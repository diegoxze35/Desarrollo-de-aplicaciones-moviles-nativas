package com.damm.artspace.ui.gallery.navigation.composable

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.damm.artspace.R

@Composable
internal fun PermissionRequestScreen(
    modifier: Modifier = Modifier,
    shouldShowRationale: Boolean,
    onLaunchPermissionRequest: () -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        @StringRes val stringResId: Int
        @StringRes val buttonTextResId: Int
        val onClickAction: () -> Unit
        var icon: ImageVector? = null


        if (shouldShowRationale) { //Is not permanently denied
            Icon(
                painter = painterResource(R.drawable.photo_24px), contentDescription = null
            )
            stringResId = R.string.permission_rationale_gallery
            buttonTextResId = R.string.permission_request_button
            onClickAction = onLaunchPermissionRequest
        } else {
            stringResId = R.string.permission_denied_forever_message
            buttonTextResId = R.string.permission_open_settings_button
            icon = Icons.Default.Settings
            val context = LocalContext.current
            onClickAction = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }
        }

        Text(text = stringResource(id = stringResId), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClickAction) {
            icon?.let {
                Icon(imageVector = it, contentDescription = null)
            }
            Text(text = stringResource(id = buttonTextResId))
        }
    }
}

@Preview(
    name = "PermissionsDenied",
    group = "Permissions",
    device = "spec:width=1080px,height=2340px,dpi=440",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun DeniedPermissionPreview() {
    Surface {
        PermissionRequestScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shouldShowRationale = false,
            onLaunchPermissionRequest = {}
        )
    }
}

@Preview(
    name = "PermissionPreview", group = "Permissions", showSystemUi = true,
    showBackground = true, uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PermissionPreview() {
    Surface {
        PermissionRequestScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shouldShowRationale = true,
            onLaunchPermissionRequest = {}
        )
    }
}
