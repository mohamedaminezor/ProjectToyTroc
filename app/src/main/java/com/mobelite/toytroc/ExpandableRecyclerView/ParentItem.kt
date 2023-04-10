package com.mobelite.toytroc.ExpandableRecyclerView

data class ParentItem(val title : String, val ImageUrl: String, val childitemList: ArrayList<ChildItem>, var isExpandable: Boolean = false)