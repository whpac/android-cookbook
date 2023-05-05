package pl.put.cookbook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.put.cookbook.recipes.Recipe


class FragmentTab2 : Fragment() {

    interface ItemListenerActivity {
        fun onItemClicked(id: Long, tab: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val recipeRecycler = inflater.inflate(R.layout.fragment_tab1, container, false) as RecyclerView
        val recipeNames = arrayOfNulls<String>(Recipe.recipesDesserts.size)
        for (i in recipeNames.indices) {
            recipeNames[i] = Recipe.recipesDesserts[i].getName()
        }

        val recipeImages = IntArray(Recipe.recipesDesserts.size)
        for (i in recipeImages.indices) {
            recipeImages[i] = Recipe.recipesDesserts[i].getImageResourceId()
        }
        val adapter = CaptionedImagesAdapter(recipeNames, recipeImages)
        recipeRecycler.adapter = adapter
        val layoutManager = GridLayoutManager(activity, 2)
        recipeRecycler.layoutManager = layoutManager

        adapter.onClickListener = object : CaptionedImagesAdapter.OnClickListener {
            override fun onClick(position: Int) {
                try {
                    val a = this@FragmentTab2.activity as ItemListenerActivity
                    a.onItemClicked(position.toLong(), 1)
                } catch (_: Exception){
                    Log.i("RECIPES", "Activity doesn't implement interface")
                }
            }
        }
        return recipeRecycler
    }
}