package com.bryankeltonadams.expandablepicker


abstract class PickerData {

    private var visibility: Boolean = false

    abstract fun getPickerDataId(): String
    abstract fun getPickerDataParentId(): String
    abstract fun getPickerDataImage(): Int?
    abstract fun getPickerDataExpandImage(): Int?
    abstract fun getPickerDataText(): String
    abstract fun getPickerDataIndentLevel(): Int

    fun setPickerDataVisibility(visibility: Boolean) {
        this.visibility = visibility
    }

    fun getPickerDataVisibility(): Boolean {
        if (getPickerDataIndentLevel() == 0) {
            visibility = true
            return visibility
        }
        return visibility
    }

}

