package com.bryankeltonadams.expandablepicker

import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class ExpandablePickerDialogFragment : DialogFragment() {

    lateinit var adapter: ExpandablePickerAdapter
    lateinit var listener: RecyclerDialogListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    companion object {

        private val TAG_RECYCLER_DIALOG: String? = "Recycler Dialog"

        fun display(
            displayList: MutableList<out PickerData>,
            fragmentManager: FragmentManager,
            listener: RecyclerDialogListener
        ): DialogFragment {
            val dialog = ExpandablePickerDialogFragment()
            val b = Bundle()
            b.putParcelableArrayList("list", displayList as ArrayList<out Parcelable>)
            dialog.arguments = b
            dialog.listener = listener
            dialog.show(fragmentManager, TAG_RECYCLER_DIALOG)
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list = arguments?.getParcelableArrayList<Parcelable>("list") as MutableList<PickerData>
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.layout_full_screen_dialog, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = activity?.title
        toolbar.inflateMenu(R.menu.expandable_picker_menu)
        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                if (item?.itemId == R.id.action_search) {
                    val searchView = item.actionView as SearchView

                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            adapter.filter.filter(query)
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            adapter.filter.filter(newText)
                            return false
                        }
                    })

                }
                return true
            }

        })


        adapter = ExpandablePickerAdapter(
            list,
            object : ExpandablePickerAdapter.RecyclerAdapterListener {
                override fun onClick(item: PickerData) {
                    listener.onDismiss(item)
                    dismiss()
                }

            })
        val recycler = view.findViewById(R.id.recyclerView) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity!!)
        recycler.adapter = adapter
        return view
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    interface RecyclerDialogListener {
        fun onDismiss(item: PickerData)
    }
}
