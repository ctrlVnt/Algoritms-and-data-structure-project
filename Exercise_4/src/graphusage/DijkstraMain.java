package graphusage;

import java.util.HashMap;
import java.io.BufferedReader;
import java.util.Map;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.lang.Double;
import java.util.Scanner;
import graphlibrary.*;
import minimalheap.*;

public class DijkstraMain {

  private static final Charset ENCODING = StandardCharsets.UTF_8;

  private static void loadCSV(String filepath, Graph<Node<String, Double>, Double> graph)
      throws IOException, Exception {
    System.out.println("\nLoading data from file...");

    Path inputFilePath = Paths.get(filepath);
    try (BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
      String line = null;
      while ((line = fileInputReader.readLine()) != null) {
        String[] lineElements = line.split(",");
        if(Double.parseDouble(lineElements[2]) >= 0){
          graph.addNode(new Node<String, Double>(lineElements[0]));
          graph.addRoute(new Node<String, Double>(lineElements[0]), new Node<String, Double>(lineElements[1]),
          Double.parseDouble(lineElements[2]));
        }
      }
    }

    System.out.println("Data loaded\n");
  }

  private static void newDestination(Scanner scanner, HashMap<String, Node<String, Double>> dijkstraGraph, Node<String, Double> startingCity){
    boolean nextCity = true;
    Node<String, Double> destination;
    while(nextCity == true){
      System.out.println("\nWhere do you wanna go?");
      destination = new Node<String, Double>(scanner.nextLine().toLowerCase().trim());
      while(dijkstraGraph.get(destination.getNodeName()) == null){
        System.out.println("\nThe selected destination doesn't exist. Please reinsert the value.");
        destination = new Node<String, Double>(scanner.nextLine().toLowerCase().trim());
      }

      System.out.println("\nThe distance to " + destination.getNodeName() + " from " + startingCity.getNodeName()
          + " is: " + Math.floor(dijkstraGraph.get(destination.getNodeName()).getNodedistanceFromS()) / 1000 + "km");
      System.out.println("\nDo you want to print all the path?");
      System.out.println("1 - yes");
      System.out.println("2 - no");
      if (scanner.nextInt() == 1){
        printPath(dijkstraGraph.get(destination.getNodeName()));
      }

      System.out.println("\nDo you want to see another destination?");
      System.out.println("1 - yes");
      System.out.println("2 - no");
      if(scanner.nextInt() != 1){
        nextCity = false;
      }
      scanner.nextLine();     
    }
  }

  private static void printPath(Node<String, Double> element) {
    if (element != null) {
      printPath(element.getNodeFather());
      System.out.println("-> " + element.getNodeName() + " ->");
    }
  }

  private static void printAllMinimalPaths(HashMap<String, Node<String, Double>> S) {
    for (Map.Entry<String, Node<String, Double>> entry : S.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue().getNodedistanceFromS() / 1000);
    }
  }

  private static HashMap<String, Node<String, Double>> testDijkstraAlgorithm()
      throws GraphException, IOException, Exception, MinimalHeapException {
    Scanner scanner = new Scanner(System.in);
    Node<String, Double> startingCity;

    Graph<Node<String, Double>, Double> graph = new Graph<>(false);
    loadCSV("../italian_dist_graph.csv", graph);

    System.out.println("Please write what is your starting city: ");
    boolean okStart = false;
    startingCity = new Node<String, Double>(scanner.nextLine().toLowerCase().trim());
    while(okStart != true){
      try {
        if(graph.existingNode(startingCity) == false){
          System.out.println("Reinsert the starting city;");
          startingCity = new Node<String, Double>(scanner.nextLine().toLowerCase().trim());
        }else{
          okStart = true;
        } 
      }
      catch(GraphException e){
        System.out.println("Reinsert the starting city;");
        startingCity = new Node<String, Double>(scanner.nextLine().toLowerCase().trim());
      }
    }
   

    Dijkstra<String> dijkstra = new Dijkstra<>();
    HashMap<String, Node<String, Double>> dijkstraGraph = dijkstra.dijkstra(graph,
      new Node<String, Double>(startingCity.getNodeName(), 0.0));
    
    newDestination(scanner, dijkstraGraph, startingCity);

    scanner.close();
    return dijkstraGraph;
  }


  public static void main(String[] args) throws GraphException, IOException, Exception, MinimalHeapException {
    HashMap<String, Node<String, Double>> test = testDijkstraAlgorithm();
    boolean printAll = false;

    if (printAll == true) {
      printAllMinimalPaths(test);
    }

    System.out.println("\nFINE\n");

  }
}
