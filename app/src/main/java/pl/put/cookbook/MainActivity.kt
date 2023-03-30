package pl.put.cookbook

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout


class MainActivity : AppCompatActivity(), RecipeListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onItemClicked(id: Long) {
        val detailsFragment = findViewById<FrameLayout>(R.id.recipe_details);
        if(detailsFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            val recipeDetailsFragment = RecipeDetailsFragment()
            recipeDetailsFragment.setRecipe(id)
            transaction.replace(R.id.recipe_details, recipeDetailsFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, id)
            startActivity(intent)
        }
    }
}