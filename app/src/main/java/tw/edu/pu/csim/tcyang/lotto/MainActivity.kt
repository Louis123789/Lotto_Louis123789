package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Play(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var lucky by remember { mutableStateOf((1..100).random()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val x = offset.x.toInt()
                    val y = offset.y.toInt()
                    Toast
                        .makeText(context, "觸控座標: X=$x, Y=$y", Toast.LENGTH_SHORT)
                        .show()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("樂透數字(1-100)為 $lucky")

        Button(onClick = { lucky = (1..100).random() }) {
            Text("重新產生樂透碼")
        }

        Text("吳育安共同編輯程式")
    }
}
