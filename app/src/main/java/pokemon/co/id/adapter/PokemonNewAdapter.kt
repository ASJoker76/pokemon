package pokemon.co.id.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pokemon.co.id.R
import pokemon.co.id.databinding.ListitempokemonBinding
import pokemon.co.id.utils.Results
import pokemon.co.id.view.fragment.DetailPokemonFragment


class PokemonNewAdapter(
    private val myFragment: Fragment
) :
    RecyclerView.Adapter<PokemonNewAdapter.MyViewHolder>() {

    var resultss = ArrayList<Results>()
    private val lastPosition = -1
    private var viewBinding: ListitempokemonBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonNewAdapter.MyViewHolder {
        viewBinding =
            ListitempokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding!!)
    }

    override fun getItemCount() = resultss.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val (name) = dataPengiriman[position]
        val resResults = resultss[position]

        val number = if(resResults.url.endsWith("/")) {
            resResults.url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            resResults.url.takeLastWhile { it.isDigit() }
        }
        //val url2 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
        val url2 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${number}.png"
        //val url2 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/${number}.svg"



        Glide.with(myFragment)
            .load(url2)
            .centerCrop()
            .into(holder.viewBinding.tvGambarPokemon)

        holder.viewBinding.tvNamaPokemon.setText(resResults.name)

        holder.viewBinding.clView.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            bundle.putInt("id_pokemon",number.toInt())
            bundle.putString("nama_pokemon", resResults.name)
            val fragementIntent = DetailPokemonFragment()
            val transaction = myFragment.activity?.supportFragmentManager?.beginTransaction()

            transaction?.replace(R.id.fl_frame, fragementIntent)
            fragementIntent.setArguments(bundle)
            transaction!!.addToBackStack(null)
            transaction?.commit()
        })
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = FADE_DURATION.toLong()
            itemView.startAnimation(anim)
        }
    }



    class MyViewHolder(binding: ListitempokemonBinding) : RecyclerView.ViewHolder(binding.getRoot()) {
        val viewBinding: ListitempokemonBinding

        init {
            viewBinding = binding
        }
    }

    companion object {
        private const val FADE_DURATION = 1000 //FADE_DURATION in milliseconds
    }

    fun setDataList(data :  ArrayList<Results>) {
        this.resultss = data
        notifyDataSetChanged()
    }
}