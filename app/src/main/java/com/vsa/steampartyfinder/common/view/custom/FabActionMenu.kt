package com.vsa.steampartyfinder.common.view.custom

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.RelativeLayout
import com.vsa.steampartyfinder.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_fab_action_menu.*


/**
 * Created by Alberto Vecina Sánchez on 16/12/17.
 */
class FabActionMenu : RelativeLayout, LayoutContainer {
    override val containerView: View?

    constructor(context: Context?) : super(context) {
        containerView = inflateView(context)
        initViews()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        containerView = inflateView(context)
        initViews()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        containerView = inflateView(context)
        initViews()
    }


    private var mIsFabOpen: Boolean = false
    private var mOriginalHeight: Int = 0
    private var showAnimatorSet: AnimatorSet = AnimatorSet()
    private var hideAnimatorSet: AnimatorSet = AnimatorSet()
    private var mOnAcceptButtonClickListener: OnAcceptButtonClickListener? = null


    private fun inflateView(context: Context?): View {
        return LayoutInflater.from(context).inflate(R.layout.view_fab_action_menu, this)
    }

    private fun initViews() {
        gravity = Gravity.BOTTOM
        fabMain.setOnClickListener {
            if (!mIsFabOpen)
                showFABMenu()
            else
                closeFABMenu()
        }
    }

    private fun showFABMenu() {
        mIsFabOpen = true
        mOriginalHeight = fabMain.height + 2 * resources.getDimension(R.dimen.fab_button_margin).toInt()

        val fabSize: Int = fab1.height + 2 * resources.getDimension(R.dimen.fab_button_margin).toInt()

        if (hideAnimatorSet.isRunning)
            hideAnimatorSet.removeAllListeners()
        showAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(fab1, "translationY", -fabSize.toFloat()),
                ObjectAnimator.ofFloat(fab2, "translationY", -2 * fabSize.toFloat()),
                ObjectAnimator.ofFloat(fab3, "translationY", -3 * fabSize.toFloat()))
        showAnimatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
                layoutParams.height = mOriginalHeight + 3 * fabSize
                requestLayout()
            }

        })
        showAnimatorSet.start()
        showCheck()
    }

    private fun closeFABMenu() {
        mIsFabOpen = false

        if (showAnimatorSet.isRunning)
            showAnimatorSet.removeAllListeners()
        hideAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(fab1, "translationY", 0f),
                ObjectAnimator.ofFloat(fab2, "translationY", 0f),
                ObjectAnimator.ofFloat(fab3, "translationY", 0f))
        hideAnimatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                layoutParams.height = mOriginalHeight
                requestLayout()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }

        })
        hideAnimatorSet.start()
        mOnAcceptButtonClickListener?.onAcceptButtonClick(arrayOf(fab3.isChecked, fab2.isChecked, fab1.isChecked))
        showFilter()
    }

    private fun showCheck() {
        val radius: Float = fabMain.width / 2f
        val anim = ViewAnimationUtils.createCircularReveal(fabMain, radius.toInt(), radius.toInt(), 0f, radius)
        fabMain.setImageResource(R.drawable.ic_accept)
        anim.start()
    }

    private fun showFilter() {
        val radius: Float = fabMain.width / 2f
        val anim = ViewAnimationUtils.createCircularReveal(fabMain, radius.toInt(), radius.toInt(), 0f, radius)
        fabMain.setImageResource(R.drawable.ic_filter)
        anim.start()
    }

    fun setOnAcceptButtonClickListener(onAcceptButtonClickListener: OnAcceptButtonClickListener) {
        mOnAcceptButtonClickListener = onAcceptButtonClickListener
    }

    interface OnAcceptButtonClickListener {
        fun onAcceptButtonClick(status: Array<Boolean>)
    }

}