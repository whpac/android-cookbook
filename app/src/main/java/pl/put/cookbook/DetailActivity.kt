package pl.put.cookbook

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import pl.put.cookbook.recipes.Recipe


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        val frag = supportFragmentManager.findFragmentById(R.id.details_frag) as RecipeDetailsFragment?
        val recipeId = intent.extras?.getLong(EXTRA_RECIPE_ID) ?: 0
        val tabId = intent.extras?.getInt(EXTRA_TAB_ID) ?: 0
        frag?.setRecipe(recipeId, tabId)

        val recipe = Recipe.recipes[tabId][recipeId.toInt()]
        val recipeImage = findViewById<ImageView>(R.id.recipe_image)
        recipeImage.setImageResource(recipe.getImageResourceId())
        this.title = recipe.getName()
    }

    fun onClickDone(view: View?) {
        val text: CharSequence = "Przesyłanie danych..."
        val duration = Snackbar.LENGTH_SHORT
        val snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration)
        snackbar.show()
    }


    companion object {
        const val EXTRA_RECIPE_ID = "recipe_id"
        const val EXTRA_TAB_ID = "tab_id"
    }
}