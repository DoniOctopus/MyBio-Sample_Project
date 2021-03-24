package com.example.first_android.film

import androidx.lifecycle.MutableLiveData
import com.example.first_android.respon_data.ResponseData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmRepository(private val filmAPI: FilmAPI) {

    var film: MutableLiveData<Film> = MutableLiveData<Film>()
    val filmList: MutableLiveData<List<Film>> = MutableLiveData<List<Film>>()

    fun getAllFilm(){

        //enqueue like a coroutine, make new request
        filmAPI.getAllFilm().enqueue(object : Callback<ResponseData> {

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val responses = response.body()

                if (responses?.status == "200"){
                    val res = responses.result
                    val gson = Gson()
                    filmList.value = gson.fromJson(gson.toJson(res), Array<Film>::class.java).toList()

                }
            }
        })
    }

    fun getFilmByID(filmID: String){
        filmAPI.getFilmByID(filmID).enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val responseData = response.body()
                println("ID $responseData")

                if (responseData?.status == "200") {
                    val res = responseData.result
                    val gson = Gson()
                    film.value = gson.fromJson(gson.toJson(res), Film::class.java)
                } else {
                    film.value = Film("", "", "", "", "")
                    println("[ERROR] Data not found")
                }
            }
        })
    }

    fun saveFilm(film: Film) {
        filmAPI.createFilm(film).enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val responseData = response.body()

                if (responseData?.status == "OK") {
                    println("OK")
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}