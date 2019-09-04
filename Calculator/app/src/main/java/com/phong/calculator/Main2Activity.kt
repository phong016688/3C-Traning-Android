package com.phong.calculator

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.collections.ArrayList

class Main2Activity : FragmentActivity(){
    val PUT_LIST_A2 = "putstringa2"
    var items: ArrayList<Fragment> = ArrayList()
    var list_text: ArrayList<String>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        list_text = laydata()
        items.add(Fragment1.newInstance(list_text))

        ViewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                return items.get(p0)
            }

            override fun getCount(): Int {
                return items.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return "Pager" + position
            }

        }
        ViewPager.setCurrentItem(0)

       setActionBar(toolBar1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_clear){
            list_text = ArrayList()
            (items[0] as Fragment1).deletelist()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun laydata(): ArrayList<String>? {
        var set: Set<String>? = null
        var arr: ArrayList<String> = ArrayList()
        try {
            set = getSharedPreferences(PREF1, Context.MODE_PRIVATE).getStringSet(KEY_STRING_PREF, null)
            Log.d("@@@@@@", "lay thanh cong")
        } catch (e: Exception) {
            Log.e("@@@@@@", e.toString())
        }
            arr = ArrayList(set)
        return arr
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArrayList(PUT_LIST_A2, list_text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        list_text = savedInstanceState!!.getStringArrayList(PUT_LIST_A2)
    }
}
