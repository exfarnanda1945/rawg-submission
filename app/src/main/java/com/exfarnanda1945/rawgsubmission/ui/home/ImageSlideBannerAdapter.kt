package com.exfarnanda1945.rawgsubmission.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.exfarnanda1945.rawgsubmission.R
import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponseResultsItem
import com.exfarnanda1945.rawgsubmission.utils.loadImage

class ImageSlideBannerAdapter(private val context: Context, private var data: List<GameResponseResultsItem?>?) :
    PagerAdapter() {

    override fun getCount(): Int {
        return data?.size!!
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.image_slider_banner, container, false)
        val itemImagesSlider = view.findViewById<ImageView>(R.id.item_image_slider_banner)
        val itemGameName = view.findViewById<TextView>(R.id.item_name_slider_banner )

        data?.get(position).let {
            loadImage(context,it?.backgroundImage,itemImagesSlider)
            itemGameName.text = it?.name
        }


        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}