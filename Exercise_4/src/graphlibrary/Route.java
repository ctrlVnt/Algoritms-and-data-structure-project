package graphlibrary;
/**
 * route of the graph with its first node and value
 * @param G for node's type
 * @param T for the route's label type
 */
public class Route<G,T>{
    
    private G node;
    private T routeValue;

    /**
     * constructor of the route
     * @param node first node of the route
     * @param routeValue value label for the route
     */
    public Route(G node, T routeValue){
      this.node = node;
      this.routeValue = routeValue;
    }

    /**
     * costructor of route only with its first node
     * @param node first node of the route
     */
    public Route(G node){
      this.node = node;
    }

    /**
     * get method
     * @return the first node of the route
     */
    public G getNode(){
      return this.node;
    }

    /**
     * get method
     * @return the label value of route
     */
    public T getRouteValue(){
      return this.routeValue;
    }

    /**
     * set method for first node of route
     * @param newNode
     */
    public void setNode(G newNode){
      this.node = newNode;
    }

    /**
     * set method for label of route
     * @param newRouteValue
     */
    public void setRouteValue(T newRouteValue){
      this.routeValue = newRouteValue;
    }

    /**
     * Override for equals method for comparetwo node object
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
      if(o == null){
        return false;
      }else{
        Route<G,T> tmp = (Route<G,T>) o;
        if(tmp.getNode().equals(this.getNode())){
          return true;
        }else{
          return false;
        }
      }
    }

}