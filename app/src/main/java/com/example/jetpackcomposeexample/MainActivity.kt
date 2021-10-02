package com.example.jetpackcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
                    RecipeColumnList(recipeList)
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
    Recipe(R.drawable.ic_launcher_background, "Calabaza1", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza2", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza3", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza4", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza5", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza6", listOf("Queso", "Azucar", "Agua")),
    Recipe(R.drawable.ic_launcher_background, "Calabaza7", listOf("Queso", "Azucar", "Agua"))
)

@Composable
private fun RecipeCard(recipe: Recipe, onRecipeClick: (Recipe) -> Unit) {
    val image = painterResource(R.drawable.ic_launcher_background)
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Modifier
        .padding(8.dp)
        .clickable {
            onRecipeClick(recipe)
        }) {
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
fun RecipeColumnList(recipeList: List<Recipe>) {
    LazyRow {
        items(recipeList) { recipe ->
            RecipeCard(recipe = recipe, onRecipeClick = {
                Log.d("Recipe", "${it.title}")
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipePreview() {
    RecipeCard(recipe = recipeList[0], onRecipeClick = {

    })
}