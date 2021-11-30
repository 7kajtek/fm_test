package pl.kylos.marczuk.pm_fm_test.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import pl.kylos.marczuk.pm_fm_test.R

class DetailsActivity : AppCompatActivity() {

    private val webView by lazy { findViewById<WebView>(R.id.web_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val url = intent.extras?.getString(EXTRA_URL)
        if (url == null) {
            finish()
            return
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_details, DetailsFragment.newInstance(url))
            .commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val EXTRA_URL = "extra_url"

        fun getIntent(context: Context, url: String) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
    }
}