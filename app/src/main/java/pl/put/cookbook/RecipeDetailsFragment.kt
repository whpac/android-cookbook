package pl.put.cookbook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import pl.put.cookbook.recipes.Recipe


class RecipeDetailsFragment : Fragment() {
    private var recipeId: Long = 0
    private lateinit var timerFragment: TimerFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            recipeId = savedInstanceState.getLong("recipeId")
        }

        timerFragment = TimerFragment()
        val ft = childFragmentManager.beginTransaction()
        ft.add(R.id.timersFragment, timerFragment)
        ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }


    override fun onStart() {
        super.onStart()
        val view = view ?: return

        val recipe = Recipe.recipes[recipeId.toInt()]

        val title = view.findViewById<TextView>(R.id.textTitle)
        title.text = recipe.getName()

        val description = view.findViewById<TextView>(R.id.textDescription)
        description.text = recipe.getRecipe()

        val timeList = ArrayList<Long>()
        for(action in recipe.getActions()){
            val time = action.getTimeInSeconds() ?: continue
            timeList.add(time.toLong())
        }
        timerFragment.setSuggestedTimes(timeList.toArray(arrayOf()))
    }

    fun setRecipe(recipeId: Long){
        this.recipeId = recipeId
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("recipeId", recipeId)
    }

}