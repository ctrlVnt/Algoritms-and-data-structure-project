package minimalheapusage;

import java.util.Comparator;
/**
 * comparator for Double type
 * @author Venturini & Lavric 
 */
public class DoubleComparator implements Comparator<Double>{
@Override
  public int compare(Double r1, Double r2) {
    int result = Double.compare(r1, r2);
    return result;
  }
}
