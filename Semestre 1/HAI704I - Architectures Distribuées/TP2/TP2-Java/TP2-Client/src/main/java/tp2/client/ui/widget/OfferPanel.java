package tp2.client.ui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import tp2.client.CreditCard;
import tp2.client.Customer;
import tp2.client.Offer;
import tp2.client.model.Hotel;
import tp2.client.ui.listener.reservation.ReservationListenable;
import tp2.client.ui.listener.reservation.ReservationListener;

public class OfferPanel extends JPanel implements ReservationListenable
{
	private static final long serialVersionUID = 5046947692947433092L;

	private Collection<ReservationListener> listeners;
	
	private Hotel hotel;
	private Offer offer;
	
	public OfferPanel(Hotel hotel, Offer offer)
	{
		this.listeners = new ArrayList<ReservationListener>();
		
		this.hotel = hotel;
		this.offer = offer;
		
		this.build();
	}
	
	@Override
	public Collection<ReservationListener> getReservationListeners()
	{
		return this.listeners;
	}

	private void build()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		this.add(new JLabel(new ImageIcon(this.createRGBImage(this.offer.getImage()))));
		
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		
		Box box = Box.createVerticalBox();
		box.add(new JLabel("<html><big><b>" + this.hotel.getName() + "</b></big></html>"));
		box.add(new JLabel("<html><i>" + this.hotel.getAddress().toString() + "</i></html>"));
		box.add(new JLabel("<html><b>" + offer.getPrice() + "€</b>/nuit - " + offer.getMaxPersonAmount() + " personnes, <i>Date première disponibilité : " + XMLDateToString() + "</i></html>"));
		this.add(box);
		
		JButton reservationButton = new JButton("Réserver");
		reservationButton.addActionListener(event -> {
			this.reserve();
		});
		this.add(reservationButton);
		
		this.add(Box.createRigidArea(new Dimension(10, 0)));
	}
	
	private BufferedImage createRGBImage(byte[] bytes)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	    try
		{
			return ImageIO.read(bis);
		}
		catch(IOException e)
		{
			return new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		}
	}

	private String XMLDateToString()
	{
		return offer.getAvailabiltyDate().getDay() + "/" + offer.getAvailabiltyDate().getMonth() + "/" + offer.getAvailabiltyDate().getYear();
	}
	
	private void reserve()
	{
		Customer customer = this.askForCustomer();
		
		if(customer != null)
		{
			boolean reservationDone = this.hotel.getService().makeReservation(this.hotel.getLogin(), this.hotel.getPassword(), this.offer.getId(), customer);
			
			if(reservationDone)
			{
				this.notifyReservation();
				JOptionPane.showMessageDialog(this, "La réservation a été effectuée", "Réservation", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "La réservation a échoué, veuillez réessayer ultérieurement...", "Réservation", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private Customer askForCustomer()
	{
		JTextField firstName = new JTextField();
		JTextField lastName = new JTextField();
		JTextField cbNumber = new JFormattedTextField(this.createCardNumberMask());
		JTextField cbExpirationDate = new JFormattedTextField(new SimpleDateFormat("MM/yy"));
		JTextField cbCVV = new JFormattedTextField(this.createCVVMask());
		
		Box fields = Box.createVerticalBox();
		this.addField(fields, "Prénom", firstName);
		this.addField(fields, "Nom", lastName);
		this.addField(fields, "Numéro Carte Bancaire", cbNumber);
		this.addField(fields, "Expiration Carte Bancaire", cbExpirationDate);
		this.addField(fields, "CVV Carte Bancaire", cbCVV);
		
		int result = JOptionPane.showConfirmDialog(this, fields, "Saisie Client", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			Customer customer = new Customer();
			customer.setFirstName(firstName.getText());
			customer.setLastName(lastName.getText());
			
			CreditCard creditCard = new CreditCard();
			creditCard.setNumber(cbNumber.getText());
			creditCard.setExpirationDate(cbExpirationDate.getText());
			creditCard.setCVV(cbCVV.getText());
			customer.setCard(creditCard);
			
			return customer;
		}
		else
		{			
			return null;
		}	
	}
	
	private void addField(JComponent container, String label, JComponent field)
	{
		container.add(new JLabel(label + " :"));
		container.add(field);
		container.add(Box.createRigidArea(new Dimension(0, 10)));
	}
	
	private MaskFormatter createCardNumberMask()
	{
		MaskFormatter mask = null;
		
        try
        {
            mask = new MaskFormatter("#### #### #### ####");
            mask.setPlaceholderCharacter('_');
        }
        catch (ParseException e)
        {
        }
        
        return mask;
	}
	
	private MaskFormatter createCVVMask()
	{
		MaskFormatter mask = null;
		
        try
        {
            mask = new MaskFormatter("###");
            mask.setPlaceholderCharacter('_');
        }
        catch (ParseException e)
        {
        }
        
        return mask;
	}
}
