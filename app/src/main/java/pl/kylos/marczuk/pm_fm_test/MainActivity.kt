package pl.kylos.marczuk.pm_fm_test

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kylos.marczuk.pm_fm_test.repository.Repository

class MainActivity : AppCompatActivity() {

    private var adapter = DataListAdapter()
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        val isEmptyViewModel = viewModel.isDbEmpty()
        isEmptyViewModel.observe(this, object : Observer<Boolean> {
            override fun onChanged(it: Boolean) {
                if (it) {
                    Log.d("PM", "Database empty, load data")
                    viewModel.refreshData()
                } else {
                    Log.d("PM", "Database is not empty, skip loading data")
                }
                isEmptyViewModel.removeObserver(this)
            }
        })

        viewModel.getList().observe(this) {
            Log.d("PM", "Load data ${it.size}")
            adapter.submitList(it)
        }

        viewModel.getErrorLiveData().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh -> {
                Log.d("PM", "Refresh data")
                viewModel.refreshData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(getApplication())
    fun getList() = repository.getList()
    fun refreshData() = repository.syncList()
    fun isDbEmpty() = repository.isDbEmpty()
    fun getErrorLiveData() = repository.getErrorLiveData()
}
