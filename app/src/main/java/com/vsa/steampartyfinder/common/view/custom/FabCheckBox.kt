package com.vsa.steampartyfinder.common.view.custom

import android.content.Context
import android.content.res.ColorStateList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import com.vsa.steampartyfinder.R


/**
 * Created by Alberto Vecina Sánchez on 16/12/17.
 */
class FabCheckBox @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FloatingActionButton(context, attrs, defStyleAttr) {
    init {
        super.setOnClickListener {
            val radius: Float = width / 2.toFloat()
            if (!isChecked) {
                isChecked = true
                val anim = ViewAnimationUtils.createCircularReveal(this, radius.toInt(), radius.toInt(), 0f, radius)
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorSelected))
                anim.start()
            } else {
                isChecked = false
                val anim = ViewAnimationUtils.createCircularReveal(this, radius.toInt(), radius.toInt(), 0f, radius)
                backgroundTintList = mOriginalTintStateList
                anim.start()
            }

        }
    }


    var isChecked: Boolean = false
    private var mOriginalTintStateList: ColorStateList? = backgroundTintList

    override fun setOnClickListener(l: OnClickListener?) {
        
    }
}