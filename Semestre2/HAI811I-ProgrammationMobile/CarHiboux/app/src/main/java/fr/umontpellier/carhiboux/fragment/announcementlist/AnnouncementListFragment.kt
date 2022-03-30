package fr.umontpellier.carhiboux.fragment.announcementlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.umontpellier.carhiboux.R

class AnnouncementListFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view : View = inflater.inflate(R.layout.announcement_list_fragment, container, false)
        view.findViewById<ListView>(R.id.announcement_list).adapter = AnnouncementListAdapter()

        view.findViewById<ImageButton>(R.id.announcement_list_search_filter).setOnClickListener {
            findNavController().navigate(R.id.from_announcement_list_to_filters)
        }

        return view
    }
}