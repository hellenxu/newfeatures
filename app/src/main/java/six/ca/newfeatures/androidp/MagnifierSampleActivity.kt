package six.ca.newfeatures.androidp

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Magnifier
import kotlinx.android.synthetic.main.act_magnifier.*
import six.ca.newfeatures.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-09-12.
 */
class MagnifierSampleActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_magnifier)

        val magnifier = Magnifier(sampleText)
        sampleText.setOnTouchListener { v, event ->
            when(event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val position = IntArray(2)
                    v.getLocationOnScreen(position)
                    magnifier.show(event.rawX - position[0], event.rawY - position[1] )
                }

                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    magnifier.dismiss()
                }
            }
            true
        }
    }
}