package minimalheapusage;

import java.util.Comparator;
/**
 * comparator for Integer type
 * @author Venturini & Lavric 
 */
public class IntegerComparator implements Comparator<Integer>{
@Override
  public int compare(Integer r1, Integer r2) {
    int result = Integer.valueOf(r1).compareTo(r2);
    return result;
  }
}
