package pl.put.cookbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val frag = supportFragmentManager.findFragmentById(R.id.details_frag) as RecipeDetailsFragment?
        val recipeId = intent.extras?.getLong(EXTRA_RECIPE_ID) ?: 0
        frag?.setRecipe(recipeId)
    }

    companion object {
        const val EXTRA_RECIPE_ID = "recipe_id"
    }
}