package com.app.jcomposematerial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {
    private val userList = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AddMoreItems()
            }
        }
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



}