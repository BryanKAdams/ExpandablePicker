package com.bryankeltonadams.expandablepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var textCell: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textCell = findViewById(R.id.recyclerText)
        textCell?.setOnClickListener {
            ExpandablePickerDialogFragment.display(
                generateList(),
                supportFragmentManager,
                object : ExpandablePickerDialogFragment.RecyclerDialogListener {
                    override fun onDismiss(item: PickerData) {
                        textCell?.text = item.getPickerDataText()
                    }
                })
        }
    }

    private fun generateList(): MutableList<TestItem> {

        val main = TestItem(
            "0",
            "null",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Main",
            0
        )
        val stock200 =
            TestItem(
                "20",
                "0",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Stock 200",
                1
            )
        val receiving200 =
            TestItem(
                "21",
                "20",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Receiving",
                2
            )
        val test =
            TestItem(
                "99",
                "21",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "test",
                3
            )
        val mixing =
            TestItem(
                "100",
                "99",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Mixing",
                4
            )
        val bowl =
            TestItem(
                "101",
                "100",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Bowl",
                5
            )
        val mixer =
            TestItem(
                "102",
                "101",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Mixer",
                6
            )
        val garyRiggs =
            TestItem(
                "103",
                "102",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Gary Riggs",
                7
            )
        val pamManfred =
            TestItem(
                "104",
                "103",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Pam Manfred",
                8
            )
        val sandy =
            TestItem(
                "105",
                "1",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "sandy",
                2
            )
        val warehouse2 =
            TestItem(
                "106",
                "0",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Warehouse",
                1
            )
        val row1 =
            TestItem(
                "200",
                "106",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Row #1",
                2
            )
        val row2 =
            TestItem(
                "201",
                "106",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Row #2",
                2
            )
        val shipping =
            TestItem(
                "202",
                "106",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Shipping",
                2
            )


        val stock100 =
            TestItem(
                "1",
                "0",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Stock 100",
                1
            )
        val receiving =
            TestItem(
                "2",
                "1",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Receiving",
                2
            )
        val deepReceiving = TestItem(
            "10",
            "2",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Deep Receiving",
            3
        )
        val deeperReceiving = TestItem(
            "11",
            "10",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Deeper Receiving",
            4
        )
        val evenDeeperReceiving = TestItem(
            "12",
            "11",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Even Deeper Receiving",
            5
        )
        val furtherReceiving = TestItem(
            "13",
            "12",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Further Receiving",
            6
        )
        val fartherReceiving = TestItem(
            "31",
            "13",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Farther Receiving",
            7
        )
        val evenFartherReceiving = TestItem(
            "32",
            "31",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Even Farther Receiving",
            8
        )
        val deepestReceiving = TestItem(
            "33",
            "32",
            R.drawable.ic_boxclosed,
            R.drawable.ic_boxopen,
            "Deepest Receiving",
            9
        )
        val paulsHouse =
            TestItem(
                "3",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Paul's House",
                0
            )
        val bikePartBin =
            TestItem(
                "4",
                "3",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Bike Part Bin",
                1
            )
        val garage =
            TestItem(
                "5", "3", R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen, "Garage", 1
            )
        val petStore =
            TestItem(
                "6",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Pet Store",
                0
            )
        val supplyRoom =
            TestItem(
                "7",
                "6",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Supply Room",
                1
            )
        val storeFront =
            TestItem(
                "8",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxclosed,
                "Store Front",
                0
            )
        val warehouse =
            TestItem(
                "9",
                "null",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxclosed,
                "Warehouse",
                0
            )
        val wtest =
            TestItem(
                "110",
                "9",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "test",
                1
            )
        val reorderpoints =
            TestItem(
                "111",
                "110",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Reorder Points",
                2
            )
        val basement =
            TestItem(
                "112",
                "111",
                R.drawable.ic_boxclosed,
                R.drawable.ic_boxopen,
                "Basement",
                3
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
}