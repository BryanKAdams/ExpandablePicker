package com.bryankeltonadams.expandablepicker

import java.io.Serializable

class TestItem(
    var id: String,
    var parentId: String,
    var image: Int?,
    var expandImage: Int?,
    var text: String,
    var indentLevel: Int
) : PickerData(), Serializable {
    override fun getPickerDataId(): String {
        return id
    }

    override fun getPickerDataParentId(): String {
        return parentId
    }

    override fun getPickerDataImage(): Int? {
        return image
    }

    override fun getPickerDataExpandImage(): Int? {
        return expandImage
    }

    override fun getPickerDataText(): String {
        return text
    }

    override fun getPickerDataIndentLevel(): Int {
        return indentLevel
    }
}