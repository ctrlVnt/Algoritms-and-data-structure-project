package minimalheap;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * it specifies a test suite for the Minimal Heap library
 * @author Lavric & Venturini
 */
public class MinimalHeapTests {

  class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }
  }


  private Integer i1, i2, i3, i4, i5, i6, i7, i8;
  private MinimalHeap<Integer> minimalHeap;

  @Before
  public void createMinimalHeap() throws MinimalHeapException{
    i1 = 0;
    i2 = 2;
    i3 = 5;
    i4 = 8;
    i5 = 10;
    i6 = 12;
    i7 = 20;
    i8 = 25;

    minimalHeap = new MinimalHeap<>(new IntegerComparator());
  }

  @Test
  public void testIsEmpty_zeroEl(){
    assertTrue(minimalHeap.isEmpty());
  }

  @Test
  public void testIsEmpty_oneEl() throws Exception{
    minimalHeap.insert(i1);
    assertFalse(minimalHeap.isEmpty());
  }


  @Test
  public void testSize_zeroEl() throws Exception{
    assertEquals(0,minimalHeap.array.size());
  }

  @Test
  public void testSize_oneEl() throws Exception{
    minimalHeap.insert(i1);
    assertEquals(1,minimalHeap.array.size());
  }

  @Test
  public void testSize_twoEl() throws Exception{
    minimalHeap.insert(i1);
    minimalHeap.insert(i2);
    assertEquals(2,minimalHeap.heapSize());
  }

  @Test
  public void testGet_oneEl() throws Exception{
    minimalHeap.insert(i1);
    assertTrue(i1==minimalHeap.array.get(0));
  }

  @Test
  public void testAdd_threeEl() throws Exception{

    Integer[] arrExpected = {i1,i2,i3};

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);

    assertArrayEquals(arrExpected,minimalHeap.array.toArray());
  }

  @Test
  public void testAdd_eightEl() throws Exception{

    Integer[] arrExpected = {i1,i2,i3,i6,i5,i7,i4,i8};

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);

    assertArrayEquals(arrExpected,minimalHeap.array.toArray());
  }

  @Test
  public void testLeftSon_fatherEl() throws Exception{

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);

    assertEquals(i2,minimalHeap.left(i1));
  }

  @Test
  public void testRightSon_fatherEl() throws Exception{

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);
    
    assertEquals(i3,minimalHeap.right(i1));
  }
  
  @Test
  public void testNotExistingRightSon_i5El() throws Exception{

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);
  
    assertEquals(i5,minimalHeap.right(i5));
   
  }

  @Test
  public void testNotExistingLeftSon_i5El() throws Exception{

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);
    
    assertEquals(i5,minimalHeap.left(i5));
  }

  @Test
  public void testAlreadyExisting_El() throws Exception{
    try {
      minimalHeap.insert(i1);
      minimalHeap.insert(i1);
      assertTrue(false);
    } catch(Exception e) {
      assertTrue(true);
    }
   
  }

  @Test
  public void testExtractMinimum_El() throws Exception{

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);
    
    assertEquals(i1,minimalHeap.extractMinimum());
  }

  @Test
  public void testArrayConsistencyAfterExtraction_El() throws Exception{

    Integer[] arrExpected = {i2,i5,i3,i6,i8,i7,i4};

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);

    minimalHeap.extractMinimum();

    assertArrayEquals(arrExpected,minimalHeap.array.toArray()); 
  }

  @Test
  public void testDecraseValue_El() throws Exception{

    Integer[] arrExpected = {i1,i2,i3,9,i5,i7,i4,i6};

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);

    minimalHeap.decreaseValue(i8, 9);

    assertArrayEquals(arrExpected,minimalHeap.array.toArray()); 
  }

  @Test
  public void testParent_El() throws Exception{

    minimalHeap.insert(i2);
    minimalHeap.insert(i1);
    minimalHeap.insert(i3);
    minimalHeap.insert(i8);
    minimalHeap.insert(i5);
    minimalHeap.insert(i7);
    minimalHeap.insert(i4);
    minimalHeap.insert(i6);

    assertEquals(i3,minimalHeap.parent(i7));
  }

  @Test
  public void testParentExeption_El() throws Exception{
    try{
      minimalHeap.insert(i2);
      minimalHeap.insert(i1);
      minimalHeap.insert(i3);
      minimalHeap.insert(i8);
      minimalHeap.insert(i5);
      minimalHeap.insert(i7);
      minimalHeap.insert(i4);
      minimalHeap.insert(i6);
      minimalHeap.parent(i1);
      assertTrue(false);
    }catch(Exception e){
      assertTrue(true);
    }
  }
}
