#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "skip_list.h"

/*
 * Test suite for skip list data structure and algorithms
 */

//precedence relation used in tests
static int precedes_int(void* i1_p,void* i2_p){
  char* int1_p = (char*)i1_p;
  char* int2_p = (char*)i2_p;
  if(strcasecmp(int1_p,int2_p) < 0)
    return(1);
  return(0);
}

//simple counter of the elements of a list
static int count_elements(SkipList *list_created){
  int count  = 0;
    if(list_is_empty(list_created) != 1){
      Node *tmp = list_created->head->next[0];
      while(tmp != NULL){
        count++;
        tmp = tmp->next[0];
      }
    }
  return count;
}

//function that copies all the elements of a list inside an array
//to control if the order is respected by the insertion function 
static char **copy_list_into_array(SkipList *list_created){
  int size = count_elements(list_created);
  if(size == 0)
    return NULL;
  
  char **array = calloc(size,sizeof(char *));
  Node *tmp = list_created->head->next[0];
  int i = 0;
  while(tmp != NULL){
    array[i] = tmp->item;
    tmp = tmp->next[0];
    i++;
  }
  return array;
}

//Data elements that are initialized before each test
static char * i1,*i2,*i3,*i4,*i5,*i6,*i7,*i8,*i9,*i10;
static SkipList *list_created;

void setUp(void){
    i1 = "a";
    i2 = "b";
    i3 = "c";
    i4 = "d";
    i5 = "e";
    i6 = "f";
    i7 = "g";
    i8 = "h";
    i9 = "i";
    i10 = "j";
    list_created = create_skip_list(precedes_int);
}

void tearDown(void){
  delete_skip_list(list_created);
}

static void test_list_is_empty_zero_el(void){
  TEST_ASSERT_TRUE(list_is_empty(list_created));
}

static void test_list_is_empty_one_el(void){
  insert_skip_list(list_created, i1);
  TEST_ASSERT_FALSE(list_is_empty(list_created))
}

static void test_list_insert_one_search_el(void){
  insert_skip_list(list_created, i1);
  TEST_ASSERT_EQUAL_PTR_ARRAY(i1,(char *)search_skip_list(list_created, i1),1);
}

//verify it the second insertion doesn't overwrite the first one
static void test_list_insert_two_search_el_first(void){
  insert_skip_list(list_created, i1);
  insert_skip_list(list_created, i2);
  TEST_ASSERT_EQUAL_PTR_ARRAY(i1,(char *)search_skip_list(list_created, i1),1);
}

//verify it the second element is correctly inserted
static void test_list_insert_two_search_el_second(void){
  insert_skip_list(list_created, i1);
  insert_skip_list(list_created, i2);
  TEST_ASSERT_EQUAL_PTR_ARRAY(i2,(char *)search_skip_list(list_created, i2),1);
}

static void test_skip_list_elements_order(void){
  char* exp_arr[] = {i1,i2,i3,i4,i5,i6,i7,i8,i9,i10};
  insert_skip_list(list_created, i1);
  insert_skip_list(list_created, i2);
  insert_skip_list(list_created, i3);
  insert_skip_list(list_created, i4);
  insert_skip_list(list_created, i5);
  insert_skip_list(list_created, i6);
  insert_skip_list(list_created, i7);
  insert_skip_list(list_created, i8);
  insert_skip_list(list_created, i9);
  insert_skip_list(list_created, i10);

  char **act_arr = copy_list_into_array(list_created);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

static void test_skip_list_elements_order_2(void){
  char* exp_arr[] = {i1,i2,i3,i4,i5,i6,i7,i8,i9,i10};
  insert_skip_list(list_created, i9);
  insert_skip_list(list_created, i1);
  insert_skip_list(list_created, i10);
  insert_skip_list(list_created, i2);
  insert_skip_list(list_created, i7);
  insert_skip_list(list_created, i5);
  insert_skip_list(list_created, i3);
  insert_skip_list(list_created, i4);
  insert_skip_list(list_created, i8);
  insert_skip_list(list_created, i6);

  char **act_arr = copy_list_into_array(list_created);
  TEST_ASSERT_EQUAL_PTR_ARRAY(exp_arr,act_arr,10);
}

int main(void){
    //test session
  UNITY_BEGIN();
  
  RUN_TEST(test_list_is_empty_zero_el);
  RUN_TEST(test_list_is_empty_one_el);
  RUN_TEST(test_list_insert_one_search_el);
  RUN_TEST(test_list_insert_two_search_el_first);
  RUN_TEST(test_list_insert_two_search_el_second);
  RUN_TEST(test_skip_list_elements_order);
  RUN_TEST(test_skip_list_elements_order_2);

  return UNITY_END();
}