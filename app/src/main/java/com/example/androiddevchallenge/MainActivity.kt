/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val puppyList = MockDataGenerator().generatePuppyList()

    Surface(color = MaterialTheme.colors.background) {
        PuppyList(puppies = puppyList, modifier = Modifier.fillMaxWidth(), onClick = { puppy -> onPuppyClicked(puppy = puppy) })
    }
}

fun onPuppyClicked(puppy: Puppy) {
    Log.d("MainActivity", "puppy clicked: ${puppy.name}")
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@Composable
fun PuppyList(puppies: List<Puppy>, modifier: Modifier, onClick: (puppy: Puppy) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxWidth()) {
        items(puppies) { puppy ->
            PuppyListItem(puppy = puppy, modifier = modifier, onClick = onClick)
        }
    }
}

@Composable
fun PuppyListItem(puppy: Puppy, modifier: Modifier = Modifier, onClick: (puppy: Puppy) -> Unit) {
    Card(elevation = 8.dp, modifier = modifier.clickable { onClick(puppy) }) {
        Column {
            Image(
                painter = painterResource(id = puppy.imageResourceId),
                contentDescription = "Puppy Image",
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = when (puppy.type) {
                        PuppyType.CAT -> "🐱"
                        PuppyType.DOG -> "\uD83D\uDC15"
                        else -> "\uD83D\uDC7D"
                    },
                    style = MaterialTheme.typography.h5
                )
                Text(text = puppy.name, style = MaterialTheme.typography.h5, modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}
