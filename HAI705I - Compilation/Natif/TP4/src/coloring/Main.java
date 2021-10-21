package coloring;

import coloring.graph.Graph;
import coloring.graph.Vertex;

public class Main
{
	public static void main(String[] args)
	{
		Graph g = Main.getDiamondGraph();
		
		System.out.println(g);
		g.optimisticColorize(2);
		System.out.println(g);
	}
	
	private static Graph getOrdinaryGraph()
	{
		Graph g = new Graph();
		
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		
		g.add(a);
		g.add(b);
		g.add(c);
		g.add(d);
		
		g.linkVertices(a, b);
		g.linkVertices(a, c);
		g.linkVertices(a, d);
		g.linkVertices(c, d);
		
		return g;
	}
	
	private static Graph getDiamondGraph()
	{
		Graph g = new Graph();
		
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		
		g.add(a);
		g.add(b);
		g.add(c);
		g.add(d);
		
		g.linkVertices(a, b);
		g.linkVertices(b, c);
		g.linkVertices(c, d);
		g.linkVertices(d, a);
		
		return g;
	}
}
