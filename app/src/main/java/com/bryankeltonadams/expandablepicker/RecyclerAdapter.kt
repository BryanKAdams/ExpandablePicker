package com.bryankeltonadams.expandablepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*
import java.util.*

class RecyclerAdapter(
    private var list: MutableList<Item>
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), Filterable {
    private var filterList = mutableListOf<Item>()
    private var originalFilterList = mutableListOf<Item>()
    private var isFiltering = false
    private var searchQuery = ""

    init {
        list = recursiveSortList(null, list, mutableListOf())
        filterList = list.filter { it.visibility }.toMutableList()
        originalFilterList = filterList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_item,
            parent, false
        )
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = filterList[position]
        val params = holder.imageView.layoutParams as LinearLayout.LayoutParams
        val params2 = holder.textView.layoutParams as LinearLayout.LayoutParams


        // Images are optional, so if the item has an image set it otherwise set the resource to be transparent.
        if (currentItem.image != null) {
            holder.imageView.setImageResource(currentItem.image!!)
            holder.imageView.visibility = View.VISIBLE
            params2.leftMargin = 0
        } else {
            holder.imageView.setImageResource(android.R.color.transparent)
            holder.imageView.visibility = View.GONE
            params2.leftMargin = 80 * (currentItem.indentLevel + 1) - 20
        }
        // Simple Boolean check where you pass in the Current Item and it will tell you if there are any Items that have the Current Item as its parentID
        fun hasChildren(item: Item): Boolean {
            val childList = list.filter { it.parentId == item.id }
            return childList.isNotEmpty()
        }

        // Boolean check where you pass in the current item and it will tell you if there are any Items in the Visible/Filter list that have the current item as its parentID
        fun hasVisibleChildren(item: Item): Boolean {
            val childList = filterList.filter { it.parentId == item.id }
            return childList.isNotEmpty()
        }
        // Sets the Visibility of the DropDown Chevron based on whether the Current Item has children or not
        if (hasChildren(currentItem)) {
            holder.dropDownButton.visibility = View.VISIBLE
            if (hasVisibleChildren(currentItem)) {
                holder.dropDownButton.rotation = 0f
                if (currentItem.expandImage != null) {
                    currentItem.expandImage?.let { holder.imageView.setImageResource(it) }
                }
            } else {
                holder.dropDownButton.rotation = -90f
                currentItem.image?.let { holder.imageView.setImageResource(it) }

            }
        } else {
            holder.dropDownButton.visibility = View.INVISIBLE

        }
        holder.textView.text = currentItem.text
        params.leftMargin = (currentItem.indentLevel * (params.width / 1.7)).toInt()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textView: TextView = itemView.text_view
        val dropDownButton: ImageView = itemView.dropDownButton
        val params2 = itemView.text_view.layoutParams as LinearLayout.LayoutParams

        init {
            // the code that changes the expand image and the chevron rotation
            dropDownButton.setOnClickListener {
                val position = adapterPosition
                val visibleChildren =
                    filterList.filter { it.parentId == filterList[position].id }
                if (visibleChildren.isNotEmpty()) {
                    itemView.dropDownButton.animate().rotation(-90f).start()
                    if (filterList[position].image != null) {
                        imageView.visibility = View.VISIBLE
                        filterList[position].image?.let { itemView.image_view.setImageResource(it) }
                    } else {
                        params2.leftMargin = 80 * (filterList[position].indentLevel + 1) - 20
                        itemView.image_view.setImageResource(android.R.color.transparent)
                        imageView.visibility = View.GONE
                    }

                } else if (visibleChildren.isEmpty()) {
                    itemView.dropDownButton.animate().rotation(-0f).start()
                    if (filterList[position].expandImage != null) {
                        filterList[position].expandImage?.let {
                            itemView.image_view.setImageResource(
                                it
                            )
                        }
                        imageView.visibility = View.VISIBLE
                        params2.leftMargin = 0
                    }
                }
                // Logic for updating the list with the children of the clicked item
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = filterList[position]
                    var clickedItemChildren = findAllChildren(clickedItem, list)
                    var itemsAdded = 0

                    val searchList = clickedItemChildren.filter {
                        it.text.toLowerCase(Locale.ROOT).contains(searchQuery)
                    }
                    if (isFiltering) {
                        if (searchList.isNotEmpty()) {
                            var allChildren = mutableListOf<Item>()
                            var allParents = mutableListOf<Item>()

                            clickedItemChildren = arrayListOf()
                            clickedItemChildren.addAll(searchList)
                            clickedItemChildren.forEach { item ->
                                if (item.indentLevel == clickedItem.indentLevel + 1) {
                                    allChildren = findAllChildren(item, list, allChildren)
                                    allChildren.remove(clickedItem)
                                } else {
                                    allParents = findAllParents(item, list, allParents)
                                    allParents.remove(clickedItem)

                                }
                            }
                            val filterParents =
                                allParents.filter { it.indentLevel < clickedItem.indentLevel }
                            allParents.removeAll(filterParents)
                            clickedItemChildren.addAll(allParents)
                            clickedItemChildren.addAll(allChildren)
                            clickedItemChildren = destroyDuplicates(clickedItemChildren)
                        }

                    }
                    for (i in clickedItemChildren.indices) {
                        // looping through all the children in the list
                        val currentChild = clickedItemChildren[i]
                        // grabbing the position of the current child in the visible/display/filter list
                        val childPosition = filterList.indexOf(currentChild)

                        // If the Item is currently visible, and the filterList contains it, meaning it's displaying on the picker
                        if (currentChild.visibility && filterList.contains(
                                currentChild
                            )
                        )
                        // set the visibility to false, and remove it from the filterList so it no longer shows up on the picker
                        {
                            currentChild.visibility = false
                            filterList.removeAt(childPosition)
                            notifyItemRemoved(childPosition)
                        }
                        // When adding to the filterList, we only want to add children that are one level higher than the parent.
                        else if (clickedItem.indentLevel == currentChild.indentLevel - 1) {
                            currentChild.visibility = true
                            itemsAdded++
                            // When adding items it's important to know the position of the item we clicked on as well as how many items we've already added below that clicked item
                            filterList.add(position + itemsAdded, currentChild)
                            notifyItemInserted(position + itemsAdded)
                        }
                    }
                    if (!isFiltering) {
                        originalFilterList = filterList.toMutableList()
                    }
                }
            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(newText: CharSequence?): FilterResults {
                if (newText!!.isNotEmpty()) {
                    var parents = mutableListOf<Item>()
                    isFiltering = true
                    filterList.clear()
                    searchQuery = newText.toString().toLowerCase(Locale.getDefault())
                    list.forEach {
                        if (it.text.toLowerCase(Locale.getDefault()).contains(searchQuery)) {
                            list[list.indexOf(it)].visibility = true
                            parents = findAllParents(it, list, parents)

                        } else {
                            it.visibility = false
                        }
                    }
                    parents.forEach { item ->
                        item.visibility = true
                    }
                    filterList.addAll(
                        recursiveSortList(null, destroyDuplicates(parents), mutableListOf())
                    )
                } else {
                    list.forEach {
                        it.visibility = originalFilterList.contains(it)
                    }
                    filterList = originalFilterList.toMutableList()
                    isFiltering = false
                }

                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                results?.values?.let {
                    filterList = it as MutableList<Item>
                }
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount() = filterList.size

    /**
     * Function that makes sure there's distinct id's in the list, no duplicate ids
     */
    private fun destroyDuplicates(list: MutableList<Item>): MutableList<Item> {
        return list.distinctBy { it.id } as MutableList<Item>
    }

    /**
     * Function that returns the passed in list in an order that allows it to show up properly in the RecyclerView, Parents next to children and in alphabetical order.
     */
    private fun recursiveSortList(
        child: Item?,
        unsortedList: MutableList<Item>,
        sortedList: MutableList<Item>
    ): MutableList<Item> {
        if (child == null) {
            val roots = unsortedList.filter { it.indentLevel == 0 }.sortedBy { it.text }
            for (root in roots) {
                recursiveSortList(root, unsortedList, sortedList)
            }
        } else {
            sortedList.add(child)
            val childChildren = unsortedList.filter { it.parentId == child.id }.sortedBy { it.text }
            if (childChildren.isNotEmpty()) {
                for (grandChild in childChildren) {
                    recursiveSortList(grandChild, unsortedList, sortedList)
                }
            }

        }
        return destroyDuplicates(sortedList)
    }

    /**
     * Function where you pass in the Parent, and receive a list of all that parents children,grandchildren...
     */
    private fun findAllChildren(
        parent: Item,
        filterList: MutableList<Item>,
        childList: MutableList<Item> = mutableListOf()
    ): MutableList<Item> {
        val parentsChildren = filterList.filter { it.parentId == parent.id }
        if (parentsChildren.isNotEmpty()) {
            childList.addAll(parentsChildren)
            for (child in parentsChildren) {
                findAllChildren(child, filterList, childList)
            }
        }
        return childList
    }

    /**
     * Function where you pass in the child, and receive a list of all that childs, parents, grandparents etc..
     */
    private fun findAllParents(
        child: Item,
        filterList: MutableList<Item>,
        parentList: MutableList<Item> = mutableListOf<Item>()
    ): MutableList<Item> {
        parentList.add(child)
        if (child.parentId != "null") {
            val childParents = filterList.filter { it.id == child.parentId }
            if (childParents.isNotEmpty()) {
                for (parent in childParents) {
                    findAllParents(parent, filterList, parentList)
                }
            }
        }
        return parentList
    }

}