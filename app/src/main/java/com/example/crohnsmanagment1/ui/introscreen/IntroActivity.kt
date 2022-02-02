package com.example.crohnsmanagment1.ui.introscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.crohnsmanagment1.MainActivity
import com.example.crohnsmanagment1.R
import com.example.crohnsmanagment1.data.models.IntroView
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    lateinit var introView: List<IntroView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        addToIntroView()

        viewPager2.adapter = ViewPagerAdapter(introView)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        circleIndicator.setViewPager(viewPager2)

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                if(position == 2){
                    animationButton()
                }

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })

    }

    private fun animationButton(){
        btn_start_app.visibility = View.VISIBLE

        btn_start_app.animate().apply {
            duration = 1400
            alpha(1f)

            btn_start_app.setOnClickListener{
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
                finish()
            }

        }.start()
    }

    private fun addToIntroView(){
        introView = listOf(
            IntroView("Welcome to Crohn's Management App", R.drawable.ic_launcher_background),
            IntroView("Welcome to Crohn's Management App", R.drawable.ic_launcher_foreground),
            IntroView("Welcome to Crohn's Management App", R.drawable.ic_delete),
        )
    }
}