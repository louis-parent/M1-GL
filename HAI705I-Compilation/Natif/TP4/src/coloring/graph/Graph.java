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

	public Graph(Graph graph)
	{
		this();
		
		for(Vertex vertex : graph.getVertices())
		{
			this.vertices.add(new Vertex(vertex));
		}
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
	
	public Set<Vertex> getVertices()
	{
		return this.vertices;
	}

	public Vertex find(Vertex lookingFor)
	{
		return this.find(lookingFor.getName());
	}
	
	public Vertex find(String name)
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
	
	public Graph remove(Vertex toRemove)
	{
		Graph g = new Graph();
		
		for(Vertex vertex : this.vertices)
		{
			if(!vertex.equals(toRemove))
			{
				Vertex clone = new Vertex(vertex);
				clone.unlinkTo(toRemove);
				g.add(clone);
			}
		}
		
		return g;
	}
	
	@Override
	public String toString()
	{
		String str = "G = ({";

		str += this.vertices.stream().map(vertex -> {
			String vStr = vertex.getName();
			
			if(vertex.isSpilled())
			{
				vStr += "[Spill]";
			}
			else if(vertex.isColorized())
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
}
