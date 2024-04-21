import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The Graph class represents a graph with vertices and edges.
 * Vertices are labeled with String names.
 * Edges are represented using an adjacency matrix.
 * The graph can be either directed or undirected.
 * 
 * Author: Yongeun Kwon(A01263922)
 */
public class Graph {
    private ArrayList<String> vertexLabels;
    private int[][] adjacencyMatrix;
    private boolean isDirected;
    private ArrayList<String> lastDFSOrder;
    private ArrayList<String> lastDFSDeadEndOrder;
    private ArrayList<String> lastBFSOrder;

    /**
     * Constructs a graph with the given vertex labels and directed/undirected flag.
     * 
     * @param vertexLabels An array of strings representing the names of the
     *                     vertices.
     * @param isDirected   A boolean indicating whether the graph is directed (true)
     *                     or undirected (false).
     */
    public Graph(String[] vertexLabels, boolean isDirected) {
        this.vertexLabels = new ArrayList<>();
        for (String label : vertexLabels) {
            this.vertexLabels.add(label);
        }
        this.adjacencyMatrix = new int[vertexLabels.length][vertexLabels.length];
        this.isDirected = isDirected;
        this.lastDFSOrder = new ArrayList<>();
        this.lastDFSDeadEndOrder = new ArrayList<>();
        this.lastBFSOrder = new ArrayList<>();
    }

    /**
     * Checks if the graph is directed.
     * 
     * @return True if the graph is directed, otherwise false.
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Adds an edge between two vertices.
     * 
     * @param vertex1 The starting vertex.
     * @param vertex2 The destination vertex.
     */
    public void addEdge(String vertex1, String vertex2) {
        int startVertex = vertexLabels.indexOf(vertex1);
        int destVertex = vertexLabels.indexOf(vertex2);
        if (startVertex != -1 && destVertex != -1) {
            adjacencyMatrix[startVertex][destVertex] = 1;
            if (!isDirected) {
                adjacencyMatrix[destVertex][startVertex] = 1;
            }
        }
    }

    /**
     * Returns the number of vertices in the graph.
     * 
     * @return The number of vertices.
     */
    public int size() {
        return vertexLabels.size();
    }

    /**
     * Returns the label of the vertex at the specified index.
     * 
     * @param v The index of the vertex.
     * @return The label of the vertex.
     */
    public String getLabel(int v) {
        return vertexLabels.get(v);
    }

    /**
     * Returns a string representation of the adjacency matrix.
     * 
     * @return A string representation of the adjacency matrix.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertexLabels.size(); i++) {
            sb.append(vertexLabels.get(i)).append(": ");
            for (int j = 0; j < vertexLabels.size(); j++) {
                sb.append(adjacencyMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Performs Breadth First Search (BFS) on the graph.
     * 
     * @param quiet If true, suppresses console output.
     */
    public void runDFS(boolean quiet) {
        lastDFSOrder.clear();
        lastDFSDeadEndOrder.clear();
        boolean[] visited = new boolean[size()];
        for (int i = 0; i < size(); i++) {
            if (!visited[i]) {
                DFSUtil(i, visited, quiet);
            }
        }
    }

    /**
     * Performs Depth First Search (DFS) starting at the specified vertex.
     * 
     * @param v     The index of the starting vertex.
     * @param quiet If true, suppresses console output.
     */
    public void runDFS(String v, boolean quiet) {
        int startVertex = vertexLabels.indexOf(v);
        if (startVertex != -1) {
            lastDFSOrder.clear();
            lastDFSDeadEndOrder.clear();
            boolean[] visited = new boolean[size()];
            DFSUtil(startVertex, visited, quiet);
        }
    }

    /**
     * Performs Breadth First Search (BFS) on the graph.
     * 
     * @param quiet If true, suppresses console output.
     */
    public void runBFS(boolean quiet) {
        lastBFSOrder.clear();
        boolean[] visited = new boolean[size()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    lastBFSOrder.add(getLabel(v));
                    if (!quiet) {
                        System.out.println("Visiting vertex " + getLabel(v));
                    }
                    for (int j = 0; j < size(); j++) {
                        if (adjacencyMatrix[v][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                }
            }
        }
    }

    /**
     * Performs Breadth First Search (BFS) starting at the specified vertex.
     * 
     * @param v     The index of the starting vertex.
     * @param quiet If true, suppresses console output.
     */
    public void runBFS(String v, boolean quiet) {
        int startVertex = vertexLabels.indexOf(v);
        if (startVertex != -1) {
            lastBFSOrder.clear();
            boolean[] visited = new boolean[size()];
            Queue<Integer> queue = new LinkedList<>();
            visited[startVertex] = true;
            queue.offer(startVertex);
            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                lastBFSOrder.add(getLabel(vertex));
                if (!quiet) {
                    System.out.println("Visiting vertex " + getLabel(vertex));
                }
                for (int i = 0; i < size(); i++) {
                    if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                    }
                }
            }
        }
    }

    /**
     * Gets the result of the most recently performed DFS.
     * 
     * @return A string containing the DFS order.
     */
    public String getLastDFSOrder() {
        if (lastDFSDeadEndOrder.isEmpty()) {
            return "No DFS traversal has been performed yet.";
        } else {
            return lastDFSOrder.toString();
        }
    }

    /**
     * Gets the result of the most recently performed DFS.
     * 
     * @return A string containing the dead-end order of DFS.
     */
    public String getLastDFSDeadEndOrder() {
        if (lastDFSDeadEndOrder.isEmpty()) {
            return "No DFS traversal has been performed yet.";
        } else {
            return lastDFSDeadEndOrder.toString();
        }
    }

    /**
     * Gets the result of the most recently performed BFS.
     * 
     * @return A string containing the BFS order.
     */
    public String getLastBFSOrder() {
        if (lastBFSOrder.isEmpty()) {
            return "No BFS traversal has been performed yet.";
        } else {
            return lastBFSOrder.toString();
        }
    }

    // Helper method for DFS traversal
    private void DFSUtil(int v, boolean[] visited, boolean quiet) {
        visited[v] = true;
        lastDFSOrder.add(getLabel(v));
        if (!quiet) {
            System.out.println("Visiting vertex " + getLabel(v));
        }
        for (int i = 0; i < size(); i++) {
            if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                DFSUtil(i, visited, quiet);
            }
        }
        lastDFSDeadEndOrder.add(getLabel(v));
    }

}