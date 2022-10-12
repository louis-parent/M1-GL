package tp2.client.ui;

import javax.swing.UIManager;

public class LookAndFeel
{
	public static void setBestLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e1)
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
			catch(Exception e2)
			{
			}
		}
	}
}
