package fr.umontpellier.carhiboux.fragment.announcementlist

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import fr.umontpellier.carhiboux.R
import fr.umontpellier.carhiboux.bundle.SearchFilters

class AnnouncementListFragment : Fragment()
{
    private val FILTERS_KEY = "filters"
    private var filters: SearchFilters? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        filters = arguments?.get(FILTERS_KEY) as SearchFilters?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view : View = inflater.inflate(R.layout.announcement_list_fragment, container, false)
        view.findViewById<ListView>(R.id.announcement_list).adapter = AnnouncementListAdapter()
        
        initMark(view)
        initListeners(view)

        return view
    }

    private fun initListeners(view: View)
    {
        view.findViewById<ImageButton>(R.id.announcement_list_search_filter).setOnClickListener {
            findNavController().navigate(R.id.from_announcement_list_to_filters, bundleOf(FILTERS_KEY to filters))
        }

        view.findViewById<ImageButton>(R.id.announcement_list_search_button).setOnClickListener {
            val search : String = view.findViewById<EditText>(R.id.announcement_list_search).text.toString()

            val newFilters : SearchFilters = if(filters != null) {
                SearchFilters.searchWithFilters(search, filters!!)
            } else {
                SearchFilters(search)
            }


            findNavController().navigate(R.id.from_announcement_list_to_filters, bundleOf(FILTERS_KEY to newFilters))
        }
    }

    private fun initMark(view: View)
    {
        val mark : View = view.findViewById<ImageView>(R.id.announcement_list_mark)
        if(filters != null)
        {
            mark.visibility = View.VISIBLE
        }
        else
        {
            mark.visibility = View.INVISIBLE
        }
    }
}
