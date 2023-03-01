package com.app.jcomposematerial

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.jcomposematerial.bottomtabs.AppBottomNavigation
import com.app.jcomposematerial.bottomtabs.Routes
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SetBottomNav() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {

        }

        NavHost(navController = navController, startDestination = Routes.NAV_HOME) {
            composable(Routes.NAV_HOME) { UserDetails(User("Home")) }
            composable(Routes.NAV_FEED) { UserDetails(User("FEED")) }
            composable(Routes.NAV_FAV) { UserDetails(User("FAVOURITE")) }
            composable(Routes.NAV_PROFILE) { UserDetails(User("PROFILE")) }
        }
    }

}

private lateinit var fontFamily: FontFamily


@Composable
private fun NavigateWithScreens() {
    val navCon = rememberNavController()
    NavHost(navController = navCon, startDestination = "home") {
        composable("home") {
            Box(modifier = Modifier.fillMaxSize()) {
                SetImageCard(navCon)
            }
        }
        composable("list?item={item}", arguments = listOf(
            navArgument("item") {
                defaultValue = "Number not available"
                type = NavType.StringType
            }
        )) {
            val item = it.arguments?.getString("item")
            SetBorder(item)
        }

    }
}

@Composable
private fun ComposeWithXml() {
    AndroidView(factory = {
        View.inflate(it, R.layout.layout_view, null)
    }, modifier = Modifier.wrapContentHeight(), update = {
        val textView = it.findViewById<TextView>(R.id.text_view)
        textView.setOnClickListener {
            textView.text = "Updated Text"
        }
    })
}

@Composable
private fun AddMoreItems() {
    val nameList = User("Shivam")

    val list = remember {
        mutableStateListOf(nameList)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        UserListCard(nameList = list)
        Text(
            text = "Add Item",
            color = Color.Yellow,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .background(Color.Blue)
                .padding(10.dp)
                .align(Alignment.BottomCenter)
                .clickable {
                    list.add(User("Akhand"))
                }
        )
    }
}

data class User(val name: String?)

private val userList = mutableListOf<User>()

@Composable
private fun LazyColumnUserList() {
    val item = remember {
        mutableStateListOf(userList)
    }
    Box() {
        LazyColumn {
            items(item) { user ->
                UserCard()
            }
        }
        Text(
            text = "Add Item",
            color = Color.Yellow,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .background(Color.Blue)
                .padding(10.dp)
                .align(Alignment.BottomCenter)
                .clickable {
                    userList.add(User("Akhand"))
                }
        )
    }
}

@Composable
private fun UserList() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        for (i in 1..50) {
            UserCard()
        }
    }
}

@Composable
private fun UserListCard(nameList: List<User>) {
    LazyColumn {
        items(nameList) {
            UserDetails(it)
        }
    }
}

@Composable
private fun UserDetails(user: User) {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .border(1.dp, color = Color.Red)
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img11), contentDescription = null,
            modifier = Modifier.clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = "Hello Guys, I am Shivam Kamboj. I work as an Android Developer." +
                        " I am willing to learn Jetpack Compose",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            val item = user.name ?: "View Profile"
            Text(
                textAlign = TextAlign.Center,
                color = Color.White,
                text = item,
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable {
                        userList.add(User("Amit"))
                    }
            )
        }
    }
}


@Composable
private fun UserCard(user: User? = User("")) {
    val userItem = remember {
        mutableStateOf(user)
    }
    Row(
        modifier = Modifier
            .padding(20.dp)
            .border(1.dp, color = Color.Red)
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img11), contentDescription = null,
            modifier = Modifier.clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = "Hello Guys, I am Shivam Kamboj. I work as an Android Developer." +
                        " I am willing to learn Jetpack Compose",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            val item = userItem.value?.name ?: "View Profile"
            Text(
                textAlign = TextAlign.Center,
                color = Color.White,
                text = item,
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable {
                        userList.add(User("Amit"))
                    }
            )
        }
    }
}

@Composable
private fun SetTextProperty() {
    val context = LocalContext.current
    Text(
        text = "Hello Android",
        fontSize = 32.sp,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.clickable {
            Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
        }
    )
}

@Composable
private fun SurfacePreview() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column() {
            Text("Hello", modifier = Modifier.padding(20.dp))
            Text("Hello Hello", modifier = Modifier.padding(20.dp))
        }
    }
}

@Composable
private fun LazyItems() {
    Column() {
        LazyColumn {
            itemsIndexed(listOf("Hi", "Shivam", "Kamboj")) { index, item ->
                Text(item, modifier = Modifier.padding(20.dp), fontSize = 24.sp)
            }
        }

        LazyColumn {
            items(5000) {
                Text("Hello $it", modifier = Modifier.padding(20.dp))
            }
        }
    }
}

@Composable
private fun ScrollAbelItems() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        for (i in 1..50) {
            Text("Hello $i", modifier = Modifier.padding(20.dp))
        }
    }
}

@Composable
private fun SetSnackBar() {
    val scaffoldState = rememberScaffoldState()
    var textState by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        it.calculateBottomPadding()
        Column {
            TextField(
                value = textState,
                onValueChange = { it1 ->
                    textState = it1
                },
                modifier = Modifier.border(5.dp, Color.Blue)
            )
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Hello $textState",
                        duration = SnackbarDuration.Short,
                        actionLabel = null
                    )
                }

            }) {
                Text(text = "Press Me!!!")
            }
        }
    }
}

@Composable
private fun SetClickDummy() {
    val color = remember {
        mutableStateOf(
            Color.Blue
        )
    }
    Column {
        SetUpdateClickView(
            Modifier
                .weight(1f)
                .background(color = color.value)
                .fillMaxSize()
        )
        SetClickableView(
            Modifier
                .weight(1f)
                .background(Color.Blue)
                .fillMaxSize()
        ) {
            color.value = it
        }
    }
}

@Composable
private fun SetUpdateClickView(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

    }
}

@Composable
private fun SetClickableView(modifier: Modifier = Modifier, update: (Color) -> Unit) {
    Box(modifier = modifier
        .clickable {
            update(
                Color(Random.nextFloat(), Random.nextFloat(), 1f)
            )
        })
}

@Composable
private fun SetStyleWithText() {
    fontFamily = FontFamily(
        Font(R.font.i_bold, FontWeight.Bold),
        Font(R.font.i_semibold, FontWeight.SemiBold),
        Font(R.font.i_regular, FontWeight.Normal),
        Font(R.font.i_thin, FontWeight.Thin),
        Font(R.font.i_extra_light, FontWeight.ExtraLight)
    )
    Box() {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ) {
                    append("S")
                }
                append("HIVAM ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ) {
                    append("K")
                }
                append("AMBOJ ")
            },
            style = TextStyle(color = Color(0xFF000000)),
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun SetImageCard(navCon: NavHostController) {
    val item = "Random Number is ${Random.nextInt()}"
    Card(
        backgroundColor = Color.Black,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(20.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img11),
                contentDescription = "This is image", modifier = Modifier.clickable {
                    navCon.navigate("list") {

                    }
                }
            )
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Black
                            ),
                            startY = 550f
                        )
                    )
            )
            Text(
                text = "Hello",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp),
                color = Color.White
            )
        }
    }
}

@Composable
private fun SetBorder(item: String? = null) {
    Column(
        modifier = Modifier
            .background(color = Color.Yellow)
            .border(12.dp, color = Color.Red)
            .padding(12.dp)
            .border(12.dp, color = Color.Black)
            .padding(12.dp)
            .border(12.dp, color = Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$item")
        Text(text = "We are glad you arrived.")
    }
}

@Composable
private fun SetColumn() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color.Yellow)
            .height(200.dp)
    ) {
        Text(text = "Hello Shivam", color = Color.Black)
        Text(text = "How you doing man?", color = Color.Black)
        Text(text = "I am good, you say?", color = Color.Black)
    }
}

@Composable
private fun SetRow() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Hello Shivam", color = Color.Red)
        Text(
            text = "How you doing man?",
            color = Color.Red,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "I am good, you say?",
            color = Color.Red,
        )
    }
}