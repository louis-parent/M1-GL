package coloring.graph;

import java.util.HashSet;
import java.util.Set;

public class Vertex
{
	private String name;
	private Set<String> edges;
	private int color;
	
	public Vertex(String name)
	{
		this.name = name;
		this.edges = new HashSet<String>();
		this.color = -1;
	}
	
	public Vertex(Vertex vertex)
	{
		this.name = vertex.getName();
		this.edges = new HashSet<String>();
		this.color = vertex.color;
		
		for(String edge : vertex.getEdges())
		{
			this.edges.add(edge);
		}
	}
	
	public void linkTo(Vertex vertex)
	{
		this.edges.add(vertex.getName());
	}
	
	public void unlinkTo(Vertex vertex)
	{
		this.edges.remove(vertex.getName());
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getDegree()
	{
		return this.getEdges().size();
	}
	
	public Set<String> getEdges()
	{
		return this.edges;
	}
	
	public void colorize(int color)
	{
		this.color = color;
	}
	
	public void spill()
	{
		this.color = Integer.MAX_VALUE;
	}
	
	public int getColor()
	{
		return this.color;
	}
	
	public boolean isColorized()
	{
		return this.color != -1;
	}
	
	public boolean isSpilled()
	{
		return this.color == Integer.MAX_VALUE;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Vertex)
		{
			return this.equals((Vertex) obj);
		}
		else
		{
			return false;
		}
	}
	
	public boolean equals(Vertex vertex)
	{
		return this.getName().equals(vertex.getName());
	}
}
