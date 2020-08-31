package com.bryankeltonadams.expandablepicker

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    private val list = generateList()
    private val displayList = mutableListOf<Item>()
    lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        displayList.addAll(list)
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun generateList(): MutableList<Item> {

        val main = Item(
            "0",
            "null",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Main",
            0,
            0,
            true
        )
        val stock200 =
            Item(
                "20",
                "0",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Stock 200",
                0,
                1,
                false
            )
        val receiving200 =
            Item(
                "21",
                "20",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Receiving",
                0,
                2,
                false
            )
        val test =
            Item(
                "99",
                "21",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "test",
                0,
                3,
                false
            )
        val mixing =
            Item(
                "100",
                "99",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Mixing",
                0,
                4,
                false
            )
        val bowl =
            Item(
                "101",
                "100",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Bowl",
                0,
                5,
                false
            )
        val mixer =
            Item(
                "102",
                "101",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Mixer",
                0,
                6,
                false
            )
        val garyRiggs =
            Item(
                "103",
                "102",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Gary Riggs",
                0,
                7,
                false
            )
        val pamManfred =
            Item(
                "104",
                "103",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Pam Manfred",
                0,
                8,
                false
            )
        val sandy =
            Item(
                "105",
                "1",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "sandy",
                0,
                2,
                false
            )
        val warehouse2 =
            Item(
                "106",
                "0",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Warehouse",
                0,
                1,
                false
            )
        val row1 =
            Item(
                "200",
                "106",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Row #1",
                0,
                2,
                false
            )
        val row2 =
            Item(
                "201",
                "106",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Row #2",
                0,
                2,
                false
            )
        val shipping =
            Item(
                "202",
                "106",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Shipping",
                0,
                2,
                false
            )


        val stock100 =
            Item("1", "0", R.drawable.ic_boxclosed, R.drawable.ic_boxopen, "Stock 100", 0, 1, false)
        val receiving =
            Item("2", "1", R.drawable.ic_boxclosed, R.drawable.ic_boxopen, "Receiving", 0, 2, false)
        val deepReceiving = Item(
            "10",
            "2",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Deep Receiving",
            0,
            3,
            false
        )
        val deeperReceiving = Item(
            "11",
            "10",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxclosed,
            "Deeper Receiving",
            0,
            4,
            false
        )
        val evenDeeperReceiving = Item(
            "12",
            "11",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Even Deeper Receiving",
            0,
            5,
            false
        )
        val furtherReceiving = Item(
            "13",
            "12",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Further Receiving",
            0,
            6,
            false
        )
        val fartherReceiving = Item(
            "31",
            "13",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Farther Receiving",
            0,
            7,
            false
        )
        val evenFartherReceiving = Item(
            "32",
            "31",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Even Farther Receiving",
            0,
            8,
            false
        )
        val deepestReceiving = Item(
            "33",
            "32",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Deepest Receiving",
            0,
            9,
            false
        )
        val paulsHouse =
            Item(
                "3",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Paul's House",
                0,
                0,
                true
            )
        val bikePartBin =
            Item(
                "4",
                "3",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Bike Part Bin",
                0,
                1,
                false
            )
        val garage =
            Item(
                "5", "3", R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen, "Garage", 0, 1, false
            )
        val petStore =
            Item(
                "6",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Pet Store",
                0,
                0,
                true
            )
        val supplyRoom =
            Item(
                "7",
                "6",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Supply Room",
                0,
                1,
                false
            )
        val storeFront =
            Item(
                "8",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxclosed,
                "Store Front",
                0,
                0,
                true
            )
        val warehouse =
            Item(
                "9",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxclosed,
                "Warehouse",
                0,
                0,
                true
            )
        val wtest =
            Item(
                "110",
                "9",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "test",
                0,
                1,
                false
            )
        val reorderpoints =
            Item(
                "111",
                "110",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Reorder Points",
                0,
                2,
                false
            )
        val basement =
            Item(
                "112",
                "111",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Basement",
                0,
                3,
                false
            )

        return arrayListOf(
            basement,
            warehouse2,
            reorderpoints,
            test,
            wtest,
            sandy,
            garyRiggs,
            mixer,
            mixing,
            pamManfred,
            bowl,
            main,
            garage,
            fartherReceiving,
            evenFartherReceiving,
            stock200,
            supplyRoom,
            warehouse,
            row1,
            row2,
            shipping,
            storeFront,
            receiving200,
            deepReceiving,
            deeperReceiving,
            petStore,
            paulsHouse,
            receiving,
            bikePartBin,
            deepestReceiving,
            stock100,
            evenDeeperReceiving,
            evenFartherReceiving,
            furtherReceiving
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu!!.findItem(R.id.action_search)
        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView

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
        return super.onCreateOptionsMenu(menu)
    }


}