package com.andreesperanca.gymde.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.EditAccountComponentBinding


class ConfigureCustomComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mTitle = "Title "
    private var mDescription = "Description"

    @DrawableRes
    private var mIcon = R.drawable.ic_me

    private val binding =
        EditAccountComponentBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setup(context, attrs)
        binding.tvDescription.text = mDescription
        binding.tvWorkoutTitle.text = mTitle
        binding.ivIcon.setImageResource(mIcon)
    }


    fun edit(
        action: () -> Unit
    ) {
        action()
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        val typedArray =
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ConfigureAccountComponent,
                0,
                0
            ).apply {
                try {
                    mTitle = getString(R.styleable.ConfigureAccountComponent_mTitle).toString()
                    mIcon =
                        getResourceId(R.styleable.ConfigureAccountComponent_mIcon, R.drawable.ic_me)
                    mDescription =
                        getString(R.styleable.ConfigureAccountComponent_mDescription).toString()
                } finally {
                    recycle()
                }
            }
    }
}