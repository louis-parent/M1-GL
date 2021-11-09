package tp2.server.model.offer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import tp2.server.model.room.Room;

public class Offer
    {
		private int id;
        private int maxPersonAmount;
        private Date availabiltyDate;
        private float price;
        private byte[] image;

        private Room room;

        private Date start;
        private Date end;

        public Offer()
        { 
        	this(0, 0, new Date(), 0, new Date(), new Date(), null);
        }
        
        public Offer(int id, int maxPersonAmount, Date availabiltyDate, float price, Date start, Date end, Room room)
        {
            this.id = id;
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
            
            this.room = room;
            this.start = start;
            this.end = end;
        }
        
        

		public int getId()
		{
			return id;
		}

		public void setId(int id)
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

		public Room getOfferedRoom()
		{
			return room;
		}

		public Date getStart()
		{
			return start;
		}

		public Date getEnd()
		{
			return end;
		}
		
		private byte[] toByteArray(Image image) throws IOException
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(this.toBufferedImage(image), "png", bos);
			return bos.toByteArray();
		}
		
		private BufferedImage toBufferedImage(Image img)
		{
		    if (img instanceof BufferedImage)
		    {
		        return (BufferedImage) img;
		    }

		    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();

		    return bimage;
		}
    }
