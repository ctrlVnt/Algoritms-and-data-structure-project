#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include "sorting_array.h"

#define INITIAL_CAPACITY 2

static int randomized_partition(SortedArray *sorted_array, int first, int last, int pivot_choice);

struct _SortedArray{
  void** array;
  unsigned long el_num;
  unsigned long array_capacity;
  int (*precedes)(void*,void*);
};

SortedArray *array_create(int (*precedes)(void*,void*)){
  if(precedes == NULL){
    fprintf(stderr,"array_create: precedes parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  SortedArray *sorted_array = (SortedArray *)malloc(sizeof(SortedArray));
  if(sorted_array == NULL){
    fprintf(stderr, "array_create: unable to allocate memory for the array");
    exit(EXIT_FAILURE);
  }
  sorted_array->array = (void**)malloc(INITIAL_CAPACITY*sizeof(void*));
  if(sorted_array->array == NULL){
    fprintf(stderr, "array_create: unable to allocate memory for the internal array");
    exit(EXIT_FAILURE);
  }
  sorted_array->el_num = 0;
  sorted_array->array_capacity =INITIAL_CAPACITY;
  sorted_array->precedes = precedes;
  
  return(sorted_array);
}

int array_is_empty(SortedArray *sorted_array){
  if(sorted_array == NULL){
    fprintf(stderr,"array_is_empty: sorted_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(sorted_array->el_num == 0)
    return(1);
  return(0);
}


unsigned long array_size(SortedArray *sorted_array){
  if(sorted_array == NULL){
    fprintf(stderr,"array_size: sorted_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  return(sorted_array->el_num);
}


void array_add(SortedArray *sorted_array, void* element){
  if(sorted_array == NULL){
    fprintf(stderr,"add_array_element: sorted_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(element == NULL){
    fprintf(stderr,"add_array_element: element parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  
  if(sorted_array->el_num >= sorted_array->array_capacity){
    sorted_array->array = (void**)realloc(sorted_array->array,2*(sorted_array->array_capacity)*sizeof(void*));
    if(sorted_array->array == NULL){
      fprintf(stderr,"array_add: unable to reallocate memory to host the new element");
      exit(EXIT_FAILURE);
    }
    sorted_array->array_capacity = 2 * sorted_array->array_capacity;
  }
  
  unsigned long index = sorted_array->el_num;
  (sorted_array->array)[index] = element;
  (sorted_array->el_num)++;
  
}


void* array_get(SortedArray *sorted_array, unsigned long i){
  if(sorted_array == NULL){
    fprintf(stderr,"array_get: sorted_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(i>=sorted_array->el_num){
    fprintf(stderr,"array_get: Index %lu is out of the array bounds",i);
    exit(EXIT_FAILURE);
  }
  return(sorted_array->array)[i];
}


void array_free_memory(SortedArray *sorted_array){
  if(sorted_array == NULL){
    fprintf(stderr,"array_free_memory: sorted_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  free(sorted_array->array);
  free(sorted_array);
}


void quick_sort(SortedArray *sorted_array, int first, int last, int pivot_choice){  
    if(first < last){
        int partition_result = randomized_partition(sorted_array, first, last, pivot_choice);

        quick_sort(sorted_array, first, partition_result-1, pivot_choice);
        quick_sort(sorted_array, partition_result + 1, last, pivot_choice);
    }
}


int partition(SortedArray *sorted_array, int first, int last){
    void* tmp = NULL;
    void* pivot = sorted_array->array[last];
    int i = first - 1;

    for(int j = first; j < last; j++){
        if((*(sorted_array->precedes))(sorted_array->array[j],pivot)){
            i++;
            tmp = sorted_array->array[j];
            sorted_array->array[j] = sorted_array->array[i];
            sorted_array->array[i] = tmp;
        }
    }
    tmp = sorted_array->array[last];
    sorted_array->array[last] = sorted_array->array[i+1];
    sorted_array->array[i+1] = tmp;

    return i+1;
}


static int randomized_partition(SortedArray *sorted_array, int first, int last, int pivot_choice){
  int i;
  void* tmp = NULL;
  switch (pivot_choice)
  {
  case 0:
      i = last;
      break;
  
  case 1:
      i = first;
      break;

  case 2:
      i = (last+first) / 2;
      break;
  
  default:
      srand(getpid());
      i = (rand() % (last - first + 1)) + first;
      break;
  }
  tmp = sorted_array->array[last];
  sorted_array->array[last] = sorted_array->array[i];
  sorted_array->array[i] = tmp;
  return partition(sorted_array, first, last);
}


void insertion_sort(SortedArray *sorted_array){
  void* key = NULL;
  int i;

  for(int j = 1; j < sorted_array->el_num; j++){
    key = sorted_array->array[j];
    i = j-1;
    while(i >= 0 && (*(sorted_array->precedes))(key,sorted_array->array[i])){     
      sorted_array->array[i+1] = sorted_array->array[i];
      i = i-1;
    }
    sorted_array->array[i+1] = key;
  }
}


void binary_insertion_sort(SortedArray *sorted_array){
  void* key = NULL;
  int j,k;

  for(int i = 1; i < sorted_array->el_num; i++){
    key = sorted_array->array[i];
    j = binary_search(sorted_array, sorted_array->array[i],0, i);
    k = i-1;

    while(k >= j){
      sorted_array->array[k+1] = sorted_array->array[k];
      k = k-1;
    }
    sorted_array->array[k+1] = key;
  }
}


int binary_search(SortedArray *sorted_array, void* key, int low, int high){
  int mid;

  if(high <= low){
    if((*(sorted_array->precedes))(key,sorted_array->array[low]) == 0){
      return low+1;

    }else{
      return low;
    }

  }else{
    mid = (low+high)/2;
    if((*(sorted_array->precedes))(key,sorted_array->array[mid]) == 0 && 
            (*(sorted_array->precedes))(sorted_array->array[mid], key) == 0){
      return mid+1;

    }else{
      if((*(sorted_array->precedes))(key,sorted_array->array[mid]) == 0){
        return binary_search(sorted_array, key, mid+1, high);

      }else{
        return binary_search(sorted_array, key, low, mid-1);
      }
    }
  }
}


















