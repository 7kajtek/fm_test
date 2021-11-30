package pl.kylos.marczuk.pm_fm_test.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import pl.kylos.marczuk.pm_fm_test.R

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val url = it.getString(EXTRA_URL) ?: ""
            view.findViewById<WebView>(R.id.web_view).loadUrl(url)
        }

    }

    companion object {
        const val EXTRA_URL = "extra_url"

        fun newInstance(url: String) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_URL, url)
            }
        }
    }
}