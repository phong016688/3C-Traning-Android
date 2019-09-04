package com.phong.calculator

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
interface Deletelist{
    fun deletelist(list: ArrayList<String>?)
}
class Fragment1 : Fragment(), PopupMenu.OnMenuItemClickListener {
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private var list_text: ArrayList<String>? = ArrayList<String>()
    private var i : Int = 0
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.menu_delete -> {
                    list_text?.removeAt(i)
                    arrayAdapter.notifyDataSetChanged()
                    var set: Set<String>?
                    set = list_text?.toSet()
                    activity?.getSharedPreferences(PREF1, Context.MODE_PRIVATE)?.edit()?.putStringSet(KEY_STRING_PREF, set)?.apply()
                }
            }
        }
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list_text = it.getStringArrayList(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_fragment1, container, false)
        var listview: ListView = v.findViewById<ListView>(R.id.listview)
        arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_text ?: emptyList())
        listview.adapter = arrayAdapter
        listview.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showfilterpopup(view)
                i = position
            }

        }
        return v
    }


    private fun showfilterpopup(view: View?) {
        var popupmenu: PopupMenu = PopupMenu(context, view)
        popupmenu.menuInflater.inflate(R.menu.popup, popupmenu.menu)
        popupmenu.setOnMenuItemClickListener(this)
        popupmenu.show()

    }

    fun deletelist(){
        list_text?.removeAll(list_text!!)
        arrayAdapter.notifyDataSetChanged()
        activity?.getSharedPreferences(PREF1, Context.MODE_PRIVATE)?.edit()?.putStringSet(KEY_STRING_PREF, emptySet())?.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance(list: ArrayList<String>?) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, list)
                }
            }
    }
}
