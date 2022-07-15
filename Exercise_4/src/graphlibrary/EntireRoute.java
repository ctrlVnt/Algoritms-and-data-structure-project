package graphlibrary;
/**
 * entire route of a graph with both nodes, the direction and the label
 * @param G for node's type
 * @param T for route's label type
 */
public class EntireRoute <G, T>{

    private G firstNode;
    private G secondNode;
    private boolean direction;
    private T weight;

    /**
     * constructor of route
     * @param firstNode first route node
     * @param secondNode second route node
     * @param direction true if it is oriented, false otherwise
     * @param weight route label
     */

    public EntireRoute (G firstNode,G secondNode,boolean direction, T weight){
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.direction = direction;
        this.weight = weight;
    }

    /**
     * constructor of route without label
     * @param firstNode first route node
     * @param secondNode second route node
     * @param direction true if it is oriented, false otherwise
     */
    public EntireRoute(G firstNode, G secondNode, boolean direction){
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.direction = direction;
    }

    /**
     * get method
     * @return the first node
     */
    public G getFirstNode(){
        return this.firstNode;
    }

    /**
     * get method
     * @return the second node of route
     */
    public G getSecondNode(){
        return this.secondNode;
    }

    /**
     * set method for route label
     * @param weight label value of the route
     */
    public void setWeight(T weight){
      this.weight = weight;
    }

    /**
     * get method
     * @return label value of route
     */
    public T getWeight(){
      return this.weight;
    }
    

  /**
   * Overrides the method that calculates the hash value
   * If two objects are equal according to the equals(Object) method,
   * then calling the hashCode method on each of the two objects must produce the same integer result.
   */
  @Override
  public int hashCode() {
    final int prime = 17;
    int result = 1;
    
    if (direction) {
      result = prime * result + ((secondNode == null) ? 0 : secondNode.hashCode());
      result = prime * result + ((firstNode == null) ? 0 : firstNode.hashCode());
    }
    else{
      result = prime * result + (((secondNode == null) ? 0 : secondNode.hashCode()) + ((firstNode == null) ? 0 : firstNode.hashCode()));
    }

    return result;
  }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
      if(o == null){
        return false;
      }else{
        EntireRoute<G, T> tmp = (EntireRoute<G, T>) o;
        if((this.firstNode.equals(tmp.firstNode) && this.secondNode.equals(tmp.secondNode)) || ((!direction && this.firstNode.equals(tmp.secondNode) && this.secondNode.equals(tmp.firstNode)))){
          return true;
        }else{
          return false;
        }
      }
    }

}
