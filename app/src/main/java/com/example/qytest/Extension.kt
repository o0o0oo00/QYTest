package com.example.qytest

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.qytest.span.CenterImageSpan
import com.example.qytest.span.CenterImageTextSpan
import com.example.qytest.span.CornerBgImageTextSpan


val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    ).toInt()

fun drawableToBitmap(w: Int, h: Int, drawable: Drawable): Bitmap {
    val config =
        if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
    val bitmap = Bitmap.createBitmap(w, h, config)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, w, h)
    drawable.draw(canvas)
    return bitmap
}

fun TextView.buildText(
    name: String,
    gender: Int? = null,
    level: Int? = null,
    title: String? = null,
    massage: String = ""
) {
    var cursor: Int = name.length
    val string = SpannableStringBuilder(name)
    val nameColorSpan = ForegroundColorSpan(context.getColor(R.color.name_color))
    val nameSizeSpan = AbsoluteSizeSpan(12, true)
    string.setSpan(nameColorSpan, 0, cursor, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    string.setSpan(nameSizeSpan, 0, cursor, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    if (gender != null) {
        ContextCompat.getDrawable(context, R.drawable.ic_nv)?.also {
            it.setBounds(0, 0, 12f.dp, 12f.dp)
            val genderSpan = CenterImageSpan(it)
            string.append(" ")
            string.setSpan(genderSpan, cursor, ++cursor, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
    level?.also { level ->
        ContextCompat.getDrawable(context, R.drawable.dengji)?.also { drawable ->
            val cornerBgImageTextSpan = CornerBgImageTextSpan(
                drawable, level.toString(), context.getColor(R.color.teal_200)
            )
            string.append(" ")
            string.setSpan(
                cornerBgImageTextSpan, cursor, ++cursor, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
    }
    title?.also {
        ContextCompat.getDrawable(context, R.drawable.zhanglao)?.also { drawable ->
            val s = drawable.intrinsicHeight.toFloat() / drawable.intrinsicWidth.toFloat()
            val w = 12f.dp.toFloat() / s
            drawable.setBounds(0, 0, w.toInt(), 12f.dp)
            val genderSpan = CenterImageTextSpan(it, drawable)
            string.append(" ")
            string.setSpan(genderSpan, cursor, ++cursor, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
    val msgColorSpan = ForegroundColorSpan(context.getColor(R.color.text_color))
    string.append(massage)
    string.setSpan(
        msgColorSpan, cursor, cursor + massage.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )
    val msgSizeSpan = AbsoluteSizeSpan(12, true)
    string.setSpan(
        msgSizeSpan, cursor, cursor + massage.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )

    text = string
}
