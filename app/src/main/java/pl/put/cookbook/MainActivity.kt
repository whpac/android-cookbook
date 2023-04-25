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
import android.widget.FrameLayout


class MainActivity : AppCompatActivity(), RecipeListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = pagerAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
    }

    override fun onItemClicked(id: Long) {
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

    private class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return MainFragment()
                1 -> return FragmentTab1()
            }
            return null
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return App.appResources.getText(R.string.home_tab)
                1 -> return App.appResources.getText(R.string.recipes_tab)
            }
            return null
        }
    }

}