/* Aidan Border
 * CSC 3380
 * Module 5 Assignment 1
 * Part 1: Implement & Compare Topographical Sort Algorithms
 * NOTE: Use something smaller than seconds when comparing your algorithms. You'll get 0 seconds for both algorithms
 * if you use seconds, which is obviously unhelpful. */

import java.util.*;

public class Main {
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		BFSGraph bfsGraph = new BFSGraph(6);
		bfsGraph.addEdge(5, 2);
		bfsGraph.addEdge(5, 0);
		bfsGraph.addEdge(4, 0);
		bfsGraph.addEdge(4, 1);
		bfsGraph.addEdge(2, 3);
		bfsGraph.addEdge(3, 1);
		System.out.println(
				"Following is a Topological Sort");
		bfsGraph.topologicalSort();


	}
}

// Class to represent a graph
class BFSGraph {
	// No. of vertices
	int vertexNum;

	// An Array of List which contains
	// references to the Adjacency List of
	// each vertex
	List<Integer> adjacencyList[];

	// Constructor
	public BFSGraph(int V) {
		this.vertexNum = V;
		adjacencyList = new ArrayList[V];
		for (int i = 0; i < V; i++)
			adjacencyList[i] = new ArrayList<Integer>();
	}

	// Function to add an edge to graph
	public void addEdge(int u, int v) {
		adjacencyList[u].add(v);
	}

	// prints a Topological Sort of the
	// complete graph
	public void topologicalSort() {
		// Create a array to store
		// indegrees of all
		// vertices. Initialize all
		// indegrees as 0.
		int[] indegree = new int[vertexNum];

		// Traverse adjacency lists
		// to fill indegrees of
		// vertices. This step takes
		// O(V+E) time
		for (int i = 0; i < vertexNum; i++) {
			ArrayList<Integer> temp
					= (ArrayList<Integer>) adjacencyList[i];
			for (int node : temp) {
				indegree[node]++;
			}
		}

		// Create a queue and enqueue
		// all vertices with indegree 0
		Queue<Integer> q
				= new LinkedList<Integer>();
		for (int i = 0; i < vertexNum; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		// Initialize count of visited vertices
		int count = 0;

		// Create a vector to store result
		// (A topological ordering of the vertices)
		Vector<Integer> topOrder = new Vector<Integer>();
		while (!q.isEmpty()) {
			// Extract front of queue
			// (or perform dequeue)
			// and add it to topological order
			int u = q.poll();
			topOrder.add(u);

			// Iterate through all its
			// neighbouring nodes
			// of dequeued node u and
			// decrease their in-degree
			// by 1
			for (int node : adjacencyList[u]) {
				// If in-degree becomes zero,
				// add it to queue
				if (--indegree[node] == 0)
					q.add(node);
			}
			count++;
		}

		// Check if there was a cycle
		if (count != vertexNum) {
			System.out.println("There exists a cycle in the graph");
			return;
		}

		// Print topological order
		for (int i : topOrder) {
			System.out.print(i + " ");
		}
	}
}

