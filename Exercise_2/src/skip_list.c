#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include "skip_list.h"


  SkipList *create_skip_list(int (*compare)(void *, void *)){
  if (compare == NULL){
    fprintf(stderr, "array_create: precedes parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  SkipList *skip_list = (SkipList *)malloc(sizeof(SkipList));
  if (skip_list == NULL){
    fprintf(stderr, "array_create: unable to allocate memory for the array");
    exit(EXIT_FAILURE);
  }

  srand(time(NULL));

  skip_list->head = (Node *)malloc(sizeof(Node));
  if (skip_list->head == NULL){
    fprintf(stderr, "array_create: unable to allocate memory for the internal array");
    exit(EXIT_FAILURE);
  }
  skip_list->head->item = NULL;
  skip_list->head->size = MAX_HEIGHT;
  skip_list->head->next = (Node **)calloc(MAX_HEIGHT, sizeof(Node *));

  skip_list->max_level = INITIAL_CAPACITY;
  skip_list->compare = compare;

  return (skip_list);
}

int list_is_empty(SkipList *list){
  if (list->head == NULL || list->head->next[0]== NULL){
    return (1);
  }else{
    return (0);
  }
}

static int random_level(){
  int lvl = 1;
  double div = RAND_MAX / (1.0 - 0.0);
  while ((0 + (rand() / div)) < 0.5 && lvl < MAX_HEIGHT){
    lvl = lvl + 1;
  }
  return lvl;
}

static Node *create_node(void *item, int n){
  Node *new = (Node *)malloc(sizeof(Node));
  new->next = (Node **)calloc(n, sizeof(Node *));
  new->size = n;
  new->item = item;
  for(int i = 0; i < n; i++){
    new->next[i] = NULL;
  }
  return new;
}

void insert_skip_list(SkipList *list, void *item){
  Node *new = create_node(item, random_level());
  if (new->size > list->max_level){
    list->max_level = new->size;
  }
  Node *x = list->head;
  for (int k = (list->max_level-1); k >= 0; k--){
    if (x->next[k] == NULL || (*(list->compare))(item, x->next[k]->item)){
      if (k < new->size){
        new->next[k] = x->next[k];
        x->next[k] = new;
      }
    }else{
      x = x->next[k];
      k = k + 1;
    }
  }
}

void *search_skip_list(SkipList *list, void *item){
  Node *x = list->head;
  for (int i = (list->max_level-1); i >= 0; i--){
    while ((x->next[i] != NULL) ? ((*(list->compare))(x->next[i]->item, item)) : 0){
      x = x->next[i];
    }
  }
  x = x->next[0];
  char *r1 = (char *)(x->item);
  char *r2 = (char *)(item);
  if (strcasecmp(r1,r2) == 0){
    return x->item;
  }else{
    return (void *)FAILURE;
  }
}

void delete_skip_list(SkipList *list){
  while (list->head != NULL){
    Node *prec = list->head;
    list->head = list->head->next[0];
    free(prec->next);
    free(prec);
  }
  free(list);
  list = NULL;
}