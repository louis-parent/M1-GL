package fr.umontpellier.carhiboux.bundle

import android.os.Parcelable
import fr.umontpellier.carhiboux.entity.enumeration.AnnouncementType
import fr.umontpellier.carhiboux.entity.enumeration.Energy
import fr.umontpellier.carhiboux.entity.enumeration.Gearbox
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class SearchFilters(
    val search: String? = null,
    val type: AnnouncementType = AnnouncementType.ANY,
    val brand: String? = null,
    val model: String? = null,
    val maxPrice: Double = Double.MAX_VALUE,
    val minPrice: Double = Double.MAX_VALUE,
    val maxKilometers: Double = Double.MAX_VALUE,
    val minYear: Int = 1971,
    val maxYear: Int = Calendar.getInstance().get(Calendar.YEAR),
    val technicalControlRequired: Boolean = false,
    val energy: Energy = Energy.ANY,
    val gearbox: Gearbox = Gearbox.ANY,
    val minPlaces: Int = 1
) : Parcelable
{
    companion object
    {
        fun searchWithFilters(search: String?, filters: SearchFilters) : SearchFilters
        {
            return SearchFilters(
                search,
                filters.type,
                filters.brand,
                filters.model,
                filters.maxPrice,
                filters.minPrice,
                filters.maxKilometers,
                filters.minYear,
                filters.maxYear,
                filters.technicalControlRequired,
                filters.energy,
                filters.gearbox,
                filters.minPlaces
            )
        }
    }
}