package com.marko590.raaprojekat.adapter

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class SwipeActionRight(var adapter: ArticleAdapter): ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val vh: ArticleAdapter.ViewHolder =
            viewHolder as ArticleAdapter.ViewHolder
        viewHolder

    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 2f
    }
    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        val maxRightShift = 200f
        val vh: ArticleAdapter.ViewHolder =
            viewHolder as ArticleAdapter.ViewHolder

        // Don't let the sliding view slide more than maxRightShift amount.
        if (dX > maxRightShift ) {

            super.onChildDraw(c, recyclerView, viewHolder, 200f, dY, actionState, isCurrentlyActive)
            return
        }

        vh.linearLayout.setTranslationX(dX)

        adapter.notifyItemChanged(viewHolder.adapterPosition)

        // Shift just the CardView and leave underlying views.

    }

}