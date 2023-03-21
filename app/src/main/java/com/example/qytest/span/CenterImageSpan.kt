package com.example.qytest.span

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan
import com.example.qytest.dp

class CenterImageSpan(drawable: Drawable, private val marginLeft: Int = 4f.dp) :
    ImageSpan(drawable) {

    override fun getSize(
        paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?
    ): Int {
        return drawable.bounds.right + (marginLeft * 2)
    }
    private val height = 12f.dp
    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val metrics = paint.fontMetrics
        val textHeight = metrics.descent - metrics.ascent
        val startY = y + (textHeight - height) / 2 + metrics.ascent
        canvas.save()
        canvas.translate(x + marginLeft, startY)
        drawable.draw(canvas)
        canvas.restore()
    }

}