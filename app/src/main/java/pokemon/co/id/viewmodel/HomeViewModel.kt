package pokemon.co.id.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pokemon.co.id.connection.Host.PAGE_SIZE
import pokemon.co.id.connection.RetroInstance
import pokemon.co.id.connection.RetroService
import pokemon.co.id.utils.RecyclerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var curPage = 0

    lateinit var pokemonLiveData: MutableLiveData<RecyclerList>

    init {
        pokemonLiveData = MutableLiveData<RecyclerList>()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList>? {
        return pokemonLiveData
    }


    fun panggilapi() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getPokemon(PAGE_SIZE, curPage * PAGE_SIZE)
        call.enqueue(object : Callback<RecyclerList>{
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                pokemonLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    pokemonLiveData?.postValue(response.body())
                } else {
                    pokemonLiveData?.postValue(null)
                }
            }
        })
    }
}


