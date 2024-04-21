public class driver {
    public static void main(String[] args) {
        String[] vnames = { "a", "b", "c", "d" };
        Graph G = new Graph(vnames, false);

        G.addEdge("a", "b");
        G.addEdge("a", "d");
        G.addEdge("b", "c");
        G.addEdge("b", "d");
        G.addEdge("c", "d");

        System.out.println("Adjacency Matrix:");
        System.out.println(G);
        System.out.println();
        // Performing DFS
        System.out.println("G DFS order traversal of graph:");
        G.runDFS(false);
        System.out.println(G.getLastDFSOrder());
        System.out.println(G.getLastDFSDeadEndOrder());

        System.out.println();
        // Performing BFS
        System.out.println("G BFS traversal of graph:");
        G.runBFS(false);
        System.out.println(G.getLastBFSOrder());

        System.out.println();
        System.out.println("G DFS traversal of graph from b:");
        G.runDFS("b", false);
        System.out.println(G.getLastDFSOrder());

        // String[] vnames1 = { "a", "b", "c", "d" };
        // Graph G1 = new Graph(vnames1, true);

        // G1.addEdge("a", "b");
        // G1.addEdge("a", "d");
        // G1.addEdge("b", "c");
        // G1.addEdge("b", "d");
        // G1.addEdge("c", "d");

        // System.out.println("Adjacency Matrix:");
        // System.out.println(G1);
        // System.out.println();
        // // Performing DFS
        // System.out.println("G1 DFS order traversal of graph:");
        // G1.runDFS(true);
        // System.out.println(G1.getLastDFSOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("G1 BFS traversal of graph:");
        // G1.runBFS(true);
        // System.out.println(G1.getLastBFSOrder());

        // Sample graph data
        // String[] vnames2 = { "a", "b", "c", "d", "e", "f", "g", "h" };
        // Graph G2 = new Graph(vnames2, false);

        // // Adding edges to the graph
        // G2.addEdge("a", "b");
        // G2.addEdge("a", "e");
        // G2.addEdge("a", "f");
        // G2.addEdge("b", "f");
        // G2.addEdge("b", "g");
        // G2.addEdge("c", "d");
        // G2.addEdge("c", "g");
        // G2.addEdge("d", "h");
        // G2.addEdge("e", "f");
        // G2.addEdge("g", "h");

        // // Undirected Connected Graph (Acyclic)
        // G2.addEdge("a", "b");
        // G2.addEdge("a", "d");
        // G2.addEdge("b", "c");
        // G2.addEdge("b", "e");
        // G2.addEdge("c", "e");
        // G2.addEdge("d", "e");
        // // [a, b, c, e, d]
        // // [a, b, d, c, e]

        // // Directed Connected Graph (Acyclic)
        // G2.addEdge("a", "b");
        // G2.addEdge("a", "d");
        // G2.addEdge("b", "c");
        // G2.addEdge("d", "e");
        // G2.addEdge("e", "f");
        // // [a, b, c, d, e, f]
        // // [a, b, d, c, e, f]

        // System.out.println("Adjacency Matrix:");
        // System.out.println(G2);
        // System.out.println();

        // // Performing DFS
        // System.out.println("G2 DFS order traversal of graph:");
        // G2.runDFS(false);
        // // System.out.println(G.getLastDFSOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("G2 BFS traversal of graph:");
        // G2.runBFS(false);
        // System.out.println(G2.getLastBFSOrder());

        // // Graph with Zero Edges
        // String[] vnames3 = { "a" };
        // Graph G3 = new Graph(vnames3, false);
        // // [a]
        // // [a]

        // System.out.println("Adjacency Matrix:");
        // System.out.println(G3);
        // System.out.println();

        // // Performing DFS
        // System.out.println("G3 DFS order traversal of graph:");
        // G3.runDFS(false);
        // // System.out.println(G3.getLastDFSOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("G3 BFS traversal of graph:");
        // G3.runBFS(false);
        // System.out.println(G3.getLastBFSOrder());

        // // Graph with Zero Edges
        // String[] vnames4 = { "a", "b", "c", "d" };
        // Graph G4 = new Graph(vnames4, false);
        // // Undirected Connected Graph (Cyclic)
        // G4.addEdge("a", "b");
        // G4.addEdge("a", "d");
        // G4.addEdge("b", "c");
        // G4.addEdge("c", "d");
        // // [a, b, c, d]
        // // [a, b, d, c]

        // System.out.println("Adjacency Matrix:");
        // System.out.println(G4);
        // System.out.println();

        // // Performing DFS
        // System.out.println("G4 DFS order traversal of graph:");
        // G4.runDFS(false);
        // // System.out.println(G.getLastDFSOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("G4 BFS traversal of graph:");
        // G4.runBFS(false);
        // System.out.println(G4.getLastBFSOrder());

        // // Directed Connected Graph (Cyclic)
        // String[] vnames5 = { "a", "b", "c", "d" };
        // Graph G5 = new Graph(vnames5, false);
        // G5.addEdge("a", "b");
        // G5.addEdge("a", "d");
        // G5.addEdge("b", "c");
        // G5.addEdge("c", "d");
        // G5.addEdge("d", "a"); // creating a cycle
        // // [a, b, c, d]
        // // [a, b, d, c]

        // System.out.println("Adjacency Matrix:");
        // System.out.println(G5);
        // System.out.println();

        // // Performing DFS
        // System.out.println("G5 DFS order traversal of graph:");
        // G5.runDFS(false);
        // // System.out.println(G5.getLastDFSOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("G5 BFS traversal of graph:");
        // G5.runBFS(false);
        // System.out.println(G5.getLastBFSOrder());

        // Graph with Zero Edges
        // String[] vnames6 = { "a", "b" };
        // Graph G6 = new Graph(vnames6, false);
        // // [a]
        // // [a]

        // System.out.println("Adjacency Matrix:");
        // System.out.println(G6);
        // System.out.println();

        // // Performing DFS
        // System.out.println("G6 DFS order traversal of graph:");
        // G6.runDFS(false);
        // System.out.println(G6.getLastDFSOrder());
        // System.out.println(G6.getLastDFSDeadEndOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("G6 BFS traversal of graph:");
        // G6.runBFS(false);
        // System.out.println(G6.getLastBFSOrder());

        // Displaying the adjacency matrix
        // System.out.println("Adjacency Matrix:");
        // System.out.println(G);

        // // Performing DFS
        // System.out.println("DFS order traversal of graph:");
        // G.runDFS(false);
        // // System.out.println(G.getLastDFSOrder());

        // System.out.println();
        // // Performing BFS
        // System.out.println("BFS traversal of graph:");
        // G.runBFS(false);
        // System.out.println(G.getLastBFSOrder());

        // // 그래프 생성
        // String[] vertices = { "A", "B", "C", "D", "E", "F" };
        // Graph graph = new Graph(vertices, false); // 무방향 그래프 생성

        // // 간선 추가
        // graph.addEdge("A", "B");
        // graph.addEdge("A", "C");
        // graph.addEdge("B", "D");
        // graph.addEdge("B", "E");
        // graph.addEdge("C", "F");

        // // BFS 테스트 - 특정 정점 "B"에서 시작
        // System.out.println("BFS from vertex B:");
        // graph.runBFS("B", false);
        // System.out.println("BFS Order: " + graph.getLastBFSOrder());
    }
}
