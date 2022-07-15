#ifndef SORTED_ARRAY_H
#define SORTED_ARRAY_H

//An array of any number of elements of any kind, in non descending order
//according to a specific precedence relation.
typedef struct _SortedArray SortedArray;

//It creates an empty array and returns the created array.
//It accepts as input a pointer to a function implementing the 
//precedence relation between the array elements. 
//Such a function must accept as input two pointers to elements and
//return 0 iff the first element does not precede the second one and
//a number different from zero otherwise.
//The input parameter cannot be NULL.
SortedArray *array_create(int (*precedes)(void*,void*));

//It accepts as input a pointer to an array and it returns 1 if
//the array is empty (0 otherwise).
//The input parameter cannot be NULL.
int array_is_empty(SortedArray *);

//It accepts as input a pointer to an array and it
//returns the number of elements currently stored into the array.
//The input parameter cannot be NULL.
unsigned long array_size(SortedArray *);

//It accepts as input a pointer to an array and a pointer to an element. It adds
//the element to the array in the last position. 
//The input parameters cannot be NULL.
void array_add(SortedArray *, void*);

//It accepts as input a pointer to an array and an integer "i" and
//it returns the pointer to the i-th element of the array
//The first parameter cannot be NULL; the second parameter must be a valid position 
//within the ordered array.
void* array_get(SortedArray *, unsigned long);

//It accepts as input a pointer to an array and 
//it frees the memory allocated to store the array. 
//It does not free the memory allocated to store the array elements.
//The input parameters cannot be NULL.
void array_free_memory(SortedArray *);

//Implementation of the Quicksort algorithm
//This function sort the array with the support of the PARTITION function
//The algorithm expects three parameters as input (A, p , r)
//A is the array, p the first element of the array and r the index of the pivot 
//In this implementation the index of the first element is implied, 
//A = sorted_array, p = first, r = last
//pivot_choice: 0 = last, 1 = first, 2 = half, 3 (and other numbers) = random
void quick_sort (SortedArray *sorted_array,int first, int last, int pivot_choice);

//PARTITION procedure used by the Quicksort algorithm
//This procedure is the main part of the algorithm and its task is to rearrange 
//the subarray A(first...last) in place.
//It returns the index where the pivot has been inserted
int partition(SortedArray *sorted_array, int first, int last);

//Insertion sort is a sorting algorithm that places
// an unsorted element at its suitable place in each iteration.
void insertion_sort(SortedArray *sorted_array);

//sorting algorithm similar to insertion sort, but instead of using 
//linear search to find the position where the element should be inserted, 
//we use binary search
void binary_insertion_sort(SortedArray *sorted_array);

//BINARY SEARCH procedure used by the Binary insertion sort algorithm
//to find the suitable place of an element.
//It returns the index where the element will be inserted
int binary_search(SortedArray *sorted_array, void* key,int low, int high);

#endif

