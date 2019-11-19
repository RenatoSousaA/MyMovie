package br.com.fiap.androidfinalprojeto.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.androidfinalprojeto.R
import kotlinx.android.synthetic.main.all_movies_recyclerview.view.*

abstract class SwipeToDeleteCallback internal constructor(internal var mContext: Context) :

    ItemTouchHelper.Callback() {

    private val mClearPaint: Paint
    private val mBackground: ColorDrawable
    private val backgroundColor: Int
    private val deleteDrawable: Drawable
    private val intrinsicWidth: Int
    private val intrinsicHeight: Int
    private val marginTop: Int
    private val heightCardMovie: Int

    init {

        mBackground = ColorDrawable()
        backgroundColor = Color.parseColor("#CD233E")
        mClearPaint = Paint()
        mClearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        deleteDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_delete)!!
        intrinsicWidth = deleteDrawable.intrinsicWidth
        intrinsicHeight = deleteDrawable.intrinsicHeight
        marginTop = mContext.resources.getDimension(R.dimen.margin_top_card_delete).toInt()
        heightCardMovie = mContext.resources.getDimension(R.dimen.height_card_movies).toInt()
    }

    override fun getMovementFlags(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder, @NonNull viewHolder1: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onChildDraw(
        @NonNull c: Canvas, @NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val itemHeight = itemView.height

        val isCancelled = dX == 0f && !isCurrentlyActive

        if (isCancelled) {
            clearCanvas(
                c,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        mBackground.color = backgroundColor
        mBackground.setBounds(
            itemView.right + dX.toInt(),
            itemView.top + marginTop,
            itemView.right,
            itemView.bottom
        )
        mBackground.draw(c)

        val deleteIconTop = itemView.top + (itemHeight - heightCardMovie / 2) - (marginTop * 2)
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 4
        val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
        val deleteIconRight = itemView.right - deleteIconMargin
        val deleteIconBottom = deleteIconTop + intrinsicHeight


        deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteDrawable.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }

    private fun clearCanvas(c: Canvas, left: Float?, top: Float?, right: Float?, bottom: Float?) {
        c.drawRect(left!!, top!!, right!!, bottom!!, mClearPaint)

    }

    override fun getSwipeThreshold(@NonNull viewHolder: RecyclerView.ViewHolder): Float {
        return 0.7f
    }

}
