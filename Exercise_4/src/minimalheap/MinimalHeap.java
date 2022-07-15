package minimalheap;

import javax.swing.event.ListDataEvent;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

/**
 * It represents an array organized like a Minimal-Heap. Elements in the array respect a tree structure 
 * where the parent node is always smaller than the right and the left son. The order is also maintained
 * after the insertion of a new element.
 * @author Lavric & Venturini
 * @param G type of the Minimal-Heap array elements
 */
public class MinimalHeap<G>{

  Hashtable<G, Integer> map = null;
  ArrayList<G> array = null;
  Comparator<? super G> comparator = null;

  /**
   * constructor of MinimalHeap
   * @param comparator
   * @throws MinimalHeapException if comparator is null
   */
  public MinimalHeap(Comparator<? super G> comparator ) throws MinimalHeapException{
    if(comparator == null) throw new MinimalHeapException("minimalheap constructor: comparator parameter cannot be null");
    this.array = new ArrayList<G>();
    this.map = new Hashtable<>();
    this.comparator = comparator;
  }

  /**
  * @return true if this ordered array is empty
  */
  public boolean isEmpty(){
    return (this.array).isEmpty();
  }

  /**
  * @return: the number of elements currently stored in this heap.
  */
  public int heapSize(){
    return (this.array).size();
  }

  /**
   * insert new element into the MinimalHeap
   * @param x the element to be inserted 
   * @throws minimalheap.MinimalHeapException if the element we are trying to insert
   * already exists inside the Min-Heap
   */
  public void insert(G x) throws MinimalHeapException{
    if(this.map.get(x) == null){
      int i = this.heapSize();
      G tmp = null;
      (this.array).add(i, x);
      this.map.put(x, i);
      while(i > 0 && this.comparator.compare(this.parentIndex(i), x) > 0){
        exchange(parentIndex(i), array.get(i));
        tmp = parentIndex(i);
        i = this.map.get(tmp);
      }
    }
  }

 /**
   * return the parent element using the index
   * @param i index of the element whose father we want to find
   * @return the father of the element with index i
   */
  private G parentIndex(int i){
    return this.array.get((i - 1)/2);
  }
  
  /**
   * find the father of the element 
   * @param element whose father we want to find
   * @return the father of the element
   * @throws minimalheap.MinimalHeapException if the i is zero, so the element is root
   */
  public G parent(G element) throws MinimalHeapException{
    int i = this.getIndex(element);
    if(i == 0){
      throw new MinimalHeapException("The root node cannot have a parent");
    }else{
      return this.array.get((i-1)/2);
    }
  }

  /**
   * find the left child of element
   * @param element an hypothetical parent
   * @return the left son if exists, otherwise it returns parameter
   */
  public G left(G element){
    int i = this.getIndex(element);
    if(2*i+1 >= this.heapSize()){
      return this.array.get(i);
    }else{
      return this.array.get(2*i+1);
    }
  }

  
  /**
   * find the right child of element
   * @param element an hypothetical parent
   * @return the right son if exists, otherwise it returns parameter
   */
  public G right(G element){
    int i = this.getIndex(element);
    if(2*i+2 >= this.heapSize()){
      return this.array.get(i);
    }else{
      return this.array.get(2*i+2);
    }
  }

  /**
   * get method
   * @param element key of the hashmap
   * @return index of the element inside the Min-Heap
   */
  private int getIndex(G element){
    return this.map.get(element);
  }

  /**
   * the method replace the first parameter with the second one.
   * @param first 
   * @param second 
   */
  private void exchange(G first, G second){
    int x1 = getIndex(first);
    int x2 = getIndex(second);
    this.map.put(first, x2);
    this.map.put(second, x1);
    array.set(x1, second);
    array.set(x2, first);
  }

  /**
   * @return the root element of the Min-Heap
   */
  public G extractMinimum(){
    G ris = array.get(0);
    exchange(array.get(0), array.get(heapSize()-1));
    array.remove(heapSize()-1);
    map.remove(ris);

    int a = 0;
    if(heapSize() != 0){
      heapify(a);
    }
    
    return ris;
    
  }

  /**
   * reshap a binary tree into a heap
   * @param i index of the element on which heapify will be runned
   */
  private void heapify(int i){
    int smallest = minOf(array.get(i),left(array.get(i)),right(array.get(i)));
    if(smallest != i){
      exchange(this.array.get(i), this.array.get(smallest));

      heapify(smallest);
    }
  }

  /**
   * return the index of the smallest element netween head, left and right element
   * @param head,left,right element to compare
   * @return the index of the smallest element
   */
  private int minOf(G head, G left, G right){
    G tmp = head;
    if(this.comparator.compare(tmp, left) >= 0){
      tmp = left;
    }
    if(this.comparator.compare(tmp, right) >= 0){
      tmp = right;
    }

    return this.map.get(tmp);
  }

  /**
   * replaces the old element with the new smaller element
   * @param oldEl element to replace
   * @param newEl new element
   * @exception MinimalHeapException if the old element is smaller or equal than the new element
   */
  public void decreaseValue(G oldEl, G newEl)throws MinimalHeapException{
    if(comparator.compare(oldEl, newEl) < 0){
      throw new MinimalHeapException("the new value must be smaller than the old value");
    }
    int index = map.get(oldEl);
    this.map.remove(oldEl);
    this.map.put(newEl, index);
    this.array.set(index, newEl);
    while(index > 0 && this.comparator.compare(this.parentIndex(index), newEl) > 0){
      exchange(parentIndex(index), array.get(index));
      G tmp = parentIndex(index);
      index = this.map.get(tmp);
    }

  }
    
  /**
   * method to print all the Minimal Heap data structure
   */
  public void printHeap(){
    for (int i = 0; i < this.array.size();i++){ 		      
        System.out.print(this.array.get(i) + " | ");		
    } 
    System.out.println();
  }

  /**
   * get method
   * @param element key of map
   * @return the element into the array in the same position of the same element in the map
   */
  public G getElement(G element){
    int i = this.getIndex(element);
    return this.array.get(i);
  }
}