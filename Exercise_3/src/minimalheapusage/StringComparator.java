package minimalheapusage;

import java.util.Comparator;
/**
 * comparator for String type
 * @author Venturini & Lavric 
 */
public class StringComparator implements Comparator<String>{
  @Override
  public int compare(String r1, String r2) {
    int result = (String.CASE_INSENSITIVE_ORDER).compare(r1, r2);
    return result;
  }
}
