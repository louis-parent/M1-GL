package coloring.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph
{
	private Set<Vertex> vertices;

	public Graph()
	{
		this.vertices = new HashSet<Vertex>();
	}

	public void add(Vertex vertex)
	{
		this.vertices.add(vertex);
	}

	public void linkVertices(Vertex vertex1, Vertex vertex2)
	{
		vertex1.linkTo(vertex2);
		vertex2.linkTo(vertex1);
	}

	public void linkVertices(Vertex vertex1, Collection<Vertex> vertices)
	{
		for(Vertex vertex2 : vertices)
		{
			this.linkVertices(vertex1, vertex2);
		}
	}

	public void colorize(int colorsAmount)
	{
		if(this.existsTriviallyColorableVertex(colorsAmount))
		{
			Vertex vertex = this.getTriviallyCollarableVertex(colorsAmount);
			Graph g = this.remove(vertex);

			g.colorize(colorsAmount);
			this.copyColors(g);

			this.colorizeVertext(vertex, colorsAmount);
		}
		else if(this.existsNotColorizedVertex())
		{
			Vertex vertex = this.getNotColorizedVertex();
			Graph g = this.remove(vertex);

			g.colorize(colorsAmount);
			this.copyColors(g);

			vertex.spill();
		}
	}

	@Override
	public String toString()
	{
		String str = "G = ({";

		str += this.vertices.stream().map(vertex -> {
			String vStr = vertex.getName();
			
			if(vertex.isColorized())
			{
				vStr += "[" + vertex.getColor() + "]";
			}
			
			return vStr;
		}).collect(Collectors.joining(", "));

		str += "}, {";

		for(Vertex vertex : this.vertices)
		{
			for(String edge : vertex.getEdges())
			{
				str += "(" + vertex.getName() + ", " + edge + "), ";
			}
		}

		return str + "}";
	}

	private boolean existsTriviallyColorableVertex(int colorsAmount)
	{
		return this.getTriviallyCollarableVertex(colorsAmount) != null;
	}

	private Vertex getTriviallyCollarableVertex(int colorsAmount)
	{
		for(Vertex vertex : this.vertices)
		{
			if(vertex.getEdgesSize() < colorsAmount)
			{
				return vertex;
			}
		}

		return null;
	}

	private void copyColors(Graph g)
	{
		for(Vertex vertex : g.vertices)
		{
			Vertex thisVertex = this.find(vertex);
			thisVertex.colorize(vertex.getColor());
		}
	}

	private Vertex find(Vertex lookingFor)
	{
		return this.find(lookingFor.getName());
	}
	
	private Vertex find(String name)
	{
		for(Vertex vertex : this.vertices)
		{
			if(vertex.getName().equals(name))
			{
				return vertex;
			}
		}

		return null;
	}

	private void colorizeVertext(Vertex vertex, int colorsAmount)
	{
		Set<Integer> dejaVu = new HashSet<Integer>();

		for(String edge : vertex.getEdges())
		{
			dejaVu.add(this.find(edge).getColor());
		}

		int color = 0;
		while(color < colorsAmount && !vertex.isColorized())
		{
			if(!dejaVu.contains(color))
			{
				vertex.colorize(color);
			}
			else
			{
				color++;
			}
		}

		if(!vertex.isColorized())
		{
			throw new Error("No avalaible color for " + vertex.getName());
		}
	}

	private boolean existsNotColorizedVertex()
	{
		return this.getNotColorizedVertex() != null;
	}
	
	private Vertex getNotColorizedVertex()
	{
		for(Vertex vertex : this.vertices)
		{
			if(!vertex.isColorized())
			{
				return vertex;
			}
		}
		
		return null;
	}
	
	private Graph remove(Vertex toRemove)
	{
		Graph g = new Graph();
		
		for(Vertex vertex : this.vertices)
		{
			if(!vertex.equals(toRemove))
			{
				try
				{
					Vertex clone = (Vertex) vertex.clone();
					clone.unlinkTo(toRemove);
					g.add(clone);
				}
				catch(CloneNotSupportedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return g;
	}
}
