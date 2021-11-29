package pl.kylos.marczuk.pm_fm_test

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.kylos.marczuk.pm_fm_test.repository.Data

class DataViewHolder(parentView: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parentView.context)
            .inflate(R.layout.data_list_item_view, parentView, false)
    ) {

    fun bind(item: Data) {
        itemView.findViewById<TextView>(R.id.item_title).text = item.title
        itemView.findViewById<TextView>(R.id.item_date).text = item.modificationDate
        itemView.findViewById<TextView>(R.id.item_description).text = item.description
        itemView.findViewById<ImageView>(R.id.item_image).setImageResource(0)
        Picasso.get().load(item.imageUrl).into(itemView.findViewById<ImageView>(R.id.item_image));
    }
}
