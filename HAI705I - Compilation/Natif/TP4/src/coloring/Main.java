package coloring;

import coloring.graph.Graph;
import coloring.graph.Vertex;

public class Main
{
	public static void main(String[] args)
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
		
		System.out.println(g);

		g.colorize(2);
		
		System.out.println(g);
	}
}
