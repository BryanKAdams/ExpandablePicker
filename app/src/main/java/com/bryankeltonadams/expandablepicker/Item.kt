package com.bryankeltonadams.expandablepicker

data class Item(
    var id: String,
    var parentId: String,
    var image: Int?,
    var expandImage: Int?,
    var text: String,
    var row: Int,
    var indentLevel: Int,
    var visibility: Boolean
) {

}
