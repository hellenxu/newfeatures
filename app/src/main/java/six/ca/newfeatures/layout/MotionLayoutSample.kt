package six.ca.newfeatures.layout

import android.app.Activity
import android.os.Bundle
import six.ca.newfeatures.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-04-15.
 */
class MotionLayoutSample: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.act_motion)
//        setContentView(R.layout.act_motion_fab)
        setContentView(R.layout.act_motion_attr)
    }
}