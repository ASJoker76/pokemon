package pokemon.co.id.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import pokemon.co.id.adapter.PokemonNewAdapter
import pokemon.co.id.databinding.FragmentHomeBinding
import pokemon.co.id.utils.GridSpacingItemDecoration
import pokemon.co.id.utils.RecyclerList
import pokemon.co.id.utils.Results
import pokemon.co.id.viewmodel.HomeViewModel
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var viewModel: HomeViewModel? = null
    private lateinit var binding: FragmentHomeBinding
    private var pokemonArrayList: ArrayList<Results>? = null
    private var pokemonAdapter: PokemonNewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadtable()
        //callApiList();
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel?.getRecyclerListDataObserver()?.observe(viewLifecycleOwner, Observer<RecyclerList>{
            if(it != null) {
                //update the adapter
                pokemonAdapter?.setDataList(it.results as ArrayList<Results>)
            } else {

            }
        })
        viewModel?.panggilapi()

        return binding.root
    }

//    private fun callApiList() {
//        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retroInstance.getPokemon()
//        call.enqueue(object : Callback<RecyclerList> {
//            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
//                if(response.isSuccessful){
//                    Log.e("isi data", response.body().toString())
//                    val resListPokemon: RecyclerList? = response.body()
//                    pokemonAdapter?.setDataList(resListPokemon?.results as ArrayList<Results>)
//                } else {
//
//                }
//            }
//        })
//    }

    private fun loadtable() {
        pokemonArrayList = ArrayList<Results>()
        pokemonAdapter = PokemonNewAdapter(this)
        binding.rvList.setAdapter(AlphaInAnimationAdapter(pokemonAdapter!!))
        binding.rvList.setLayoutManager(
            GridLayoutManager(
                getActivity(),
                3,
                GridLayoutManager.VERTICAL,
                false
            )
        )
        binding.rvList.addItemDecoration(GridSpacingItemDecoration(2, 2, true, 2))
        val alphaInAnimationAdapter = AlphaInAnimationAdapter(pokemonAdapter!!)
        alphaInAnimationAdapter.setDuration(1000)
        alphaInAnimationAdapter.setInterpolator(OvershootInterpolator())
        alphaInAnimationAdapter.setFirstOnly(false)
    }
}