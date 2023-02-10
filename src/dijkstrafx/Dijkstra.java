/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstrafx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author milar
 */
public class Dijkstra {
    
    public List<Integer>  computePath(int numNodes, List<List<Edge>> adjacencyList, int source) {
        long startTime = System.nanoTime();

        int[] dist = new int[numNodes];
        int[] prev = new int[numNodes];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> (int)(o1.dist - o2.dist));
        boolean[] visited = new boolean[numNodes];
        List<Integer> visitedNodes = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        dist[source] = 0;
        queue.add(new Node(source,0));

        while (!queue.isEmpty()) {
            Node u = queue.poll(); //extractmin
            int node = u.number;
            if(visited[node])continue;
            visited[node] = true;
            visitedNodes.add(node);

            for (Edge e : adjacencyList.get(node)) {
                int v = e.to;
                int weight = e.weight;
                if (dist[node] != Integer.MAX_VALUE && dist[node] + weight < dist[v]) {
                    dist[v] = dist[node] + weight;
                    prev[v] = node;
                    queue.add(new Node(v, dist[v]));
                }
            }
        }
        long endTime = System.nanoTime();
        double timeinMillisec = (endTime - startTime)/ 1_000_000.0;
        System.out.println("\n Completed operation in time: " + timeinMillisec + " msec");

        return visitedNodes;
    }
}
