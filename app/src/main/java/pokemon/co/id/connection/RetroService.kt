package pokemon.co.id.connection

import pokemon.co.id.utils.Evolution
import pokemon.co.id.utils.RecyclerList
import pokemon.co.id.utils.detailRecylerlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

    @GET("pokemon")
    fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<RecyclerList>

    @GET("pokemon/{name}")
    fun getDetailPokemon(
        @Path("name") name: String
    ): Call<detailRecylerlist>

    @GET("evolution-chain/{id}")
    fun getEvolutionPokemon(
        @Path("id") id: Int
    ): Call<Evolution>


//    @GET("pokemon")
//    fun getpokemon(): Call<ResListPokemon?>?
}