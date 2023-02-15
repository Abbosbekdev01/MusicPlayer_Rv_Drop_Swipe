package uz.abbosbek.musicplayer_view.utils

interface ItemTouchHelperAdapter {

    /** Item larni ko'chirish uchun ishlatiladi*/
    fun onItemMove(fromPosition:Int, toPosition:Int)

    /** Item larni ong yoki chapga o'tkazish uchun ishlatiladi*/
    fun onItemDismiss(position:Int)
}