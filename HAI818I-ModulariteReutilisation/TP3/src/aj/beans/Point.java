package aj.beans;

class Point
{

	protected int x = 0;
	protected int y = 0;

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setRectangular(int newX, int newY)
	{
		setX(newX);
		setY(newY);
	}

	public void setX(int newX)
	{
		x = newX;
	}

	public void setY(int newY)
	{
		y = newY;
	}

	public void offset(int deltaX, int deltaY)
	{
		setRectangular(x + deltaX, y + deltaY);
	}

	public String toString()
	{
		return "(" + getX() + ", " + getY() + ")";
	}
}