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
    val context = LocalContext.current

    var lucky by remember {
        mutableStateOf((1..100).random())
    }

    Column (modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                val x = offset.x.toInt()
                val y = offset.y.toInt()
                Toast.makeText(context, "螢幕觸控(馬崇恩) - X: $x, Y: $y", Toast.LENGTH_SHORT).show()
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "樂透數字(1-100)為 $lucky",
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // 短按：數字減1，但不能小於1
                        if (lucky > 1) {
                            lucky -= 1
                        }
                        Toast.makeText(context, "短按：數字減1", Toast.LENGTH_SHORT).show()
                    },
                    onLongPress = {
                        // 長按：數字加1，但不能大於100
                        if (lucky < 100) {
                            lucky += 1
                        }
                        Toast.makeText(context, "長按：數字加1", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        )

        Button(
            onClick = { lucky = (1..100).random() }
        ) {
            Text("重新產生樂透碼")
        }

        Text("吳育安共同編輯程式")
    }
}