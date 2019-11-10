package com.dc.storedataoffline

interface ItemClickListener {
    fun onItemClick(position : Int,operation : String = "")
}