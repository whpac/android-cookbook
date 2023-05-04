package pl.put.cookbook

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator


class LollipopFragment : Fragment() {
    private lateinit var lollipopOuter: View
    private lateinit var lollipopMiddle: View
    private lateinit var lollipopInner: View

    private var isCollapsed = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lollipop, container, false)

        lollipopOuter = view.findViewById(R.id.lollipop3)
        lollipopMiddle = view.findViewById(R.id.lollipop2)
        lollipopInner = view.findViewById(R.id.lollipop1)

        view.setOnClickListener {
            startAnimation()
        }

        return view
    }

    private fun makeAnimator(target: View, start: Float, end: Float, interpolator: TimeInterpolator): Animator {
        val scaleXAnimator: ObjectAnimator = ObjectAnimator
            .ofFloat(target, "scaleX", start, end)
            .setDuration(1200)
        scaleXAnimator.interpolator = interpolator
        val scaleYAnimator: ObjectAnimator = ObjectAnimator
            .ofFloat(target, "scaleY", start, end)
            .setDuration(1200)
        scaleYAnimator.interpolator = interpolator

        val animator = AnimatorSet()
        animator.playTogether(
            scaleXAnimator,
            scaleYAnimator
        )
        return animator
    }

    private fun startAnimation() {
        val start = if(isCollapsed) 0f else 1f
        val end = if(isCollapsed) 1f else 0f
        val interpolator = if(isCollapsed) DecelerateInterpolator() else AccelerateInterpolator()

        val outerAnimator = makeAnimator(lollipopOuter, start, end, interpolator)
        val middleAnimator = makeAnimator(lollipopMiddle, start, end, interpolator)
        val innerAnimator = makeAnimator(lollipopInner, start, end, interpolator)

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            outerAnimator,
            middleAnimator,
            innerAnimator
        )
        animatorSet.start()

        isCollapsed = !isCollapsed
    }
}