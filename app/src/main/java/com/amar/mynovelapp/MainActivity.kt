package com.amar.mynovelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvNovels: RecyclerView
    private val list = ArrayList<Novel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        rvNovels = findViewById(R.id.rv_novels)
        rvNovels.setHasFixedSize(true) //fixed size RecyclerView

        list.addAll(getListNovels())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvNovels.layoutManager = LinearLayoutManager(this)
        val listNovelAdapter = ListNovelAdapter(list)
        rvNovels.adapter = listNovelAdapter
    }

    //fungsi memanggil data yang ada di resource string.xml
    private fun getListNovels(): ArrayList<Novel> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listNovel = ArrayList<Novel>()
        for (i in dataName.indices) {
            val novel = Novel(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listNovel.add(novel)
        }
        return listNovel
    }

    //menu item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //berpindah ke halaman about
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intenAbout = Intent(this@MainActivity, AboutActivity::class.java)
                Toast.makeText(this, "About Page", Toast.LENGTH_SHORT).show()
                startActivity(intenAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}