package fr.umontpellier.carhiboux.fragment.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.umontpellier.carhiboux.R
import java.time.LocalDateTime
import java.util.*

class FilterFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view : View = inflater.inflate(R.layout.search_filter_fragment, container, false)

        view.findViewById<EditText>(R.id.filter_year_max).setText(Calendar.getInstance().time.year.toString())

        view.findViewById<ImageButton>(R.id.filter_back).setOnClickListener {
            findNavController().navigate(R.id.from_filters_to_announcement_list)
        }

        return view
    }
}