package com.example.poverty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        var textView:TextView=findViewById(R.id.textViewAboutUsTitle)
        var text= "TOGETHER WE MAKE THE POOR HEALTHY"
        var spannableString = SpannableString(text)
        var foregroundSpan =ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark))
        spannableString.setSpan(foregroundSpan,25,31,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.setText(text)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_actionback_24dp)

        Picasso.with(this).load("https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/aboutUs.jpg").into(imageViewAboutUs)
    }
}
