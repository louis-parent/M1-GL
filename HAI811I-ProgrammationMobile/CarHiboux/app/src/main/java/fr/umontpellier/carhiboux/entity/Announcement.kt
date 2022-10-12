package fr.umontpellier.carhiboux.entity

import fr.umontpellier.carhiboux.entity.enumeration.AnnouncementType
import fr.umontpellier.carhiboux.entity.enumeration.Energy
import fr.umontpellier.carhiboux.entity.enumeration.Gearbox

class Announcement(
    val id: Long,
    val type: AnnouncementType,
    val brand: String,
    val model: String,
    val price: Double,
    val address: Address,
    val kilometers: Int,
    val year: Int,
    val technicalControl: Boolean,
    val energy: Energy,
    val gearbox: Gearbox,
    val exteriorColor: String,
    val interiorColor: String,
    val doors: Int,
    val places: Int,
    val formerOwnerCount: Int,
    val power: Int,
    val din: Int,
    val co2: Double,
    val consumption: Double,
    val image: Array<Byte>
)
{


}