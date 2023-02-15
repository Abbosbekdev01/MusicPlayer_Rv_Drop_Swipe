package uz.abbosbek.musicplayer_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.musicplayer_view.adapters.UserAdapter
import uz.abbosbek.musicplayer_view.databinding.ActivityMainBinding
import uz.abbosbek.musicplayer_view.models.User
import uz.abbosbek.musicplayer_view.utils.ItemTouchHelperAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<User>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        userAdapter = UserAdapter(list, this)
        binding.rvLiner.adapter = userAdapter

        val itemTouchHelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }

        val itemTouch = ItemTouchHelper(itemTouchHelper)
        with(itemTouch) {
            attachToRecyclerView(binding.rvLiner)
        }
    }

    private fun loadData() {
        list = ArrayList()
        for (i in 0 until 100) {
            list.add(User("Janob Rasul", "Coptiva"))
            list.add(User("Janob Rasul", "Coptiva"))
            list.add(User("Janob Rasul", "Coptiva"))
            list.add(User("Janob Rasul", "Coptiva"))
        }
    }
}