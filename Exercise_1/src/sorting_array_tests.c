#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "sorting_array.h"

/*
 * Test suite for sorting array data structure and algorithms
 */

//precedence relation used in tests
static int precedes_int(void* i1_p,void* i2_p){
  int* int1_p = (int*)i1_p;
  int* int2_p = (int*)i2_p;
  if((*int1_p) < (*int2_p))
    return(1);
  return(0);
}



//Data elements that are initialized before each test
static int i1,i2,i3,i4,i5,i6,i7,i8,i9,i10;
static SortedArray *sorted_array_int;

void setUp(void){
  i1 = 0;
  i2 = 1;
  i3 = 2;
  i4 = 3;
  i5 = 4;
  i6 = 5;
  i7 = 6;
  i8 = 7;
  i9 = 8;
  i10 = 9;

  sorted_array_int = array_create(precedes_int);
}


void tearDown(void){
  array_free_memory(sorted_array_int);
}


static void test_array_is_empty_zero_el(void){
  TEST_ASSERT_TRUE(array_is_empty(sorted_array_int));
}


static void test_array_is_empty_one_el(void){
 array_add(sorted_array_int,&i1);
 TEST_ASSERT_FALSE(array_is_empty(sorted_array_int));
}
  
  
static void test_array_size_zero_el(void){
  TEST_ASSERT_EQUAL_INT(0,array_size(sorted_array_int));
}

  
static void test_array_size_one_el(void){
  array_add(sorted_array_int,&i1);
  TEST_ASSERT_EQUAL_INT(1,array_size(sorted_array_int));
}

  
static void test_array_size_two_el(void){
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i2);
  TEST_ASSERT_EQUAL_INT(2,array_size(sorted_array_int));
}

  
static void test_array_add_get_one_el(void){
  array_add(sorted_array_int,&i1);
  TEST_ASSERT_EQUAL_PTR(&i1,array_get(sorted_array_int,0));
}

//Tests on quick sort algorithm

static void test_array_empty_quick_sorting(void){
  quick_sort(sorted_array_int, 0, 0, 0);
  TEST_ASSERT_TRUE(array_is_empty(sorted_array_int));
}



static void test_array_one_el_quick_sorting(void){
  int* exp_arr[] = {&i5};
  array_add(sorted_array_int,&i5);
  quick_sort(sorted_array_int, 0, 0, 0);
  int** act_arr = malloc(1*sizeof(int*));
  for(unsigned long i=0;i<1;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,1);
}
  
  
static void test_array_all_equal_quick_sorting(void){
  int* exp_arr[] = {&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3};
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);

  quick_sort(sorted_array_int, 0, 9, 0);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

  
static void test_array_quick_sorting_last(void){
  int* exp_arr[] = {&i1,&i2,&i3,&i4,&i5,&i6,&i7,&i8,&i9,&i10};
  array_add(sorted_array_int,&i4);
  array_add(sorted_array_int,&i9);
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i8);
  array_add(sorted_array_int,&i5);
  array_add(sorted_array_int,&i2);
  array_add(sorted_array_int,&i7);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i6);
  array_add(sorted_array_int,&i10);

  quick_sort(sorted_array_int, 0, 9, 0);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

static void test_array_quick_sorting_first(void){
  int* exp_arr[] = {&i1,&i2,&i3,&i4,&i5,&i6,&i7,&i8,&i9,&i10};
  array_add(sorted_array_int,&i4);
  array_add(sorted_array_int,&i9);
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i8);
  array_add(sorted_array_int,&i5);
  array_add(sorted_array_int,&i2);
  array_add(sorted_array_int,&i7);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i6);
  array_add(sorted_array_int,&i10);

  quick_sort(sorted_array_int, 0, 9, 1);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

static void test_array_quick_sorting_half(void){
  int* exp_arr[] = {&i1,&i2,&i3,&i4,&i5,&i6,&i7,&i8,&i9,&i10};
  array_add(sorted_array_int,&i4);
  array_add(sorted_array_int,&i9);
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i8);
  array_add(sorted_array_int,&i5);
  array_add(sorted_array_int,&i2);
  array_add(sorted_array_int,&i7);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i6);
  array_add(sorted_array_int,&i10);

  quick_sort(sorted_array_int, 0, 9, 2);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

static void test_array_quick_sorting_random(void){
  int* exp_arr[] = {&i1,&i2,&i3,&i4,&i5,&i6,&i7,&i8,&i9,&i10};
  array_add(sorted_array_int,&i4);
  array_add(sorted_array_int,&i9);
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i8);
  array_add(sorted_array_int,&i5);
  array_add(sorted_array_int,&i2);
  array_add(sorted_array_int,&i7);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i6);
  array_add(sorted_array_int,&i10);

  quick_sort(sorted_array_int, 0, 9, 3);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

//Tests on insertion sort algorithm

static void test_array_empty_insertion_sorting(void){
  insertion_sort(sorted_array_int);
  TEST_ASSERT_TRUE(array_is_empty(sorted_array_int));
}

static void test_array_one_el_insertion_sorting(void){
  int* exp_arr[] = {&i5};
  array_add(sorted_array_int,&i5);
  insertion_sort(sorted_array_int);
  int** act_arr = malloc(1*sizeof(int*));
  for(unsigned long i=0;i<1;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,1);
}

static void test_array_all_equal_insertion_sorting(void){
    int* exp_arr[] = {&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3};
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);

  insertion_sort(sorted_array_int);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

static void test_array_insertion_sorting(void){
  int* exp_arr[] = {&i1,&i2,&i3,&i4,&i5,&i6,&i7,&i8,&i9,&i10};
  array_add(sorted_array_int,&i4);
  array_add(sorted_array_int,&i9);
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i8);
  array_add(sorted_array_int,&i5);
  array_add(sorted_array_int,&i2);
  array_add(sorted_array_int,&i7);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i6);
  array_add(sorted_array_int,&i10);

  insertion_sort(sorted_array_int);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

//Tests on binary insertion sort algorithm

static void test_array_empty_binary_insertion_sorting(void){
  binary_insertion_sort(sorted_array_int);
  TEST_ASSERT_TRUE(array_is_empty(sorted_array_int));
}

static void test_array_one_el_binary_insertion_sorting(void){
  int* exp_arr[] = {&i5};
  array_add(sorted_array_int,&i5);
  binary_insertion_sort(sorted_array_int);
  int** act_arr = malloc(1*sizeof(int*));
  for(unsigned long i=0;i<1;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,1);
}

static void test_array_all_equal_binary_insertion_sorting(void){
    int* exp_arr[] = {&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3,&i3};
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i3);

  binary_insertion_sort(sorted_array_int);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

static void test_array_binary_insertion_sorting(void){
  int* exp_arr[] = {&i1,&i2,&i3,&i4,&i5,&i6,&i7,&i8,&i9,&i10};
  array_add(sorted_array_int,&i4);
  array_add(sorted_array_int,&i9);
  array_add(sorted_array_int,&i1);
  array_add(sorted_array_int,&i8);
  array_add(sorted_array_int,&i5);
  array_add(sorted_array_int,&i2);
  array_add(sorted_array_int,&i7);
  array_add(sorted_array_int,&i3);
  array_add(sorted_array_int,&i6);
  array_add(sorted_array_int,&i10);

  binary_insertion_sort(sorted_array_int);
  int** act_arr = malloc(10*sizeof(int*));
  for(unsigned long i=0;i<10;i++){
    act_arr[i] = (int*)array_get(sorted_array_int,i);
  }
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

int main(void) {

  //test session
  UNITY_BEGIN();
  
  RUN_TEST(test_array_is_empty_zero_el);
  RUN_TEST(test_array_is_empty_one_el);
  RUN_TEST(test_array_size_zero_el);
  RUN_TEST(test_array_size_one_el);
  RUN_TEST(test_array_size_two_el);
  RUN_TEST(test_array_add_get_one_el);
  RUN_TEST(test_array_empty_quick_sorting);
  RUN_TEST(test_array_one_el_quick_sorting);
  RUN_TEST(test_array_all_equal_quick_sorting);
  RUN_TEST(test_array_quick_sorting_last);
  RUN_TEST(test_array_quick_sorting_first);
  RUN_TEST(test_array_quick_sorting_half);
  RUN_TEST(test_array_quick_sorting_random);

  RUN_TEST(test_array_empty_insertion_sorting);
  RUN_TEST(test_array_one_el_insertion_sorting);
  RUN_TEST(test_array_all_equal_insertion_sorting);
  RUN_TEST(test_array_insertion_sorting);

  RUN_TEST(test_array_empty_binary_insertion_sorting);
  RUN_TEST(test_array_one_el_binary_insertion_sorting);
  RUN_TEST(test_array_all_equal_binary_insertion_sorting);
  RUN_TEST(test_array_binary_insertion_sorting);

  return UNITY_END();
}

