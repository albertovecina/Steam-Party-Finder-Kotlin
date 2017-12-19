package com.vsa.steampartyfinder.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import com.vsa.steampartyfinder.R


/**
 * Created by Alberto Vecina Sánchez on 16/12/17.
 */
class FabCheckBox : FloatingActionButton {
    constructor(context: Context?) : super(context) {
        initViews()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initViews()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews()
    }

    var isChecked: Boolean = false
    private var mOriginalTintStateList: ColorStateList? = backgroundTintList


    private fun initViews() {
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

    override fun setOnClickListener(l: OnClickListener?) {
    }
}