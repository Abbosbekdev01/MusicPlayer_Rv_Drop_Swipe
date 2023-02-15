package uz.abbosbek.musicplayer_view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.musicplayer_view.R
import uz.abbosbek.musicplayer_view.databinding.ItemRvBinding
import uz.abbosbek.musicplayer_view.models.User
import uz.abbosbek.musicplayer_view.utils.ItemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter(val list: ArrayList<User>, val context: Context) :
    RecyclerView.Adapter<UserAdapter.Vh>(), ItemTouchHelperAdapter {

    inner class Vh(val itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(user: User, position: Int) {
            itemRv.tvName.text = user.name
            itemRv.tvNom.text = user.nomi


            val animation = AnimationUtils.loadAnimation(context, R.anim.item_anim)
            itemRv.root.startAnimation(animation)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i)
            }
        } else {
            for (i in fromPosition until toPosition + 1) {
                Collections.swap(list, i, i)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}