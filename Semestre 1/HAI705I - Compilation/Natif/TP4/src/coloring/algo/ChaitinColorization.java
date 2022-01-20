package coloring.algo;

import java.util.HashSet;
import java.util.Set;

import coloring.graph.Graph;
import coloring.graph.Vertex;

public class ChaitinColorization
{
	private Graph graph;
	
	public ChaitinColorization(Graph graph)
	{
		this.graph = new Graph(graph);
	}
	
	public Graph colorize(int colorsAmount)
	{
		if(this.existsTriviallyColorableVertex(colorsAmount))
		{
			Vertex vertex = this.getTriviallyCollarableVertex(colorsAmount);
			Graph g = this.graph.remove(vertex);
			
			this.copyColors(this.doRecursion(g, colorsAmount));

			this.colorizeVertex(vertex, colorsAmount);
		}
		else if(this.existsNotColorizedVertex())
		{
			Vertex vertex = this.getNotColorizedVertex();
			Graph g = this.graph.remove(vertex);

			this.copyColors(this.doRecursion(g, colorsAmount));

			vertex.spill();
		}
		
		return this.graph;
	}
	
	public Graph optimisticColorize(int colorsAmount)
	{
		if(this.existsTriviallyColorableVertex(colorsAmount))
		{
			Vertex vertex = this.getTriviallyCollarableVertex(colorsAmount);
			Graph g = this.graph.remove(vertex);

			this.copyColors(this.doRecursion(g, colorsAmount));

			this.colorizeVertex(vertex, colorsAmount);
		}
		else if(this.existsNotColorizedVertex())
		{
			Vertex vertex = this.getNotColorizedVertex();
			Graph g = this.graph.remove(vertex);

			this.copyColors(this.doRecursion(g, colorsAmount));

			this.optimisticSpill(vertex, colorsAmount);
		}
		
		return this.graph;
	}
	
	public Graph getGraph()
	{
		return this.graph;
	}
	
	private Graph doRecursion(Graph g, int colorsAmount)
	{
		ChaitinColorization recursion = new ChaitinColorization(g);
		return recursion.optimisticColorize(colorsAmount);
	}
	
	private boolean existsTriviallyColorableVertex(int colorsAmount)
	{
		return this.getTriviallyCollarableVertex(colorsAmount) != null;
	}

	private Vertex getTriviallyCollarableVertex(int colorsAmount)
	{
		for(Vertex vertex : this.graph.getVertices())
		{
			if(vertex.getDegree() < colorsAmount)
			{
				return vertex;
			}
		}

		return null;
	}

	private void copyColors(Graph g)
	{
		for(Vertex vertex : g.getVertices())
		{
			Vertex thisVertex = this.graph.find(vertex);
			thisVertex.colorize(vertex.getColor());
		}
	}
	
	private void colorizeVertex(Vertex vertex, int colorsAmount)
	{
		int color = this.getMinimalColor(vertex, colorsAmount);
		
		if(color != -1)
		{
			vertex.colorize(color);
		}
		else
		{
			throw new Error("No avalaible color for " + vertex.getName());
		}
	}

	private int getMinimalColor(Vertex vertex, int colorsAmount)
	{
		Set<Integer> dejaVu = new HashSet<Integer>();

		for(String edge : vertex.getEdges())
		{
			dejaVu.add(this.graph.find(edge).getColor());
		}
		
		int color = 0;
		while(color < colorsAmount && !vertex.isColorized())
		{
			if(!dejaVu.contains(color))
			{
				return color;
			}
			else
			{
				color++;
			}
		}
		
		return -1;
	}
	
	private void optimisticSpill(Vertex vertex, int colorsAmount)
	{
		int color = this.getMinimalColor(vertex, colorsAmount);
		
		if(color != -1)
		{
			vertex.colorize(color);
		}
		else
		{
			vertex.spill();
		}
	}

	private boolean existsNotColorizedVertex()
	{
		return this.getNotColorizedVertex() != null;
	}
	
	private Vertex getNotColorizedVertex()
	{
		Vertex found = null;
		int maxDegree = 0;
		
		for(Vertex vertex : this.graph.getVertices())
		{
			if(!vertex.isColorized() && vertex.getDegree() > maxDegree)
			{
				found = vertex;
				maxDegree = vertex.getDegree();
			}
		}
		
		return found;
	}
}
