package pl.put.cookbook

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.FrameLayout


class MainActivity : AppCompatActivity(), RecipeListFragment.Listener, FragmentTab1.ItemListenerActivity, FragmentTab2.ItemListenerActivity {

    lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        pager = findViewById(R.id.pager)
        pager.adapter = pagerAdapter
        pager.currentItem = savedInstanceState?.getInt(STATE_TAB_ID) ?: 0
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
    }

    override fun onItemClicked(id: Long) {
        Log.i("RECIPES", id.toString())
        val detailsFragment = findViewById<FrameLayout>(R.id.recipe_details)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(STATE_TAB_ID, pager.currentItem)
    }

    private class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return MainFragment()
                1 -> return FragmentTab1()
                2 -> return FragmentTab2()
            }
            return null
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return App.appResources.getText(R.string.home_tab)
                1 -> return App.appResources.getText(R.string.main_courses_tab)
                2 -> return App.appResources.getText(R.string.desserts_tab)
            }
            return null
        }
    }

    companion object {
        private const val STATE_TAB_ID = "tab_id"
    }

}