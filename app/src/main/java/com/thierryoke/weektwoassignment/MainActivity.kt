package com.thierryoke.weektwoassignment

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.item_recycler.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext
import java.text.ParsePosition

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myRocklist : List<MusicItem>
    lateinit var myClassiclist : List<MusicItem>
    lateinit var myPoplist : List<MusicItem>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_music)
        val tabLayout : TabLayout = findViewById(R.id.tab_layout)

        tabLayout.addOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener
                {
                    override fun onTabSelected(tab: TabLayout.Tab?) {

                        if(tab?.position ==0) {
                            MusicsAPI.initretrofit().getMusic("rock").enqueue(
                                    object : Callback<MusicResponse>, ClickInterface {
                                override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>)
                                {
                                    if (response.isSuccessful)
                                    {
                                        response.body()?.let {
                                            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
                                            recyclerView.adapter = MyAdapter(it.results, this)
                                            myRocklist = it.results
                                        }
                                    }
                                }
                                override fun onFailure(
                                        call: Call<MusicResponse>,
                                        t: Throwable
                                ) {
                                }
                                override fun onCellClickListener(position: Int)
                                {
                                    val itent = Intent()
                                    itent.setAction(android.content.Intent.ACTION_VIEW);
                                    itent.setDataAndType(Uri.parse(myRocklist[position].previewUrl), "audio/*")
                                    startActivity(itent)                                        }

                                    })
                        }
                        else if(tab?.position == 1)
                        {
                            MusicsAPI.initretrofit().getMusic("classic").enqueue(
                                    object : Callback<MusicResponse>, ClickInterface {
                                        override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>)
                                        {
                                            if (response.isSuccessful)
                                            {
                                                response.body()?.let {
                                                    recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
                                                    recyclerView.adapter = MyAdapter(it.results, this)
                                                    myClassiclist= it.results
                                                }
                                            }
                                        }
                                        override fun onFailure(
                                                call: Call<MusicResponse>,
                                                t: Throwable
                                        ) {
                                            Toast.makeText(baseContext,"$call.",Toast.LENGTH_SHORT).show()
                                            println(call)

                                        }
                                        override fun onCellClickListener(position: Int) {
                                            val itent = Intent()
                                            itent.setAction(android.content.Intent.ACTION_VIEW);
                                            itent.setDataAndType(Uri.parse(myClassiclist[position].previewUrl), "audio/*")
                                            startActivity(itent)                                        }


                                    })
                        }
                        else if (tab?.position==2)
                        {
                            MusicsAPI.initretrofit().getMusic("pop").enqueue(
                                    object : Callback<MusicResponse>, ClickInterface {
                                        override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>)
                                        {
                                            if (response.isSuccessful)
                                            {
                                                response.body()?.let {
                                                    recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
                                                    recyclerView.adapter = MyAdapter(it.results, this )
                                                    myPoplist = it.results
                                                }
                                            }
                                            }
                                        override fun onFailure(
                                                call: Call<MusicResponse>,
                                                t: Throwable) {
                                            Toast.makeText(baseContext,"$call.",Toast.LENGTH_SHORT).show()
                                            println(call)
                                        }
                                        override fun onCellClickListener(position: Int) {
                                            val itent = Intent()
                                            itent.setAction(android.content.Intent.ACTION_VIEW);
                                            itent.setDataAndType(Uri.parse(myPoplist[position].previewUrl), "audio/*")
                                            startActivity(itent)
                                        }
                                    })
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}

                }
        )

    }




}



