package m1.hotels.model.offer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import m1.hotels.model.room.Room;

public class Offer
    {
		private static String ID_SEPARATOR = "§";
		private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		
		private String id;
        private int maxPersonAmount;
        private Date availabiltyDate;
        private float price;
        private byte[] image;

        public Offer()
        { 
        	this(0, new Date(), 0, new Date(), new Date(), null);
        }
        
        public Offer(int maxPersonAmount, Date availabiltyDate, float price, Date start, Date end, Room room)
        {
            this.id = this.computeId(start, end, room);
            this.maxPersonAmount = maxPersonAmount;
            this.availabiltyDate = availabiltyDate;
            this.price = price;
            
            try
			{
				this.image = this.toByteArray(room.getPhoto());
			}
			catch(IOException e)
			{
				this.image = null;
			}
        }

		public String getId()
		{
			return id;
		}

		public void setId(String id)
		{
			this.id = id;
		}

		public int getMaxPersonAmount()
		{
			return maxPersonAmount;
		}

		public void setMaxPersonAmount(int maxPersonAmount)
		{
			this.maxPersonAmount = maxPersonAmount;
		}

		public Date getAvailabiltyDate()
		{
			return availabiltyDate;
		}

		public void setAvailabiltyDate(Date availabiltyDate)
		{
			this.availabiltyDate = availabiltyDate;
		}

		public float getPrice()
		{
			return price;
		}

		public void setPrice(float price)
		{
			this.price = price;
		}

		public byte[] getImage()
		{
			return image;
		}

		public void setImage(byte[] image)
		{
			this.image = image;
		}
        
		private String computeId(Date start, Date end, Room room)
		{
			String id = "";
			
			id += Offer.DATE_FORMAT.format(start);
			id += ID_SEPARATOR;
			id += Offer.DATE_FORMAT.format(end);
			id += ID_SEPARATOR;
			id += room.getId();
			
			return id;
		}
		
		private byte[] toByteArray(BufferedImage image) throws IOException
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", bos);
			return bos.toByteArray();
		}

		public static Date getStartForId(String offer)
		{
			try
			{
				return Offer.DATE_FORMAT.parse(offer.split(ID_SEPARATOR)[0]);
			}
			catch(ParseException e)
			{
				return null;
			}
		}

		public static Date getEndForId(String offer)
		{
			try
			{
				return Offer.DATE_FORMAT.parse(offer.split(ID_SEPARATOR)[1]);
			}
			catch(ParseException e)
			{
				return null;
			}
		}

		public static int getRoomForId(String offer)
		{
			return Integer.parseInt(offer.split(ID_SEPARATOR)[2]);
		}
    }
