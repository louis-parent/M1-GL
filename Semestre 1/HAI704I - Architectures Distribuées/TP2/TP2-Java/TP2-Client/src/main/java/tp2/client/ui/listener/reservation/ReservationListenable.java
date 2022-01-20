package tp2.client.ui.listener.reservation;

import java.util.Collection;

public interface ReservationListenable
{
	public abstract Collection<ReservationListener> getReservationListeners();
	
	public default void addReservationListener(ReservationListener listener)
	{
		this.getReservationListeners().add(listener);
	}
	
	public default void removeReservationListener(ReservationListener listener)
	{
		this.getReservationListeners().remove(listener);
	}
	
	public default void notifyReservation()
	{
		for(ReservationListener listener : this.getReservationListeners())
		{
			listener.reservationDone();
		}
	}
}
