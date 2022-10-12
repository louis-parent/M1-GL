package tp2.client.ui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import tp2.client.ui.listener.filter.OfferFilterListenable;
import tp2.client.ui.listener.filter.OfferFilterListener;

public class FilterPanel extends JPanel implements OfferFilterListenable
{
	private static final long serialVersionUID = -7936108068858778912L;

	private Collection<OfferFilterListener> listeners;

	private JSpinner starSpinner;
	private JSlider priceSlider;

	private JLabel minLabel;

	private JLabel maxLabel;
	
	public FilterPanel()
	{
		this.listeners = new ArrayList<OfferFilterListener>();
		
		this.build();
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));		
	}

	@Override
	public Collection<OfferFilterListener> getOfferFilterListeners()
	{
		return this.listeners;
	}
	
	public void setMinPrice(float min)
	{
		this.minLabel.setText("Min. " + Math.floor(min));
		this.priceSlider.setMinimum((int) min);
	}
	
	public void setMaxPrice(float max)
	{
		this.maxLabel.setText("Max. " + Math.ceil(max));
		this.priceSlider.setMaximum((int) max);
	}
	
	private void build()
	{
		Box box = Box.createVerticalBox(); 
		
		box.add(new JLabel("Etoiles Minimales :"));
		
		starSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		starSpinner.addChangeListener(event -> {
			this.filterChange();
		});
		box.add(starSpinner);
		
		box.add(new JLabel("Prix Maximum :"));
		
		Box priceBox = Box.createHorizontalBox();
		
		minLabel = new JLabel("Min.");
		priceBox.add(minLabel);
		
		priceBox.add(Box.createRigidArea(new Dimension(5, 0)));
		
		priceSlider = new JSlider(0, 0, 0);
		priceSlider.addChangeListener(event -> {
			this.filterChange();
		});
		priceBox.add(priceSlider);
		
		priceBox.add(Box.createRigidArea(new Dimension(5, 0)));
		
		maxLabel = new JLabel("Max.");
		priceBox.add(maxLabel);
		
		box.add(priceBox);
		this.add(box);
	}

	private void filterChange()
	{
		int stars = Integer.parseInt(this.starSpinner.getValue().toString());
		float maxPrice = this.priceSlider.getValue();
		
		this.notifyOfferFilter(stars, maxPrice);
	}
}
