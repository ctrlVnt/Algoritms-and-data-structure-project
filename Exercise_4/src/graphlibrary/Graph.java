package graphlibrary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * generic complete graph
 * @param G for node's type
 * @param T for route's type
 * @author Lavric & Venturini
 */
public class Graph<G, T> {
  private HashSet<EntireRoute<G,T>> routes;
  private HashMap<G, LinkedList<Route<G, T>>> graph;
  private boolean direction;

  /**
   * costructor for empty graph
   * 
   * @param direction to indicate if is oriented or non-oriented
   * @throws GraphException if direction is null
   */
  public Graph(Boolean direction) throws GraphException {
    if (direction == null) {
      throw new GraphException("Graph constructor: direction parameter cannot be null");
    }
    this.direction = direction;
    this.graph = new HashMap<G, LinkedList<Route<G, T>>>();
    this.routes = new HashSet<EntireRoute<G,T>>();
  }

  /**
   * Adds a new node in the graph and create its adjacency list
   * 
   * @param newNode the new node
   * @throws GraphException error if the parameter doesn't exist
   */
  public void addNode(G newNode) throws GraphException {
    if (newNode == null) {
      throw new GraphException("addNode: parameter cannot be null");
    }
    if(!existingNode(newNode)){
      graph.put(newNode, new LinkedList<Route<G, T>>());
    }
  }

  /**
   * Add a new route for 2 existing nodes or, if a node doesn't
   * exist, it will be created and added to the graph
   * 
   * @param initialNode,destinationNode,routeValue
   * @throws GraphException if some parameter is null or if exist a loop in
   *                        non-oriented graph
   */
  public void addRoute(G initialNode, G destinationNode, T routeValue) throws GraphException {
    if (initialNode == null || destinationNode == null || routeValue == null) {
      throw new GraphException("addRoute: One or more parameters are null");
    }
    if (initialNode.equals(destinationNode) && isOriented() == false) {
      throw new GraphException("addRoute: a non directed graph cannot have a loop");
    }

    if (!existingRoute(initialNode, destinationNode)) {
      if (!this.graph.containsKey(destinationNode)) {
        addNode(destinationNode);
      }
      if (!this.graph.containsKey(initialNode)) {
        addNode(initialNode);
      }
      if (direction == true) {
        this.graph.get(initialNode).add(new Route<G, T>(destinationNode, routeValue));
      } else {
        this.graph.get(initialNode).add(new Route<G, T>(destinationNode, routeValue));
        this.graph.get(destinationNode).add(new Route<G, T>(initialNode, routeValue));
      }
      this.routes.add(new EntireRoute<G,T>(initialNode, destinationNode, direction, routeValue));
    }
  }

  /**
   * verify if the graph is oriented or non-oriented
   * 
   * @return true or false
   */
  public boolean isOriented() {
    return direction;
  }

  /**
   * verify if exist a route between two nodes
   * 
   * @param initialNode,destinationNode
   * @return true or false
   * @throws GraphException if some parameter is null
   */
  public boolean existingRoute(G initialNode, G destinationNode) throws GraphException {
    if (initialNode == null || destinationNode == null) {
      throw new GraphException("existingRoute: One or more parameters are null");
    }
    if (!this.graph.containsKey(destinationNode) || !this.graph.containsKey(initialNode)) {
      return false;
    } else if (routes.contains(new EntireRoute<G,T>(initialNode, destinationNode, direction)) || (routes.contains(new EntireRoute<G,T>(destinationNode, initialNode, direction)) && this.direction == false)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * verify if the node exists into the graph
   * 
   * @param node
   * @return true or false
   * @throws GraphException if node parameter is null
   */
  public boolean existingNode(G node) throws GraphException {
    if (node == null) {
      throw new GraphException("existingNode: Node parameter cannot be null");
    } else {
      return this.graph.containsKey(node);
    }
  }

  /**
   * delete the node into the graph and its routes
   * 
   * @param delNode
   * @throws GraphException if delNode is null or if delNode don't exist
   */
  public void deleteNode(G delNode) throws GraphException {
    if (delNode == null) {
      throw new GraphException("deleteNode: delNode parameter can not be null");
    }
    if (!existingNode(delNode)) {
      System.out.println("deleteNode: delNode does not exists");
    }

    graph.remove(delNode);
    for (Map.Entry<G, LinkedList<Route<G, T>>> entry : graph.entrySet()) {
      entry.getValue().remove(new Route<G, T>(delNode));
    }

    for (Iterator<EntireRoute<G,T>> i = routes.iterator(); i.hasNext();) {
      EntireRoute<G,T> e = i.next();
      if (e.getFirstNode().equals(new EntireRoute<G,T>(delNode, delNode, direction)) || e.getSecondNode().equals(new EntireRoute<G,T>(delNode, delNode, direction))) {
        i.remove();
      }
    }
  }

  /**
   * delete the route (from intialNode to destinationNode) into the graph
   * 
   * @param initialNode,destinationNode
   * @throws GraphException if one parameter is null
   */
  public void deleteRoute(G initialNode, G destinationNode) throws GraphException {

    if (initialNode == null || destinationNode == null) {
      throw new GraphException("deleteRoute: initialNode or destinationNode cannot be null");
    }
    if (existingNode(initialNode) && existingNode(destinationNode)) {

      graph.get(initialNode).remove(new Route<G, T>(destinationNode));

      if (!isOriented()) {
        graph.get(destinationNode).remove(new Route<G, T>(initialNode));
      }

      routes.remove(new EntireRoute<G,T>(initialNode, destinationNode, direction));

    } else {
      throw new GraphException("deleteRoute: one or more nodes don't exist");
    }
  }

  /**
   * view the number of nodes into the graph
   * 
   * @return size of graph
   */
  public int getNodesCount() {
    return graph.size();
  }

  /**
   * view the number of routes into the graph
   * 
   * @return size of routes
   */
  public int getRoutesCount() {
    return routes.size();
  }

  /**
   * view the vector of nodes into the graph
   * 
   * @return vector of nodes
   */
  public Set<G> getNodes() {
    return graph.keySet();
  }

  /**
   * view the vector of routes into the graph
   * 
   * @return vector of routes
   */
  public Set<EntireRoute<G,T>> getRoutes() {
    return routes;
  }

  /**
   * get all adjacent nodes of the node in exam
   * 
   * @param node
   * @return linkedList of vertices adjacent of node
   * @throws GraphException if node is null or node doesn't exist into the graph
   */
  public LinkedList<Route<G, T>> getAdjacentNodes(G node) throws GraphException {
    if (node == null) {
      throw new GraphException("getAdjacentNodes: node parameter cannot be null");
    }
    if (!existingNode(node)) {
      System.out.println("getAdjacentNodes: node parameter doesn't exist");
    }
    return graph.get(node);
  }

  /**
   * get the label of route in exam
   * 
   * @param initialNode,destinationNode
   * @return value of the route
   * @throws GraphException if some of parameters are null or don't exist
   */
  public T getRouteLabel(G initialNode, G destinationNode) throws GraphException {
    if (initialNode == null || destinationNode == null) {
      throw new GraphException("getRouteLabel: initialNode and destinationNode parameters cannot be null");
    }
    if (!existingRoute(initialNode, destinationNode)) {
      System.out.println("getRouteLabel: the node does not exists");
      return null;
    }
    T routeValue = null;
    for (Route<G, T> tmp : graph.get(initialNode)) {
      if (tmp.getNode().equals(destinationNode)) {
        routeValue = tmp.getRouteValue();
      }
    }

    return routeValue;
  }

  /**
   * verify if the graph is empty
   * 
   * @return true or false
   */
  public Boolean isEmpty() {
    return graph.isEmpty();
  }

  /**
   * verify if there are some routes in the graph
   * 
   * @return: true or false
   */
  public Boolean isEmptyRoutes() {
    return routes.isEmpty();
  }

}
