#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "sorting_array.h"

#define TEST 0


int lenght;

struct Record{
    int id_field;
    char* string_field;
    int int_field;
    double float_field;
};

//It takes as input two pointers to struct record.
//It returns 1 if the integer field of the first record is less than 
//the integer field of the second one (otherwise it compares the others fields)
//if the integer field of the second record is greater than the integer field
//of the first one, than it returns 0.
//The comparison between the strings fields is not case sensitive
static int precedes_record_int_field(void* r1,void* r2){
  if(r1 == NULL){
    fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2 == NULL){
    fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct Record *rec1 = (struct Record*)r1;
  struct Record *rec2 = (struct Record*)r2;
  
  if(rec1->int_field < rec2->int_field){
      return(1);
  }else if(rec1->int_field == rec2->int_field){
      if(rec1->float_field < rec2->float_field){
          return(1);
      }else if(rec1->float_field == rec2->float_field){
          if(strcasecmp(rec1->string_field, rec2->string_field) < 0){
              return(1);
          }
      }
  }
  return(0);
}

static  void free_array(SortedArray* array) {
  unsigned long  el_num =  array_size(array);
  for(unsigned long i=0;i<el_num;i++){
    struct Record *array_element = (struct Record *)array_get(array, i);
    free(array_element->string_field);
    free(array_element);
  }
  array_free_memory(array);
}

//It takes as input two pointers to struct record.
//It returns 1 iff the float field of the first record is less than 
//the float field of the second one (otherwise it compares the others fields)
//if the float field of the second record is greater than the float field
//of the first one, than it returns 0.
//The comparison between the strings fields is not case sensitive
static int precedes_record_float_field(void* r1,void* r2){
  if(r1 == NULL){
    fprintf(stderr,"precedes_record_float_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2 == NULL){
    fprintf(stderr,"precedes_record_float_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct Record *rec1 = (struct Record*)r1;
  struct Record *rec2 = (struct Record*)r2;
  
  if(rec1->float_field < rec2->float_field){
      return(1);
  }else if(rec1->float_field == rec2->float_field){
      if(strcasecmp(rec1->string_field, rec2->string_field) < 0){
          return(1);
      }else if(strcasecmp(rec1->string_field, rec2->string_field) == 0){
          if(rec1->int_field < rec2->int_field){
              return(1);
          }
      }
  }
  return(0);
}

//It takes as input two pointers to struct record.
//It returns 1 iff the string field of the first record is less than 
//the string field of the second one (otherwise it compares the others fields)
//if the string field of the second record is greater than the string field
//of the first one, than it returns 0.
//The comparison between the strings fields is not case sensitive
static int precedes_record_string_field(void* r1,void* r2){
  if(r1 == NULL){
    fprintf(stderr,"precedes_record_string_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2 == NULL){
    fprintf(stderr,"precedes_record_string_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct Record *rec1 = (struct Record*)r1;
  struct Record *rec2 = (struct Record*)r2;
  
  if(strcasecmp(rec1->string_field, rec2->string_field) < 0){
      return(1);
  }else if(strcasecmp(rec1->string_field, rec2->string_field) == 0){
      if(rec1->int_field < rec2->int_field){
          return(1);
      }else if(rec1->int_field == rec2->int_field){
          if(rec1->float_field < rec2->float_field){
              return(1);
          }
      }
  }
  return(0);
}

static void load_array(const char* file_name, SortedArray* sorted_array){
  char *read_line;              
  char buffer[1024];
  int buf_size = 1024;
  FILE *fp;

  fp = fopen(file_name,"r");
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }

  int index = 0;
  while(fgets(buffer,buf_size,fp) != NULL && index < lenght){ 
    read_line = malloc((strlen(buffer)+1)*sizeof(char));  
    if(read_line == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }
    strcpy(read_line,buffer);
    
    char *id_field_in_read_line = strtok(read_line,",");
    int id_field = atoi(id_field_in_read_line);
    char *string_field_in_read_line = strtok(NULL,",");
    char *string_field = malloc((strlen(string_field_in_read_line)+1)*sizeof(char));
    if(string_field == NULL){
      fprintf(stderr,"main: unable to allocate memory for the string field of the read record");
      exit(EXIT_FAILURE);
    }
    strcpy(string_field,string_field_in_read_line);
   
    char *integer_field_in_read_line = strtok(NULL,",");
    int integer_field = atoi(integer_field_in_read_line);

    char *float_field_in_read_line = strtok(NULL,",");
    double floating_point_field = strtod(float_field_in_read_line, NULL);

    struct Record* record = malloc(sizeof(struct Record));
    if(record == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read record");
      exit(EXIT_FAILURE);
    }
    record->id_field = id_field;
    record->string_field = string_field;
    record->int_field = integer_field;
    record->float_field = floating_point_field;

    array_add(sorted_array, record);

    bzero(buffer, 1024);
    free(read_line);
    index++;
  }
  fclose(fp);

  printf("\nData loaded\n\n");
}


static  void print_array(SortedArray* sorted_array){ 
  struct Record *array_element;
  
  printf("Printing array...\n");
  for(unsigned long i=0;i<lenght;i++){
    array_element = (struct Record *)array_get(sorted_array, i);
    printf("<%d,%s,%d,%lf>\n",array_element->id_field,array_element->string_field, array_element->int_field, array_element->float_field); 
  }
}

static void write_csv(SortedArray* sorted_array)
{
  struct Record *array_element;
  int i = 0;
  FILE *out;
  out = fopen("./sorted.csv", "w+");
  if (out == NULL)
  {
      perror("Couldn't create the output csv file\n");
      exit(EXIT_FAILURE);
  }
  printf("Writing on the output csv file\n");
  for (i = 0; i < lenght; i++)
  { 
    array_element = (struct Record *)array_get(sorted_array, i);
    fprintf(out, "%d, %s, %d, %lf\n", array_element->id_field, array_element->string_field, array_element->int_field, array_element->float_field);
  }

  if (fclose(out) != 0)
  {
      perror("Couldnìt close the output csv file\n");
      exit(EXIT_FAILURE);
  }
  printf("Output file: registro.csv\n");
}


static  void test_with_comparison_function_quick_sort(const char* file_name, int (*compare)(void*, void*), int print_choice) {
  SortedArray* array = array_create(compare);
  clock_t start, end; 
  double cpu_time_used;

  load_array(file_name, array);
  start = clock();
  quick_sort(array, 0, lenght - 1, 1);
  end = clock();
  cpu_time_used = ((double)(end-start))/CLOCKS_PER_SEC;
  
  if(print_choice == 1){
    print_array(array);
  }
  printf("\ntime spent on sorting: %f\n\n", cpu_time_used);
  write_csv(array);
  free_array(array);
}

static  void test_with_comparison_function_binary_insertion_sort(const char* file_name, int (*compare)(void*, void*), int print_choice) {
  SortedArray* array = array_create(compare);
  clock_t start, end; 
  double cpu_time_used;

  load_array(file_name, array);
  start = clock();
  binary_insertion_sort(array);
  end = clock();
  cpu_time_used = ((double)(end-start))/CLOCKS_PER_SEC;
  
  if(print_choice == 1){
    print_array(array);
  }
  printf("\ntime spent on sorting: %f\n\n", cpu_time_used);
  write_csv(array);
  free_array(array);
}

#if(TEST)
  static  void test_with_comparison_function_insertion_sort(const char* file_name, int (*compare)(void*, void*), int print_choice) {
    SortedArray* array = array_create(compare);
    clock_t start, end; 
    double cpu_time_used;

    load_array(file_name, array);
    start = clock();
    insertion_sort(array);
    end = clock();
    cpu_time_used = ((double)(end-start))/CLOCKS_PER_SEC;
    
    if(print_choice == 1){
      print_array(array);
    }

    printf("time spent on sorting: %f\n", cpu_time_used);
    write_csv(array);
    free_array(array);
  }
#endif

static void test_quick_sort(int sort, int print_choice, const char* argv){
  switch (sort){
    case 1:
      test_with_comparison_function_quick_sort(argv, precedes_record_int_field, print_choice);
      break;

    case 2:
      test_with_comparison_function_quick_sort(argv, precedes_record_string_field, print_choice);
      break;

    case 3:
      test_with_comparison_function_quick_sort(argv, precedes_record_float_field,  print_choice);
      break;

    default:
      fprintf(stderr, "number must be 1, 2 or 3\n");
      break;
  }
}

static void test_binary_sort(int sort, int print_choice, const char* argv){
  switch (sort){
    case 1:
      test_with_comparison_function_binary_insertion_sort(argv, precedes_record_int_field, print_choice);
      break;
    
    case 2:
      test_with_comparison_function_binary_insertion_sort(argv, precedes_record_string_field, print_choice);
      break;

    case 3:
      test_with_comparison_function_binary_insertion_sort(argv, precedes_record_float_field, print_choice);
      break;

    default:
      fprintf(stderr, "number must be 1, 2 or 3\n");
      break;
  }
}

void main(int argc, const char* argv[]){
  if(argc < 2){
    printf("Usage: sorted_array_main < file * name >\n");
  }	
  int choice, sort, print_choice;

  printf("How many records do you wanna sort from 0 to 20.000.000?\n");
  if(scanf("%d", &lenght)!= 1){
    fprintf(stderr, "Error insertion value lenght\n");
  }

  printf("\nQuale algoritmo eseguire?\n1 - Binary insertion-sort\n2 - Quicksort\n");
  if(scanf("%d", &choice)!= 1){
    fprintf(stderr, "Error insertion value choice\n");
  }

  printf("\nDo you want to print the array when it's sorted?\n1 - YES\n2 - NO\n");
  if(scanf("%d", &print_choice)!= 1){
    fprintf(stderr, "Error insertion value for criterion\n");
  }


  if(choice == 2){
      printf("\n######################## QUICK SORT: %d ########################\n", lenght);
      printf("Please select the parameter you want to order by\n1 - INTEGER\n2 - STRING\n3 - FLOAT\n");

      if(scanf("%d", &sort)!= 1){
        fprintf(stderr, "Error insertion value for criterion\n");
      }
      test_quick_sort(sort, print_choice, argv[1]);

  }else if(choice == 1){
      printf("\n######################## BINARY INSERTION SORT: %d ########################\n", lenght);
      printf("Please select the parameter you want to order by\n1 - INTEGER\n2 - STRING\n3 - FLOAT\n");

      if(scanf("%d", &sort)!= 1){
        fprintf(stderr, "Error insertion value for criterion\n");
      } 
      test_binary_sort(sort, print_choice, argv[1]);

  }else{
    fprintf(stderr, "number must be 1 or 2\n");
  }

  #if(TEST)
    printf("######################## INSERTION SORT: %d ########################\n", lenght);

      printf("\nINTEGER\n");
      test_with_comparison_function_insertion_sort(argv[1], precedes_record_int_field, print_choice);

      printf("\nSTRING\n");
      test_with_comparison_function_insertion_sort(argv[1], precedes_record_string_field, print_choice);

      printf("\nFLOAT\n");
      test_with_comparison_function_insertion_sort(argv[1], precedes_record_float_field, print_choice);
   #endif
}