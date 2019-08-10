package id.co.aygo.androidjetpack.view.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.view.base.AygoActivity
import id.co.aygo.androidjetpack.view.base.AygoFragment
import id.co.aygo.androidjetpack.view.base.Constants.ACTION
import id.co.aygo.androidjetpack.view.base.Constants.DATA_KEY1
import id.co.aygo.androidjetpack.view.base.Constants.DATA_KEY2
import id.co.aygo.androidjetpack.view.base.Constants.FRAGMENT_EMPLOYES
import id.co.aygo.androidjetpack.view.base.Constants.TAB_ACCOUNT
import id.co.aygo.androidjetpack.view.base.Constants.TAB_HOME
import id.co.aygo.androidjetpack.view.base.Constants.TAB_SCHEDULE
import id.co.aygo.androidjetpack.view.employes.EmployeFragment
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.addAdditionalTabFragment
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.addInitialTabFragment
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.addShowHideFragment
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.removeFragment
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.showHideTabFragment
import id.co.aygo.androidjetpack.view.home.configTab.StackListManager.updateStackIndex
import id.co.aygo.androidjetpack.view.home.configTab.StackListManager.updateStackToIndexFirst
import id.co.aygo.androidjetpack.view.home.configTab.StackListManager.updateTabStackIndex
import id.co.aygo.androidjetpack.view.home.listener.FragmentInteractionCallback
import id.co.aygo.androidjetpack.view.account.AccountFragment
import id.co.aygo.androidjetpack.view.base.Constants.FRAGMENT_DETAIL_PROFILE
import id.co.aygo.androidjetpack.view.dashboard.DashboardFragment
import id.co.aygo.androidjetpack.view.employes.DetailEmployeFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.LinkedHashMap

class HomeActivity : AygoActivity(), FragmentInteractionCallback {

    lateinit var currentFragment:Fragment
    lateinit var homeFragment:HomeFragment
    lateinit var accountFragment:AccountFragment
    lateinit var scheduleFragment:DashboardFragment

    lateinit var stack:MutableMap<String, Stack<Fragment>>
    var currentTab:String =""
    lateinit var stackList:MutableList<String>
    lateinit var menuStack:MutableList<String>

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedTab(TAB_HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                selectedTab(TAB_SCHEDULE)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                selectedTab(TAB_ACCOUNT)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun OnView(): Int = R.layout.activity_home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        initUI()
    }

    private fun initUI() {

        homeFragment = HomeFragment()
        scheduleFragment = DashboardFragment()
        accountFragment = AccountFragment()

        stack = LinkedHashMap()
        stack[TAB_HOME] = Stack()
        stack[TAB_SCHEDULE] = Stack()
        stack[TAB_ACCOUNT] = Stack()

        menuStack = ArrayList()
        menuStack.add(TAB_HOME)

        stackList = ArrayList()
        stackList.add(TAB_HOME)
        stackList.add(TAB_SCHEDULE)
        stackList.add(TAB_ACCOUNT)

        selectedTab(TAB_HOME)
    }

    private fun selectedTab(tabId: String) {
        if (currentTab != tabId){
            currentTab = tabId
            AygoFragment.currentTabs = currentTab

            if(stack.get(tabId)!!.size==0){
                when(tabId){

                    TAB_HOME ->{
                        addInitialTabFragment(supportFragmentManager, stack, TAB_HOME, homeFragment, R.id.mainFrame, true)
                        resolveStackLists(tabId)
                        assignCurrentFragment(homeFragment)
                    }
                    TAB_SCHEDULE ->{
                        addAdditionalTabFragment(supportFragmentManager, stack, TAB_SCHEDULE, scheduleFragment, currentFragment, R.id.mainFrame, true)
                        resolveStackLists(tabId)
                        assignCurrentFragment(scheduleFragment)
                    }
                    TAB_ACCOUNT ->{
                        addAdditionalTabFragment(supportFragmentManager, stack, TAB_ACCOUNT, accountFragment, currentFragment, R.id.mainFrame, true)
                        resolveStackLists(tabId)
                        assignCurrentFragment(accountFragment)
                    }
                }
            }else{
                showHideTabFragment(supportFragmentManager, stack[tabId]!!.lastElement(), currentFragment)
                resolveStackLists(tabId)
                assignCurrentFragment(stack[tabId]!!.lastElement())
            }
        }
    }

    private fun assignCurrentFragment(fragment: Fragment) {
        currentFragment = fragment
    }

    private fun resolveStackLists(tabId: String) {
        updateStackIndex(stackList, tabId)
        updateTabStackIndex(menuStack, tabId)
    }

    private fun popFragment() {
        /*
         * Select the second last fragment in current tab's stack,
         * which will be shown after the fragment transaction given below
         */
        try{
            val fragment = stack[currentTab]!!.elementAt(stack[currentTab]!!.size - 2)
            /*pop current fragment from stack */
            stack[currentTab]!!.pop()

            removeFragment(supportFragmentManager, fragment, currentFragment)
            assignCurrentFragment(fragment)
        }catch (e: Exception){

        }

    }

    override fun onFragmentInteractionCallback(bundle: Bundle) {
        val action = bundle.getString(ACTION)
        if(action != null){
            when(action){
                TAB_HOME -> selectedTab(TAB_HOME)
                TAB_SCHEDULE -> selectedTab(TAB_SCHEDULE)
                TAB_ACCOUNT -> selectedTab(TAB_ACCOUNT)
                FRAGMENT_EMPLOYES -> showFragment(bundle, EmployeFragment())
                FRAGMENT_DETAIL_PROFILE -> showFragment(bundle, DetailEmployeFragment.initial(bundle))
            }
        }
    }

    override fun onBackFragment() {
        onBackPressed()
    }

    override fun onBackPressed() {
        resolveBackPressed()
    }

    private fun resolveBackPressed() {
        var stackValue = 0
        if (stack[currentTab]!!.size == 1) {
            val value = stack[stackList[1]]
            if (value!!.size > 1) {
                stackValue = value.size
                popAndNavigateToPreviousMenu()
            }
            if (stackValue <= 1) {
                if (menuStack.size > 1) {
                    navigateToPreviousMenu()
                } else {
                    finish()
                }
            }
        } else {
            popFragment()
        }
    }

    private fun popAndNavigateToPreviousMenu() {
        val tempCurrent = stackList[0]
        currentTab = stackList[1]
        AygoFragment.currentTabs = currentTab
        nav_view.selectedItemId = resolveTabPositions(currentTab)

        showHideTabFragment(supportFragmentManager, stack[currentTab]!!.lastElement(), currentFragment)
        assignCurrentFragment(stack[currentTab]!!.lastElement())
        updateStackToIndexFirst(stackList, tempCurrent)
        menuStack.removeAt(0)
    }

    private fun navigateToPreviousMenu() {
        menuStack.removeAt(0)
        currentTab = menuStack[0]
        AygoFragment.currentTabs = currentTab
        nav_view.selectedItemId = resolveTabPositions(currentTab)

        showHideTabFragment(supportFragmentManager, stack[currentTab]!!.lastElement(), currentFragment)
        assignCurrentFragment(stack[currentTab]!!.lastElement())
    }

    private fun resolveTabPositions(currentTab: String): Int {
        var tabIndex = 0
        when (currentTab) {
            TAB_HOME -> tabIndex = R.id.navigation_home
            TAB_SCHEDULE -> tabIndex = R.id.navigation_dashboard
            TAB_ACCOUNT -> tabIndex = R.id.navigation_account
        }
        return tabIndex
    }

    /*Add a fragment to the stack of a particular tab*/
    private fun showFragment(bundle: Bundle, fragmentToAdd: Fragment) {
        val tab = bundle.getString(DATA_KEY1)
        val shouldAdd = bundle.getBoolean(DATA_KEY2)
        addShowHideFragment(supportFragmentManager, stack, tab, fragmentToAdd, currentFragmentFromShownStack(), R.id.mainFrame, shouldAdd)
        assignCurrentFragment(fragmentToAdd)
    }

    fun currentFragmentFromShownStack(): Fragment{
        return stack[currentTab]!!.elementAt(stack[currentTab]!!.size - 1)
    }

}
