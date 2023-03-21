package com.example.qytest.span

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.style.ReplacementSpan
import com.example.qytest.dp
import com.example.qytest.drawableToBitmap
import com.example.qytest.px

class CornerBgImageTextSpan(
    private val drawable: Drawable,
    private val mText: String,
    private val bgColor: Int
) : ReplacementSpan() {
    private val width = 26f.dp
    private val height = 12f.dp
    private val corner = 100f
    private val margin = 4f.dp
    override fun getSize(
        paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?
    ): Int {
        return width + margin * 2
    }

    private val rect = RectF()
    private val bgPaint = Paint().apply {
        color = bgColor
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 8f.px
        isAntiAlias = true
    }
    private val bitmap by lazy {
        drawableToBitmap(10f.dp, 10f.dp, drawable)
    }
    private lateinit var metrics: Paint.FontMetrics

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
        metrics = paint.fontMetrics
        val textHeight = metrics.descent - metrics.ascent
        val startY = y + (textHeight - height) / 2 + metrics.ascent
        rect.set(x + margin / 2, startY, x + width + margin / 2, startY + height.toFloat())
        canvas.drawRoundRect(rect, corner, corner, bgPaint)
        canvas.drawBitmap(bitmap, rect.left + 1f.px, rect.top + 1f.px, paint)

        val fontMetrics = textPaint.fontMetrics
        val textRectHeight = fontMetrics.bottom - fontMetrics.top
        canvas.drawText(
            mText,
            x + width / 2 + margin / 2,
            startY + (height - textRectHeight) / 2 - fontMetrics.top,
            textPaint
        )
    }
}