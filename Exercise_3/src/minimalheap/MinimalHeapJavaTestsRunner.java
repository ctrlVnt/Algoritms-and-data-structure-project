package minimalheap;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * main class for test MinimalHeap methods
 * @author Venturini & Lavric
 */
public class MinimalHeapJavaTestsRunner {
  
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(MinimalHeapTests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  
    System.out.println(result.wasSuccessful());
    
  }
  
}
