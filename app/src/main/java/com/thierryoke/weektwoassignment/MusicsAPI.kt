package com.thierryoke.weektwoassignment

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.google.com/url?sa=D&q=https://itunes.apple.com/search%3Fterm%3Drock%26amp%3Bamp%3Bmedia%3Dmusic%26amp%3Bamp%3Bentity%3Dsong%26amp%3Bamp%3Blimit%3D50&ust=1605383820000000&usg=AOvVaw25czAZx_kvtGCf6HImG7Ar&hl=en

//https://itunes.apple.com/search?term=rock&media=music&entity=song&limit=50
interface MusicsAPI {

    @GET("search")
        fun getMusic(@Query("term")term: String,
                        @Query("media")media: String = "music",
                        @Query("entity")entity: String = "song",
                        @Query("limit")limit: Int  = 50): Call<MusicResponse>

    companion object{
            fun initretrofit(): MusicsAPI {
                return Retrofit.Builder().baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(MusicsAPI::class.java)
            }
        }
    }


