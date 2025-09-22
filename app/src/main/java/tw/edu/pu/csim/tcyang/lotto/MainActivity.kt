package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Play(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier = Modifier) {
    // Get the current context for showing Toast
    val context = LocalContext.current

    // State to hold the random lucky number
    var lucky by remember {
        mutableStateOf((1..100).random())
    }

    // State to hold the touch position
    var touchX by remember { mutableStateOf(0f) }
    var touchY by remember { mutableStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                Toast.makeText(context, "螢幕觸控(馬崇恩)", Toast.LENGTH_SHORT).show()
            }
            .pointerInput(Unit) {
                // Detect touch events and get the touch position
                detectTapGestures { offset ->
                    touchX = offset.x
                    touchY = offset.y
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "樂透數字(1-100)為 $lucky"
        )

        Button(
            onClick = { lucky = (1..100).random() } // Generate a new random number
        ) {
            Text("重新產生樂透碼")
        }

        // Display touch coordinates
        Text(
            text = "觸控位置: X = ${"%.2f".format(touchX)}, Y = ${"%.2f".format(touchY)}",
            color = Color.Black
        )

        Text("吳育安共同編輯程式")
    }
}
