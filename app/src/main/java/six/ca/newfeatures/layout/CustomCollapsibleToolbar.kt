package six.ca.newfeatures.layout

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-05-02.
 */
class CustomCollapsibleToolbar @JvmOverloads constructor(ctx: Context,
                                                         attrs: AttributeSet? = null,
                                                         defStyleAttr: Int = 0)
    : MotionLayout(ctx, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBar: AppBarLayout?, p1: Int) {
        progress = -p1 / appBar?.totalScrollRange?.toFloat()!!
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as AppBarLayout).addOnOffsetChangedListener(this)
    }
}