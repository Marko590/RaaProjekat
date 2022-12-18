package com.marko590.raaprojekat.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView



class SwipeActionLeft(var adapter: ArticleAdapter): ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var pos=viewHolder.adapterPosition
        adapter.content.removeAt(pos)
        adapter.notifyItemRemoved(pos)
    }

}