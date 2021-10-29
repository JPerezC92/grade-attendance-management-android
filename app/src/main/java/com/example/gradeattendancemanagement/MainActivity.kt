package com.example.gradeattendancemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gradeattendancemanagement.miscellaneous.components.TableScreen
import com.example.gradeattendancemanagement.ui.theme.GradeAttendanceManagementTheme


import androidx.appcompat.app.AppCompatActivity

/*
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Card
import androidx.ui.material.themeTextStyle
import androidx.ui.res.imageResource
*/


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeAttendanceManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Store(context = this, content = { RouterApp() })
                    //TableScreen()
                    //MyApp()
                    //MyBody()


                }
            }
        }
    }
}


/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GradeAttendanceManagementTheme {
        Greeting("Android")
    }
}

 */
/*
@Preview
@Composable
fun MyApp() {
    MaterialTheme {


            MyBody()


        }
    }

*/
/*
@Composable
fun MyAppBar(appBarTitle: String){
    TopAppBar(title = {
        Text(text = appBarTitle)
    })
}
*/
/*
@Composable
fun MyBody(){
    Padding(padding = 16.dp) {

        Card(shape = RoundedCornerShape(size = 10.dp)) {
            val image = +imageResource(R.drawable.aula)
            Column(modifier = Spacing(16.dp)) {
                Container(expanded = true,height = 200.dp) {
                    Clip(shape = RoundedCornerShape(size = 10.dp)) {
                        DrawImage(image = image)
                    }
                }
                HeightSpacer(height = 10.dp)

                Text(text = "Welcome to Jetpack compose",style = +themeTextStyle { h6 })
                Text(text = "Android Studio",style = +themeTextStyle { body2 })
                Text(text = "Canary",style = +themeTextStyle { body2 })
            }
        }

    }



}
*/
