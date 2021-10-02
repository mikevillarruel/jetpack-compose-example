package com.example.jetpackcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RecipeColumnListDemo(recipeList)
                }
            }
        }
    }
}

data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)

val recipeList = listOf(
    Recipe(R.drawable.ic_launcher_background, "Arrozmate", listOf("Arroz", "Tomate", "Crema")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Torta", listOf("Merengue", "Chocolate", "Vainilla")),
    Recipe(R.drawable.ic_launcher_background, "Torta2", listOf("Merengue2", "Chocolate2", "Vainilla2")),
    Recipe(R.drawable.ic_launcher_background, "TestText", listOf("Merengue2", "Chocolate2", "Vainilla2")),
    Recipe(R.drawable.ic_launcher_background, "Android", listOf("Merengue2", "Chocolate2", "Vainilla2"))
)

@Composable
private fun RecipeCard(recipe: Recipe) {
    val image = painterResource(R.drawable.ic_launcher_background)
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            val imageModifier = Modifier
                .requiredHeight(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
            Image(
                painter = image,
                modifier = imageModifier,
                contentScale = ContentScale.Crop,
                contentDescription = "header"
            )
            Spacer(modifier = Modifier.requiredHeight(16.dp))
            Text(text = recipe.title, style = typography.h6)
            for (ingredient in recipe.ingredients) {
                Text(text = "* $ingredient", style = typography.body2)
            }
        }
    }
}

@Composable
fun RecipeColumnListDemo(recipeList: List<Recipe>) {
    LazyColumn {
        items(recipeList) { recipe ->
            RecipeCard(recipe)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipePreview() {
    RecipeCard(recipeList[0])
}