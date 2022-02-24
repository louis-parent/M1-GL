package fr.igpolytech.mtp.oodp1;

import org.junit.Assert;
import org.junit.Test;

public class CafeTest {
	@Test
	public void canBrewEspresso() {
		Cafe cafe = new Cafe();
		cafe.restockBeans(7);
		Coffee coffee = cafe.brew(CoffeeType.Espresso);
		// It is an Espresso
		Assert.assertEquals(CoffeeType.Espresso,coffee.getType());
		// No milk		
		Assert.assertEquals("Wrong quantity of Milk",0,coffee.getMilk());
		// We've got enough coffee
		Assert.assertEquals(7,coffee.getBeans());		
	}
	
		
}
