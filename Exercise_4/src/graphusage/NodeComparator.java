package graphusage;

import graphlibrary.*;
import java.util.Comparator;
import java.lang.Double;

/**
 * comparator for node-type objects
 * @author Venturini & Cosmin
 */

public class NodeComparator<S> implements Comparator<Node<S,Double>> {   //rivedere comparator che non funziona
  @Override
  public int compare(Node<S,Double> r1, Node<S,Double> r2){
    return (Double.compare(r1.getNodedistanceFromS(),r2.getNodedistanceFromS()));
  }
}