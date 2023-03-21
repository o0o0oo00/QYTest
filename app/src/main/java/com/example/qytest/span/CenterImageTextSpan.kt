package com.example.qytest.span

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan
import com.example.qytest.dp
import com.example.qytest.px

class CenterImageTextSpan(private val mText: String, drawable: Drawable, private val marginLeft: Int = 4f.dp) :
    ImageSpan(drawable) {

    override fun getSize(
        paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?
    ): Int {
        return drawable.bounds.right + (marginLeft)
    }
    private val width = 26f.dp
    private val height = 12f.dp
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 8f.px
        isAntiAlias = true
    }
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
        canvas.translate(x, startY)
        drawable.draw(canvas)
        canvas.restore()
        val fontMetrics = textPaint.fontMetrics
        val textRectHeight = fontMetrics.bottom - fontMetrics.top
        canvas.drawText(
            mText,
            x + width / 2,
            startY + (height - textRectHeight) / 2 - fontMetrics.top,
            textPaint
        )
    }

}