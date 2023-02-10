/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstrafx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author milar
 */
public class RandomGraph {
private  int numNodes;
    private  int numEdges;
    private  int source;
    private List<List<Edge>> adjacencyList;

    public RandomGraph(int numNodes, int numEdges) {
    }

    private void initializeRandomEdges(int numEdges) {
        int edgesGenerated = 0;
        Random rand = new Random();

        while (edgesGenerated < numEdges){               //generazione archi
            int from = rand.nextInt(numNodes);
            int to = rand.nextInt(numNodes);
            if (from != to) {                             //controllo self loop

                boolean edgeExists = false;               //controllo archi paralleli
                for (Edge e : adjacencyList.get(from)) {
                    if (e.to == to) {
                        edgeExists = true;
                        break;
                    }
                }
                if (!edgeExists){
                    int weight = rand.nextInt(20) + 1;
                    Edge edge = new Edge(from, to, weight);
                    adjacencyList.get(from).add(edge);
                    edgesGenerated++;
                }
            }
        }

    }
    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
        initializeRandomEdges(numEdges);
    }

    public void setNumNodes(int numNodes,int source) {
        this.numNodes = numNodes;
        adjacencyList = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        initializeSourceEdge(source);
    }
    public void setSource(int source) {
        this.source = source;
    }

    private void initializeSourceEdge(int source) {       // controllo sorgente-nodo uscente
        Random rand = new Random();
        int to = rand.nextInt(numNodes);
        while (to == source) {
            to = rand.nextInt(numNodes);
        }
        int weight = rand.nextInt(20) + 1;
        Edge edge = new Edge(source, to, weight);
        adjacencyList.get(source).add(edge);
    }

    public String printPath(){
        Dijkstra dijkstra = new Dijkstra();
        String res = dijkstra.computePath(this.numNodes, this.adjacencyList, this.source).toString();

        return res;
    }

}
    
