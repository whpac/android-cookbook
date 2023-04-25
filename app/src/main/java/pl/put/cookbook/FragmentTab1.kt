package pl.put.cookbook

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.put.cookbook.recipes.Recipe


class FragmentTab1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val recipeRecycler = inflater.inflate(R.layout.fragment_tab1, container, false) as RecyclerView
        val recipeNames = arrayOfNulls<String>(Recipe.recipes.size)
        for (i in recipeNames.indices) {
            recipeNames[i] = Recipe.recipes[i].getName()
        }

        val cocktailImages = IntArray(Recipe.recipes.size)
        for (i in cocktailImages.indices) {
            cocktailImages[i] = Recipe.recipes[i].getImageResourceId()
        }
        val adapter = CaptionedImagesAdapter(recipeNames, cocktailImages)
        recipeRecycler.adapter = adapter
        val layoutManager = GridLayoutManager(activity, 2)
        recipeRecycler.setLayoutManager(layoutManager)

        adapter.onClickListener = object : CaptionedImagesAdapter.OnClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, position.toLong())
                activity!!.startActivity(intent)
            }
        }
        return recipeRecycler
    }
}