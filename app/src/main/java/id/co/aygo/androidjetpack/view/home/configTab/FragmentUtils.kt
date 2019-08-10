
package id.co.aygo.androidjetpack.view.home.configTab
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.view.base.Constants.ACTION
import id.co.aygo.androidjetpack.view.base.Constants.DATA_KEY1
import id.co.aygo.androidjetpack.view.base.Constants.DATA_KEY2
import id.co.aygo.androidjetpack.view.home.listener.FragmentInteractionCallback
import java.util.Stack

object FragmentUtils {

    /*
     * Add the initial fragment, in most cases the first tab in BottomNavigationView
     */
    fun addInitialTabFragment(fragmentManager: FragmentManager,
                              stacks: Map<String, Stack<Fragment>>,
                              tag: String,
                              fragment: Fragment,
                              layoutId: Int,
                              shouldAddToStack: Boolean) {
        if (shouldAddToStack) stacks[tag]!!.push(fragment)
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left,
                        R.anim.slide_out_to_right
                )
                .add(layoutId, fragment)
                .commit()
    }

    /*
     * Add additional tab in BottomNavigationView on click, apart from the initial one and for the first time
     */
    fun addAdditionalTabFragment(fragmentManager: FragmentManager,
                                 stacks: Map<String, Stack<Fragment>>,
                                 tag: String,
                                 show: Fragment,
                                 hide: Fragment,
                                 layoutId: Int,
                                 shouldAddToStack: Boolean) {
        if (shouldAddToStack) stacks[tag]!!.push(show)
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left,
                        R.anim.slide_out_to_right
                )
                .add(layoutId, show)
                .show(show)
                .hide(hide)
                .commit()
    }

    /*
     * Hide previous and show current tab fragment if it has already been added
     * In most cases, when tab is clicked again, not for the first time
     */
    fun showHideTabFragment(fragmentManager: FragmentManager,
                            show: Fragment,
                            hide: Fragment) {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left,
                        R.anim.slide_out_to_right
                )
                .hide(hide)
                .show(show)
                .commit()
    }

    /*
     * Add fragment in the particular tab stack and show it, while hiding the one that was before
     */
    fun addShowHideFragment(fragmentManager: FragmentManager,
                            stacks: Map<String, Stack<Fragment>>,
                            tag: String,
                            show: Fragment,
                            hide: Fragment,
                            layoutId: Int,
                            shouldAddToStack: Boolean) {
        if (shouldAddToStack) stacks[tag]!!.push(show)
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left,
                        R.anim.slide_out_to_right
                )
                .add(layoutId, show)
                .show(show)
                .hide(hide)
                .commit()
    }

    fun removeFragment(fragmentManager: FragmentManager, show: Fragment, remove: Fragment) {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left,
                        R.anim.slide_out_to_right
                )
                .remove(remove)
                .show(show)
                .commit()
    }

    /*
     * Send action from fragment to activity
     */
    fun sendActionToActivity(bundle: Bundle, action: String, tab: String, shouldAdd: Boolean, fragmentInteractionCallback: FragmentInteractionCallback) {
        bundle.putString(ACTION, action)
        bundle.putString(DATA_KEY1, tab)
        bundle.putBoolean(DATA_KEY2, shouldAdd)
        fragmentInteractionCallback.onFragmentInteractionCallback(bundle)
    }
}
