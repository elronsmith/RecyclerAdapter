package ru.elron.examplerecycleradapter.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES_ARRAY = arrayOf(
    "EASY",
    "MEDIUM",
    "HARD",
    "NIGHTMARE",
    "ULTRA-NIGHTMARE"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when(position) {
            0 -> EasyFragment()
            1 -> MediumFragment()
            2 -> HardFragment()
            3 -> NightmareFragment()
            else -> UltraNightmareFragment()
        }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES_ARRAY[position]
    }

    override fun getCount(): Int {
        return TAB_TITLES_ARRAY.size
    }
}
