package pl.kylos.marczuk.pm_fm_test

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.kylos.marczuk.pm_fm_test.repository.Data

class DataListAdapter :
    ListAdapter<Data, DataViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(parent)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Data> = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldUser: Data, newUser: Data): Boolean {
                return oldUser.id === newUser.id
            }

            override fun areContentsTheSame(oldUser: Data, newUser: Data): Boolean {
                return oldUser == newUser
            }
        }
    }
}
