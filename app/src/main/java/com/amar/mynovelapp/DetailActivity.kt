package com.amar.mynovelapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_NOVEL = "extra_novel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val imgDetailPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)


        val novel = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Novel>(EXTRA_NOVEL, Novel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Novel>(EXTRA_NOVEL)
        }

        if (novel != null) {
            tvDetailName.text = novel.name
            tvDetailDescription.text = novel.description
            imgDetailPhoto.setImageResource(novel.photo)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                val message = "https://www.gramedia.com/"

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, message)

                startActivity(Intent.createChooser(intent, "Bagikan melalui"))
            }
        }
    }
}