package pl.put.cookbook

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity(), RecipeListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onItemClicked(id: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, id)
        startActivity(intent)
    }
}