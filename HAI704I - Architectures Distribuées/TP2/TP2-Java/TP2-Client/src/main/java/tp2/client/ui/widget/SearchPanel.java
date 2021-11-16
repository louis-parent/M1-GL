package tp2.client.ui.widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import tp2.client.ui.listener.search.HotelSearchListenable;
import tp2.client.ui.listener.search.HotelSearchListener;

public class SearchPanel extends JPanel implements HotelSearchListenable
{
	private static final long serialVersionUID = 1864412905269264406L;

	private Collection<HotelSearchListener> listeners;

	private JComboBox<String> cityBox;
	private JFormattedTextField arrivalField;
	private JFormattedTextField departureField;
	private JSpinner personSpinner;
	
	private DateFormat dateFormat;

	public SearchPanel(Collection<String> cities)
	{
		this.listeners = new HashSet<HotelSearchListener>();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		build(cities);
	}
	
	public void fetchSearch()
	{
		this.search();
	}

	@Override
	public Collection<HotelSearchListener> getHotelSearchListeners()
	{
		return this.listeners;
	}

	private void build(Collection<String> cities)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		this.cityBox = new JComboBox<String>(cities.toArray(new String[0]));
		this.add(this.createLabeledField("Ville", this.cityBox));

		this.arrivalField = new JFormattedTextField(this.dateFormat);
		this.arrivalField.setValue(new Date());
		this.add(this.createLabeledField("Arrivée", this.arrivalField));

		this.departureField = new JFormattedTextField(this.dateFormat);
		this.departureField.setValue(this.tomorrow());
		this.add(this.createLabeledField("Départ", this.departureField));

		this.personSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		this.add(this.createLabeledField("Nombre de Personnes", this.personSpinner));

		JButton search = new JButton("Rechercher");
		search.addActionListener(event -> {
			this.search();
		});
		this.add(this.createLabeledField("", search));

		this.add(Box.createRigidArea(new Dimension(10, 0)));
	}
	
	private Box createLabeledField(String name, JComponent component)
	{
		Box box = Box.createVerticalBox();

		String labelText = " ";
		if(!name.isBlank())
		{
			labelText = name + " :";
		}

		JLabel label = new JLabel(labelText);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(label);

		box.add(component);
		box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return box;
	}
	
	private Date tomorrow()
	{
		Date dt = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		
		return c.getTime();
	}
	
	private void search()
	{
		String city = this.cityBox.getSelectedItem().toString();

		Date arrival;
		Date departure;
		
		try
		{
			arrival = this.dateFormat.parse(this.arrivalField.getText());
			departure = this.dateFormat.parse(this.departureField.getText());
		}
		catch(ParseException e)
		{
			arrival = new Date();
			departure = new Date();
		}
		
		int persons = Integer.parseInt(this.personSpinner.getValue().toString());
		
		this.notifyHotelSearch(city, arrival, departure, persons);
	}
}
