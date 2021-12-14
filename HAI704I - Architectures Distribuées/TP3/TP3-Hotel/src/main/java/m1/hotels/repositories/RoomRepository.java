package m1.hotels.repositories;

import java.util.Collection;
import java.util.HashSet;

import m1.hotels.model.room.Room;
import m1.hotels.model.room.bed.Bed;
import m1.hotels.model.room.bed.DoubleBed;
import m1.hotels.model.room.bed.SimpleBed;

public class RoomRepository
{
	private static Collection<Room> repository = new HashSet<Room>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Room(66, "src/main/resources/cheap.jpg", new Bed[] { new SimpleBed() }));
			add(new Room(106, "src/main/resources/family.jpg", new Bed[] { new DoubleBed(), new SimpleBed(), new SimpleBed() }));
			add(new Room(666, "src/main/resources/vip.jpg", new Bed[] { new DoubleBed() }));
		}
	};
	
	public static Collection<Room> readAll()
	{
		return repository;
	}
}
