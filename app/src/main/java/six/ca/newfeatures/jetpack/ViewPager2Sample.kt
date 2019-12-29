package six.ca.newfeatures.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.act_vp.*
import six.ca.newfeatures.R

/**
 * @author hellenxu
 * @date 2019-05-27
 * Copyright 2019 Six. All rights reserved.
 */
class ViewPager2Sample : AppCompatActivity() {
    private val vpFragments = ArrayList<Fragment>()
    private val vp2Fragments = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_vp)

        setupVps()
    }

    private fun setupVps() {
        for (i in 0..3) {
            vpFragments.add(createFragment(CommonFragment.ARGUMENT_TITLE + i))
            vp2Fragments.add(createFragment(CommonFragment.ARGUMENT_TITLE + i + 2))
        }

        vp.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int {
                return vpFragments.size
            }

            override fun getItem(position: Int): Fragment {
                return vpFragments[position]
            }
        }
        vp2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return vp2Fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return vp2Fragments[position]
            }

        }
    }

    private fun createFragment(title: String): Fragment {
        val fragment = CommonFragment()
        val bundle = Bundle()
        bundle.putString(CommonFragment.ARGUMENT_TITLE, title)
        fragment.arguments = bundle
        return fragment
    }
}

class CommonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_common, container, false)
        rootView.findViewById<TextView>(R.id.tvTitle).text = arguments?.getString(ARGUMENT_TITLE, "")
        return rootView
    }

    companion object {
        const val ARGUMENT_TITLE = "title"
    }
}