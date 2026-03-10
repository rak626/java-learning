package LLD.behavioural.Strategy;


// https://www.geeksforgeeks.org/system-design/strategy-pattern-set-1/
public class Client {
    public static void main(String[] args) {
        // Create SortingContext with BubbleSortStrategy
        SortingContext sortingContext = new SortingContext(new BubbleSortStrategy());
        int[] array1 = {1, 5, 22, 3, 234, 33, 25};
        sortingContext.performSort(array1); // Output: Sorting using Bubble Sort

        // Change strategy to MergeSortStrategy
        sortingContext.setSortingStrategy(new MergeSortStrategy());
        int[] array2 = {523, 223, 221, 3352, 23, 55, 2, 5};
        sortingContext.performSort(array2); // Output: Sorting using Merge Sort

        // Change strategy to QuickSortStrategy
        sortingContext.setSortingStrategy(new QuickSortStrategy());
        int[] array3 = {43, 12, 11, 455, 23, 11};
        sortingContext.performSort(array3); // Output: Sorting using Quick Sort
    }

    /*
    * Advantages:
        This pattern improves flexibility and maintainability by separating behavior from the main logic.
        Promotes open/closed principle by allowing new strategies to be added easily
        Makes code cleaner and easier to maintain
        Enables runtime behavior changes without code modification

      Disadvantages:
        While flexible, the pattern can introduce extra complexity.
        Increases the number of classes in the system
        Clients must be aware of different strategies
        May add slight overhead due to additional object creation
    * */
}
