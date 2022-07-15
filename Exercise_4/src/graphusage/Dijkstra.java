package graphusage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;
import java.lang.Double;


import minimalheap.*;
import graphlibrary.*;


public class Dijkstra <S> {

  /**
   * Implementation of Dijkstra algorithm used to calculate the minimal paths 
   * from a source node inside a graph.
   * @param graph
   * @param sourceNode
   * @return a hashmap containing all possible destination nodes and their distance
   * @throws MinimalHeapException
   * @throws GraphException
   */
  public HashMap<S,Node<S, Double>> dijkstra(Graph<Node<S, Double>, Double> graph, Node<S, Double> sourceNode) throws MinimalHeapException, GraphException{ 
    HashMap<S, Node<S, Double>> support = new HashMap<>();
    for (Iterator<Node<S, Double>> i = graph.getNodes().iterator(); i.hasNext();) {
      Node<S, Double> tmp = i.next();
      tmp.setNodedistanceFromS(Double.MAX_VALUE);
      tmp.setNodeFather(null);
      support.put(tmp.getNodeName(), tmp);
    }

    sourceNode.setNodedistanceFromS(0.0);
    MinimalHeap<Node<S, Double>> queue =  makePriorityQueue(graph.getNodes(), sourceNode);
    support.replace(sourceNode.getNodeName(), sourceNode);
    HashMap<S, Node<S, Double>> S = new HashMap<>();

    while(!queue.isEmpty()){
      Node<S, Double> u = queue.extractMinimum();
      S.put(u.getNodeName(), u);
      LinkedList<Route<Node<S, Double>, Double>> adjacents = graph.getAdjacentNodes(u);

      if(adjacents.size() > 0){
        for(int i = 0; i < adjacents.size(); i++){
          Node<S, Double> v = support.get(adjacents.get(i).getNode().getNodeName());
          if((v.getNodedistanceFromS()).compareTo(Double.sum(u.getNodedistanceFromS(), graph.getRouteLabel(u, v))) > 0){
            v.setNodedistanceFromS(Double.sum(u.getNodedistanceFromS(), graph.getRouteLabel(u, v)));
            v.setNodeFather(u);
            Node<S,Double> decreasedV = new Node<>(v.getNodeName(), Double.sum(u.getNodedistanceFromS(), graph.getRouteLabel(u, v)), v.getNodeFather()); 
            queue.decreaseValue(v, decreasedV);
            support.replace(v.getNodeName(), decreasedV);
          }
        }
      }
    }
    return S;
  }

  /**
   * Creates and load the minimal heap used by Dijkstra to calculate
   * the minimal paht to all the destination nodes
   * @param nodes
   * @param sourceNode
   * @return a minimal heap loaded with all the nodes of the graph
   * @throws MinimalHeapException
   */
  private MinimalHeap<Node<S, Double>> makePriorityQueue(Set<Node<S, Double>> nodes, Node<S, Double> sourceNode) throws MinimalHeapException{
    MinimalHeap<Node<S, Double>> queue = new MinimalHeap<>(new NodeComparator<S>());
    queue.insert(sourceNode);
    for (Iterator<Node<S, Double>> i = nodes.iterator(); i.hasNext();) {
      Node<S, Double> tmp = i.next();
      queue.insert(tmp);
    }
    return queue;
  }

}