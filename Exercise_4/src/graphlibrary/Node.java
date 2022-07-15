package graphlibrary;
/**
 * node of graph with distance from starting node, the label of itself and the adjacent prevoius node
 * @param S for the node's label type
 * @param T for the node's distance from starting node type
 */
public class Node<S, T> {

  private T distanceFromS;
  private S nodeName;
  private Node<S, T> father;

  /**
   * constructor of node
   * @param nodeName
   * @param distanceFromS value of distance from starting node
   */
  public Node(S nodeName, T distanceFromS) {
    this.distanceFromS = distanceFromS;
    this.nodeName = nodeName;
  }

  /**
   * constructor of node without distance from starting node
   * @param nodeName
   */
  public Node(S nodeName) {
    this.nodeName = nodeName;
  }

  /**
   * constructor of node with its adjacent previous node
   * @param nodeName
   * @param distanceFromS value of distance from starting node
   * @param father the adjacent previous node
   */
  public Node(S nodeName, T distanceFromS, Node<S, T> father) {
    this.distanceFromS = distanceFromS;
    this.nodeName = nodeName;
    this.father = father;
  }

  /**
   * get method
   * @return value of distance from the starting node
   */
  public T getNodedistanceFromS() {
    return this.distanceFromS;
  }

  /**
   * set method
   * @param distanceFromS distance from starting node
   */
  public void setNodedistanceFromS(T distanceFromS) {
    this.distanceFromS = distanceFromS;
  }

  /**
   * get method
   * @return the label of this node
   */
  public S getNodeName() {
    return this.nodeName;
  }

  /**
   * set method for the node label
   * @param nodeName
   */
  public void setNodeName(S nodeName) {
    this.nodeName = nodeName;
  }

  /**
   * set method for the adjacent previous node
   * @param nodeFather
   */
  public void setNodeFather(Node<S, T> nodeFather) {
    this.father = nodeFather;
  }

  /**
   * get method
   * @return the adjacent previous node
   */
  public Node<S, T> getNodeFather() {
    return this.father;
  }

  /**
   * Overrides the method that calculates the hash value
   * If two objects are equal according to the equals(Object) method,
   * then calling the hashCode method on each of the two objects must produce the
   * same integer result.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 19;

    result = prime * result + nodeName.hashCode();

    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    } else {
      Node<S, T> tmp = (Node<S, T>) o;
      if ((this.nodeName.equals(tmp.nodeName))) {
        return true;
      } else {
        return false;
      }
    }
  }
}
