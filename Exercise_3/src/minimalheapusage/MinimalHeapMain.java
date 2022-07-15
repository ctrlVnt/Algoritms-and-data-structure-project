package minimalheapusage;

import java.io.IOException;
import java.util.Comparator;

import minimalheap.*;

import java.util.Scanner;

public class MinimalHeapMain {

  public static void printOptions(){
    System.out.println();
    System.out.println("Seleziona l'operazione da effettuare:");
    System.out.println("0 - stop your program");
    System.out.println("1 - inserire elementi");
    System.out.println("2 - visualizzare dimensioni heap");
    System.out.println("3 - visualizzare genitore elemento x");
    System.out.println("4 - visualizzazione figlio sinistro elemento x");
    System.out.println("5 - visualizzazione figlio destro elemento x");
    System.out.println("6 - estrazione elemento valore minimo");
    System.out.println("7 - diminuzione valore elemento x");
    System.out.println("8 - visualizzare heap");
  }

  private static void testWithComparisonFunctionInteger(Comparator<Integer> comparator) throws IOException, MinimalHeapException {
    MinimalHeap<Integer> heap = new MinimalHeap<>(comparator);
    Scanner scan = new Scanner(System.in);
    int choice;
    printOptions();
    choice = scan.nextInt();
    while(choice != 0){
      switch(choice){
        
        case 1:
        int number;
        int count;
        System.out.println("Quanti elementi vuoi inserire?");
        count = scan.nextInt();
        System.out.println("inserisci i valori:");
        for(int i = count; i > 0; i--){
          number = scan.nextInt();
          heap.insert(number);
        }
        printOptions();
        choice = scan.nextInt();
        break;

        case 2:
        System.out.println();
        System.out.println("lo heap contiene " + heap.heapSize() + " elementi");

        printOptions();
        choice = scan.nextInt();
        break;

        case 3:
        int elem;
        System.out.println("Di quale elemento vuoi vedere il padre?");
        elem = scan.nextInt();
        System.out.println();
        System.out.println("Il padre di " + elem + " e': " + heap.parent(elem));

        printOptions();
        choice = scan.nextInt();
        break;

        case 4:
        int left;
        System.out.println("Di quale elemento vuoi vedere il figlio sinistro?");
        left = scan.nextInt();
        System.out.println();
        System.out.println("Il figlio sinistro di " + left + " e': " + heap.left(left));

        printOptions();
        choice = scan.nextInt();
        break;

        case 5:
        int right;
        System.out.println("Di quale elemento vuoi vedere il figlio destro?");
        right = scan.nextInt();
        System.out.println();
        System.out.println("Il figlio destro di " + right + " e': " + heap.right(right));

        printOptions();
        choice = scan.nextInt();
        break;

        case 6:
        System.out.println();
        System.out.println("Estraggo il valore minimo: " + heap.extractMinimum());

        printOptions();
        choice = scan.nextInt();
        break;

        case 7:
        System.out.println("Inserisci l'elemento di cui vuoi sotrarre il valore con il valore che vorresti");
        int thisEl, newEl;
        thisEl = scan.nextInt();
        newEl = scan.nextInt();
        heap.decreaseValue(thisEl, newEl);
        System.out.println("Fatto!");

        printOptions();
        choice = scan.nextInt();
        break;

        case 8:
        System.out.println();
        heap.printHeap();

        printOptions();
        choice = scan.nextInt();
        break;

        default:
        throw new MinimalHeapException("Valore non valido, aborted");
      }
    }
    scan.close();
  }

  private static void testWithComparisonFunctionString(Comparator<String> comparator) throws IOException, MinimalHeapException {
    MinimalHeap<String> heap = new MinimalHeap<>(comparator);
    Scanner scan = new Scanner(System.in);
    int choice;
    printOptions();
    choice = scan.nextInt();
    while(choice != 0){
      switch(choice){
        
        case 1:
        String stringa;
        int count;
        System.out.println("Quanti elementi vuoi inserire?");
        count = scan.nextInt();
        System.out.println("inserisci i valori:");
        scan.nextLine();
        for(int i = count; i > 0; i--){
          stringa = scan.nextLine();
          heap.insert(stringa);
        }
        printOptions();
        choice = scan.nextInt();
        break;

        case 2:
        System.out.println();
        System.out.println("lo heap contiene " + heap.heapSize() + " elementi");

        printOptions();
        choice = scan.nextInt();
        break;

        case 3:
        String elem;
        System.out.println("Di quale elemento vuoi vedere il padre?");
        scan.nextLine();
        elem = scan.nextLine();
        System.out.println();
        System.out.println("Il padre di " + elem + " e': " + heap.parent(elem));

        printOptions();
        choice = scan.nextInt();
        break;

        case 4:
        String left;
        System.out.println("Di quale elemento vuoi vedere il figlio sinistro?");
        scan.nextLine();
        left = scan.nextLine();
        System.out.println();
        System.out.println("Il figlio sinistro di " + left + " e': " + heap.left(left));

        printOptions();
        choice = scan.nextInt();
        break;

        case 5:
        String right;
        System.out.println("Di quale elemento vuoi vedere il figlio destro?");
        scan.nextLine();
        right = scan.nextLine();
        System.out.println();
        System.out.println("Il figlio destro di " + right + " e': " + heap.right(right));

        printOptions();
        choice = scan.nextInt();
        break;

        case 6:
        System.out.println();
        System.out.println("Estraggo il valore minimo: " + heap.extractMinimum());

        printOptions();
        choice = scan.nextInt();
        break;

        case 7:
        System.out.println("Inserisci l'elemento di cui vuoi sotrarre il valore con il valore che vorresti");
        String thisEl, newEl;
        scan.nextLine();
        thisEl = scan.nextLine();
        newEl = scan.nextLine();
        heap.decreaseValue(thisEl, newEl);
        System.out.println("Fatto!");

        printOptions();
        choice = scan.nextInt();
        break;

        case 8:
        System.out.println();
        heap.printHeap();

        printOptions();
        choice = scan.nextInt();
        break;

        default:
        throw new MinimalHeapException("Valore non valido, aborted");
      }
    }
    scan.close();
  }

  private static void testWithComparisonFunctionDouble(Comparator<Double> comparator) throws IOException, MinimalHeapException {
    MinimalHeap<Double> heap = new MinimalHeap<>(comparator);
    Scanner scan = new Scanner(System.in);
    int choice;
    printOptions();
    choice = scan.nextInt();
    while(choice != 0){
      switch(choice){
        
        case 1:
        Double number;
        int count;
        System.out.println("Quanti elementi vuoi inserire?");
        count = scan.nextInt();
        System.out.println("inserisci i valori:");
        for(int i = count; i > 0; i--){
          number = scan.nextDouble();
          heap.insert(number);
        }
        printOptions();
        choice = scan.nextInt();
        break;

        case 2:
        System.out.println();
        System.out.println("lo heap contiene " + heap.heapSize() + " elementi");

        printOptions();
        choice = scan.nextInt();
        break;

        case 3:
        Double elem;
        System.out.println("Di quale elemento vuoi vedere il padre?");
        elem = scan.nextDouble();
        System.out.println();
        System.out.println("Il padre di " + elem + " e': " + heap.parent(elem));

        printOptions();
        choice = scan.nextInt();
        break;

        case 4:
        Double left;
        System.out.println("Di quale elemento vuoi vedere il figlio sinistro?");
        left = scan.nextDouble();
        System.out.println();
        System.out.println("Il figlio sinistro di " + left + " e': " + heap.left(left));

        printOptions();
        choice = scan.nextInt();
        break;

        case 5:
        Double right;
        System.out.println("Di quale elemento vuoi vedere il figlio destro?");
        right = scan.nextDouble();
        System.out.println();
        System.out.println("Il figlio destro di " + right + " e': " + heap.right(right));

        printOptions();
        choice = scan.nextInt();
        break;

        case 6:
        System.out.println();
        System.out.println("Estraggo il valore minimo: " + heap.extractMinimum());

        printOptions();
        choice = scan.nextInt();
        break;

        case 7:
        System.out.println("Inserisci l'elemento di cui vuoi sotrarre il valore con il valore che vorresti");
        Double thisEl, newEl;
        thisEl = scan.nextDouble();
        newEl = scan.nextDouble();
        heap.decreaseValue(thisEl, newEl);
        System.out.println("Fatto!");

        printOptions();
        choice = scan.nextInt();
        break;

        case 8:
        System.out.println();
        heap.printHeap();

        printOptions();
        
        choice = scan.nextInt();
        break;

        default:
        throw new MinimalHeapException("Valore non valido, aborted");
      }
    }
    scan.close();
  }

  public static void main(String[] args) throws IOException, MinimalHeapException, Exception {
    System.out.println("START MAIN:");
    Scanner scan = new Scanner(System.in);

    System.out.println("Con cosa lavorare:");
    System.out.println("1 - Interi");
    System.out.println("2 - Stringhe");
    System.out.println("3 - Double");

    int choice = scan.nextInt();
    while(choice > 3 || choice < 1){
      System.out.println("VALORE NON VALIDO, scegliere un numero da 1 a 3:");
      System.out.println("1 - Interi");
      System.out.println("2 - Stringhe");
      System.out.println("3 - Double");
      choice = scan.nextInt();
    }
    switch(choice){
      case 1:
      testWithComparisonFunctionInteger(new IntegerComparator());
      break;

      case 2:
      testWithComparisonFunctionString(new StringComparator());
      break;

      case 3:
      testWithComparisonFunctionDouble(new DoubleComparator());
      break;
    }
    scan.close();
  }
}
