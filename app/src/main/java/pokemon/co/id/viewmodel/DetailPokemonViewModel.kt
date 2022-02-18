package pokemon.co.id.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pokemon.co.id.connection.Host
import pokemon.co.id.connection.RetroInstance
import pokemon.co.id.connection.RetroService
import pokemon.co.id.utils.Evolution
import pokemon.co.id.utils.RecyclerList
import pokemon.co.id.utils.detailRecylerlist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokemonViewModel : ViewModel() {

    lateinit var pokemonLiveData: MutableLiveData<detailRecylerlist>
    lateinit var evolutionLiveData: MutableLiveData<Evolution>

    init {
        pokemonLiveData = MutableLiveData<detailRecylerlist>()
        evolutionLiveData = MutableLiveData<Evolution>()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<detailRecylerlist>? {
        return pokemonLiveData
    }

    fun getRecycler2ListDataObserver(): MutableLiveData<Evolution>? {
        return evolutionLiveData
    }

    fun panggildetailapi(nama : String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDetailPokemon(nama)
        call.enqueue(object : Callback<detailRecylerlist> {
            override fun onFailure(call: Call<detailRecylerlist>, t: Throwable) {
                pokemonLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<detailRecylerlist>, response: Response<detailRecylerlist>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    pokemonLiveData?.postValue(response.body())
                } else {
                    pokemonLiveData?.postValue(null)
                }
            }
        })
    }

    fun panggilevolution(id : Int) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getEvolutionPokemon(id)
        call.enqueue(object : Callback<Evolution> {
            override fun onFailure(call: Call<Evolution>, t: Throwable) {
                evolutionLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<Evolution>, response: Response<Evolution>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    evolutionLiveData?.postValue(response.body())
                } else {
                    evolutionLiveData?.postValue(null)
                }
            }
        })
    }
}