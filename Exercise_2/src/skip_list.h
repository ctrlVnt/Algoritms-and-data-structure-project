#define MAX_HEIGHT 10
#define FAILURE "failure"
#define INITIAL_CAPACITY 0

typedef struct _SkipList SkipList;

typedef struct _Node Node;

//Structure of the skiplist.
//It has a pointer to the head of the list, represented by an empty sentinel node
struct _SkipList{
  Node *head;
  unsigned int max_level;
  int (*compare)(void *, void *);
};

//Structure of the node.
//It contains an array of pointers to a specified number of nodes.
//This number, saved in size, is calculated by a random function.
struct _Node{
  Node **next;
  unsigned int size;
  void *item;
};

//Insert new node with item in the correct position into the list l
//The parameters are the skiplist and the item to insert
void insert_skip_list (SkipList* l, void* item);

//Check if into the skip list exist item and return it, otherwhise return -1;
//The parameters are the skiplist and the item to delete
void *search_skip_list(SkipList* l, void* item);

//Create and allocate memory for new skiplist empty
//It also creates the sentinel node
//The only parameter is the pointer to the comparator function
SkipList *create_skip_list(int (*compare)(void*,void*));

//Delete and deallocates memory of skip list
//The only parameter is the pointer to the skiplist to delete
void delete_skip_list(SkipList *);

//It accepts as input a pointer to an list and it returns 1 if
//the list is empty (0 otherwise).
//The input parameter cannot be NULL.
int list_is_empty(SkipList*);