package graphlibrary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GraphTests {

  private Integer i1, i2, i3, i4, i5, i6, i7, i8;
  private Graph<Integer, Integer> graphtest;

  @Before
  public void createGraphTest() throws GraphException {
    i1 = 0;
    i2 = 2;
    i3 = 5;
    i4 = 8;
    i5 = 10;
    i6 = 12;
    i7 = 20;
    i8 = 25;

    graphtest = new Graph<>(false);
  }

  @Test
  public void testIsOriented(){
    assertFalse(graphtest.isOriented());
  }

  @Test
  public void testIsEmpty_zeroElEdge() {
    assertTrue(graphtest.isEmpty());
  }

  @Test
  public void testIsEmpty_zeroElRoute()throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    assertTrue(graphtest.isEmptyRoutes());
  }

  @Test
  public void testIsEmpty_oneElEdge() throws Exception{
    graphtest.addNode(i1);
    assertFalse(graphtest.isEmpty());
  }

  @Test
  public void testIsEmpty_oneElRoute() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addRoute(i1, i2, 13);
    assertFalse(graphtest.isEmptyRoutes());
  }

  @Test
  public void testSize_zeroElEdge() throws Exception{
    assertEquals(0,graphtest.getNodesCount());
  }

  @Test
  public void testSize_zeroElRoute() throws Exception{
    assertEquals(0,graphtest.getRoutesCount());
  }

  @Test
  public void testSize_oneEladdEdge() throws Exception{
    graphtest.addNode(i1);
    assertEquals(1,graphtest.getNodesCount());
  }

  @Test
  public void testSize_oneEladdRoute() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i3);
    graphtest.addRoute(i1, i3, 26);
    assertEquals(1,graphtest.getRoutesCount());
  }

  @Test
  public void testSize_threeElEdge() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    assertEquals(3,graphtest.getNodesCount());
  }

  @Test
  public void testSize_threeElRoutes() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    assertEquals(3,graphtest.getRoutesCount());
  }

  @Test
  public void testExist_oneElEdge() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i3);
    graphtest.addNode(i2);
    graphtest.addNode(i6);
    assertTrue(graphtest.existingNode(i2));
  }

  @Test
  public void testExist_oneElRoute() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addNode(i4);
    graphtest.addNode(i5);
    graphtest.addNode(i6);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    graphtest.addRoute(i4, i5, 1);
    graphtest.addRoute(i3, i6, 30);
    graphtest.addRoute(i5, i6, 88);
    graphtest.addRoute(i6, i1, 75);
    assertTrue(graphtest.existingRoute(i2, i3));
  }

  @Test
  public void testdelete_oneEdge() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addNode(i4);
    graphtest.addNode(i5);
    graphtest.addNode(i6);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    graphtest.addRoute(i4, i5, 1);
    graphtest.addRoute(i3, i6, 30);
    graphtest.addRoute(i5, i6, 88);
    graphtest.addRoute(i6, i1, 75);
    graphtest.deleteNode(i5);
    assertFalse(graphtest.existingNode(i5));
  }

  @Test
  public void testdelete_oneRoute() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addNode(i4);
    graphtest.addNode(i5);
    graphtest.addNode(i6);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    graphtest.addRoute(i4, i5, 1);
    graphtest.addRoute(i3, i6, 30);
    graphtest.addRoute(i5, i6, 88);
    graphtest.addRoute(i6, i1, 75);
    graphtest.deleteRoute(i2, i3);
    assertFalse(graphtest.existingRoute(i2, i3));
  }

  @Test
  public void test_adjacent() throws Exception{

    int result [] = {i3, i2, i6};

    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addNode(i4);
    graphtest.addNode(i5);
    graphtest.addNode(i6);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    graphtest.addRoute(i4, i5, 1);
    graphtest.addRoute(i3, i6, 30);
    graphtest.addRoute(i5, i6, 88);
    graphtest.addRoute(i6, i1, 75);

    int test [] = new int [3];
    LinkedList<Route<Integer,Integer>> test1 = graphtest.getAdjacentNodes(i1);

    for (int i = 0; i < 3; i++){
      test[i] = test1.remove().getNode();
    }

    assertArrayEquals(result, test);
  }

  @Test
  public void test_getEdge() throws Exception{

    Integer result[] = {i1, i2, i3, i4, i5, i6};

    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addNode(i4);
    graphtest.addNode(i5);
    graphtest.addNode(i6);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    graphtest.addRoute(i4, i5, 1);
    graphtest.addRoute(i3, i6, 30);
    graphtest.addRoute(i5, i6, 88);
    graphtest.addRoute(i6, i1, 75);
    Set<Integer> a =  graphtest.getNodes();
    Integer [] test = new Integer [6];
    a.toArray(test); 
    assertArrayEquals(test, result);
  }

  @Test
  public void test_getRoute() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
 
    Set<EntireRoute<Integer, Integer>> a =  graphtest.getRoutes();
    LinkedList<EntireRoute<Integer, Integer>> test2 = new LinkedList<EntireRoute<Integer, Integer>>();
    test2.add(new EntireRoute<Integer, Integer>(i1, i2, false));
    test2.add(new EntireRoute<Integer, Integer>(i1, i3, false));
    test2.add(new EntireRoute<Integer, Integer>(i2, i3, false));

    int count = 0;
    for (Iterator<EntireRoute<Integer, Integer>> i = a.iterator(); i.hasNext();) {
      EntireRoute<Integer, Integer> e = i.next();
      if(e.equals(test2.remove())){
        count++;
      } 
    }

    assertEquals(3, count);
  }

  @Test
  public void test_getRouteValue() throws Exception{
    graphtest.addNode(i1);
    graphtest.addNode(i2);
    graphtest.addNode(i3);
    graphtest.addNode(i4);
    graphtest.addNode(i5);
    graphtest.addNode(i6);
    graphtest.addRoute(i1, i3, 26);
    graphtest.addRoute(i1, i2, 18);
    graphtest.addRoute(i2, i3, 44);
    graphtest.addRoute(i4, i5, 1);
    graphtest.addRoute(i3, i6, 30);
    graphtest.addRoute(i5, i6, 88);
    graphtest.addRoute(i6, i1, 75);

    Integer value = 88;
    assertTrue(value.equals(graphtest.getRouteLabel(i5, i6)));
  }
}
