package fr.umontpellier.carhiboux.fragment.filter

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.umontpellier.carhiboux.R
import fr.umontpellier.carhiboux.bundle.SearchFilters
import fr.umontpellier.carhiboux.entity.enumeration.AnnouncementType
import fr.umontpellier.carhiboux.entity.enumeration.Energy
import fr.umontpellier.carhiboux.entity.enumeration.Gearbox
import java.util.*

class FilterFragment : Fragment()
{
    private val FILTERS_KEY = "filters"
    private var defaultFilters: SearchFilters? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        defaultFilters = arguments?.get(FILTERS_KEY) as SearchFilters?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view : View = inflater.inflate(R.layout.search_filter_fragment, container, false)
        initListeners(view)

        view.findViewById<EditText>(R.id.filter_year_max).setText(Calendar.getInstance().get(Calendar.YEAR).toString())
        initSeekBar(view, R.id.filter_min_price, 0, SearchFilters.PRICE_MAX_VALUE)
        initSeekBar(view, R.id.filter_max_price, 0, SearchFilters.PRICE_MAX_VALUE, SearchFilters.PRICE_MAX_VALUE)
        initSeekBar(view, R.id.filter_max_kilometer, 0, SearchFilters.KILOMETERS_MAX_VALUE, SearchFilters.KILOMETERS_MAX_VALUE)

        initAnnouncementTypes(view)
        initEnergies(view)
        initGearboxes(view)
        initDefaultFilters(view)

        return view
    }

    private fun initSeekBar(view: View, id : Int, min: Int, max: Int, defaultProgress: Int = 0)
    {
        val bar : SeekBar = view.findViewById(id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bar.min = min
        }
        bar.max = max
        bar.progress = defaultProgress
    }

    private fun initAnnouncementTypes(view: View)
    {
        val group : RadioGroup = view.findViewById(R.id.filter_type)

        for (type in AnnouncementType.values())
        {
            val button = RadioButton(context)
            button.text = view.context.getText(type.string)
            button.id = type.view
            group.addView(button)
        }

        group.check(AnnouncementType.ANY.view)
    }

    private fun initEnergies(view: View)
    {
        val group : RadioGroup = view.findViewById(R.id.filter_energy)

        for (type in Energy.values())
        {
            val button = RadioButton(context)
            button.text = view.context.getText(type.string)
            button.id = type.view
            group.addView(button)
        }

        group.check(Energy.ANY.view)
    }
    private fun initGearboxes(view: View)
    {
        val group : RadioGroup = view.findViewById(R.id.filter_gearbox)

        for (type in Gearbox.values())
        {
            val button = RadioButton(context)
            button.text = view.context.getText(type.string)
            button.id = type.view
            group.addView(button)
        }

        group.check(Gearbox.ANY.view)
    }

    private fun initListeners(view: View)
    {
        view.findViewById<ImageView>(R.id.filter_back).setOnClickListener {
            findNavController().navigate(R.id.from_filters_to_announcement_list, bundleOf(FILTERS_KEY to defaultFilters))
        }

        view.findViewById<ImageButton>(R.id.filter_search_button).setOnClickListener {
            findNavController().navigate(R.id.from_filters_to_announcement_list, bundleOf(FILTERS_KEY to getFilters()))
        }

        view.findViewById<ImageButton>(R.id.filter_search_cancel).setOnClickListener {
            findNavController().navigate(R.id.from_filters_to_announcement_list)
        }

        view.findViewById<SeekBar>(R.id.filter_min_price).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar?, progress: Int, byUser: Boolean)
            {
                view.findViewById<TextView>(R.id.filter_min_price_preview).text = "" + progress + "€"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        view.findViewById<SeekBar>(R.id.filter_max_price).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar?, progress: Int, byUser: Boolean)
            {
                view.findViewById<TextView>(R.id.filter_max_price_preview).text = "" + progress + "€"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        view.findViewById<SeekBar>(R.id.filter_max_kilometer).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar?, progress: Int, byUser: Boolean)
            {
                view.findViewById<TextView>(R.id.filter_max_kilometer_preview).text = "" + progress + "km"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun getFilters() : SearchFilters
    {
        return SearchFilters(
            requireView().findViewById<EditText>(R.id.filter_search).text.toString(),
            AnnouncementType.forView(requireView().findViewById<RadioGroup>(R.id.filter_type).checkedRadioButtonId),
            requireView().findViewById<EditText>(R.id.filter_brand).text.toString(),
            requireView().findViewById<EditText>(R.id.filter_model).text.toString(),
            getSeekBarValue(R.id.filter_max_price),
            getSeekBarValue(R.id.filter_min_price),
            getSeekBarValue(R.id.filter_max_kilometer),
            requireView().findViewById<EditText>(R.id.filter_year_min).text.toString().toInt(),
            requireView().findViewById<EditText>(R.id.filter_year_max).text.toString().toInt(),
            requireView().findViewById<CheckBox>(R.id.filter_technical).isChecked,
            Energy.forView(requireView().findViewById<RadioGroup>(R.id.filter_energy).checkedRadioButtonId),
            Gearbox.forView(requireView().findViewById<RadioGroup>(R.id.filter_gearbox).checkedRadioButtonId),
            requireView().findViewById<EditText>(R.id.filter_places).text.toString().toInt()
        )
    }

    private fun getSeekBarValue(id : Int, view : View = requireView()) : Double
    {
        val bar : SeekBar = view.findViewById(id)
        return ((bar.progress.toDouble() / bar.max.toDouble()) * bar.width.toDouble())
    }

    private fun setSeekBarValue(id : Int, value : Double, view : View = requireView())
    {
        val bar : SeekBar = view.findViewById(id)
        bar.progress = ((value / bar.width.toDouble()) * bar.max.toDouble()).toInt()
    }

    private fun initDefaultFilters(view: View)
    {
        if(defaultFilters != null)
        {
            view.findViewById<EditText>(R.id.filter_search).setText(defaultFilters!!.search)
            view.findViewById<RadioGroup>(R.id.filter_type).check(defaultFilters!!.type.view)
            view.findViewById<EditText>(R.id.filter_brand).setText(defaultFilters!!.brand)
            view.findViewById<EditText>(R.id.filter_model).setText(defaultFilters!!.model)
            setSeekBarValue(R.id.filter_max_price, defaultFilters!!.maxPrice, view)
            setSeekBarValue(R.id.filter_min_price, defaultFilters!!.minPrice, view)
            setSeekBarValue(R.id.filter_max_kilometer, defaultFilters!!.maxKilometers, view)
            view.findViewById<EditText>(R.id.filter_year_min).setText(defaultFilters!!.minYear.toString())
            view.findViewById<EditText>(R.id.filter_year_max).setText(defaultFilters!!.maxYear.toString())
            view.findViewById<CheckBox>(R.id.filter_technical).isChecked = defaultFilters!!.technicalControlRequired
            view.findViewById<RadioGroup>(R.id.filter_energy).check(defaultFilters!!.energy.view)
            view.findViewById<RadioGroup>(R.id.filter_gearbox).check(defaultFilters!!.gearbox.view)
            view.findViewById<EditText>(R.id.filter_places).setText(defaultFilters!!.minPlaces.toString())
        }
    }
}