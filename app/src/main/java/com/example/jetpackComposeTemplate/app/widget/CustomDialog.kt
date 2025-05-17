package com.example.jetpackComposeTemplate.app.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpackComposeTemplate.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlin.collections.forEach

enum class DialogType {
    Success, Error, Info, TimeOut, Confirmation
}

data class DialogButton(
    val text: String,
    val onClick: () -> Unit
)

@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    type: DialogType,
    title: String,
    message: String,
    buttons: List<DialogButton>? = null
) {
    if (showDialog) {
        Dialog(onDismissRequest = {}) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Always load the Lottie animation using a dedicated file for each dialog type.
                    var isAnimationComplete by remember { mutableStateOf(false) }
                    val lottieFile = when (type) {
                        DialogType.Success -> R.raw.success_tick2
                        DialogType.Error -> R.raw.error_animation
                        DialogType.Info -> R.raw.info_animation
                        DialogType.TimeOut -> R.raw.time_out
                        DialogType.Confirmation -> R.raw.confirm_anim
                    }
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieFile))
                    val progress by animateLottieCompositionAsState(composition)
                    // For non-confirmation dialogs, wait for the animation to complete.
                    if (type != DialogType.Confirmation) {
                        LaunchedEffect(progress) {
                            if (progress >= 1f) {
                                delay(100)
                                isAnimationComplete = true
                            }
                        }
                    } else {
                        // For confirmation dialogs, show the content immediately
                        isAnimationComplete = true
                    }
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.size(100.dp)
                    )

                    AnimatedVisibility(
                        visible = isAnimationComplete,
                        enter = slideInVertically(
                            initialOffsetY = { -50 },
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        ) + fadeIn(animationSpec = tween(durationMillis = 500))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            // Title
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            // Message
                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            // Determine button colors based on type.
                            val buttonColors = when (type) {
                                DialogType.Success -> MaterialTheme.colorScheme.primary
                                DialogType.Error -> MaterialTheme.colorScheme.error
                                DialogType.Info -> MaterialTheme.colorScheme.tertiary
                                DialogType.TimeOut -> colorResource(R.color.teal_700)
                                DialogType.Confirmation -> MaterialTheme.colorScheme.primary
                            }
                            val dialogButtons = buttons ?: if (type == DialogType.Confirmation) {
                                listOf(
                                    DialogButton("Yes") { onDismiss() },
                                    DialogButton("No") { onDismiss() }
                                )
                            } else {
                                listOf(DialogButton("OK") { onDismiss() })
                            }
                            dialogButtons.forEach { button ->
                                OutlinedButton(
                                    onClick = button.onClick,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .height(48.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = buttonColors
                                    ),
                                    border = BorderStroke(2.dp, buttonColors)
                                ) {
                                    Text(
                                        text = button.text,
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessDialogPreview() {
    CustomDialog(
        showDialog = true,
        onDismiss = { /* No-op */ },
        type = DialogType.Success,
        title = "Success",
        message = "Your operation was successful.",
        buttons = listOf(
            DialogButton("OK") { /* Dismiss Logic */ }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview() {
    CustomDialog(
        showDialog = true,
        onDismiss = { /* No-op */ },
        type = DialogType.Error,
        title = "Error",
        message = "Something went wrong. Please try again.",
        buttons = listOf(
            DialogButton("OK") { /* Dismiss Logic */ }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun InfoDialogPreview() {
    CustomDialog(
        showDialog = true,
        onDismiss = { /* No-op */ },
        type = DialogType.Info,
        title = "Information",
        message = "Please read and acknowledge this information.",
        buttons = listOf(
            DialogButton("OK") { /* Dismiss Logic */ }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TimeOutDialogPreview() {
    CustomDialog(
        showDialog = true,
        onDismiss = { /* No-op */ },
        type = DialogType.TimeOut,
        title = "Time-Out",
        message = "Session timed out",
        buttons = listOf(
            DialogButton("OK") { /* Dismiss Logic */ }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ConfirmationDialogPreview() {
    CustomDialog(
        showDialog = true,
        onDismiss = { /* Dismiss Logic */ },
        type = DialogType.Confirmation,
        title = "Cancel Installation",
        message = "Do you want to cancel the installation?",
        buttons = listOf(
            DialogButton("Yes") { /* Confirm Cancellation Logic */ },
            DialogButton("No") { /* Dismiss Logic */ }
        )
    )
}
