package pl.put.cookbook

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import pl.put.cookbook.recipes.Recipe

class RecipeListFragment : ListFragment() {

    interface Listener {
        fun onItemClicked(id: Long)
    }

    private var listener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val names = arrayOfNulls<String>(Recipe.recipes.size)
        for (i in names.indices) {
            names[i] = Recipe.recipes[i].getName()
        }
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            inflater.context, android.R.layout.simple_list_item_1, names
        )
        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as Listener?
    }

    override fun onListItemClick(listView: ListView, itemView: View, position: Int, id: Long) {
        listener?.onItemClicked(id)
    }
}