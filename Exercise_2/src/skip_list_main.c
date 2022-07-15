#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "skip_list.h"

static int precedes_record_string_field(void* r1,void* r2){
  if(r1 == NULL){
    fprintf(stderr,"precedes_record_string: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2 == NULL){
    fprintf(stderr,"precedes_record_string: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  char *rec1 = (char*)r1;
  char *rec2 = (char*)r2;
  
  if(strcasecmp(rec1, rec2) < 0){
    return(1);
  }else{
    return(0);
  }
}

static  void print_list(SkipList* list){ 
  Node *tmp = list->head->next[0];
  char *string_to_print;
  
  while(tmp != NULL){
    string_to_print = (char *)tmp->item;
    printf("%s\n",string_to_print);
    tmp = tmp->next[0];
  }
}

static void *load_dictionary(const char* file_name, SkipList *dictionary){
  char *read_line;              
  char buffer[256];
  int buf_size = 256;
  FILE *fp;

  fp = fopen(file_name,"r");
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }
  while(fgets(buffer,buf_size,fp) != NULL){ 
    read_line = malloc((strlen(buffer)+1)*sizeof(char));  
    if(read_line == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }
    strcpy(read_line,buffer);
    
    char *string = malloc((strlen(read_line)+1)*sizeof(char));
    if(string == NULL){
      fprintf(stderr,"main: unable to allocate memory for the string field of the read record");
      exit(EXIT_FAILURE);
    }
    strcpy(string, read_line);
    insert_skip_list(dictionary, string);
    bzero(buffer, 256);
    free(read_line);
  }
  fclose(fp);

  printf("\nDictionary loaded\n\n");
}

static void words_to_correct(const char* file_name, SkipList *dictionary){
  char *read_line;              
  char buffer[2048];
  int buf_size = 2048;
  char * end = "\n";
  FILE *fp;

  fp = fopen(file_name,"r");
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }
 
  while(fgets(buffer,buf_size,fp) != NULL ){ 
    read_line = malloc((strlen(buffer)+1)*sizeof(char));  
    if(read_line == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }
    strcpy(read_line,buffer);
    char *string = malloc((strlen(read_line)+1)*sizeof(char));
    if(string == NULL){
      fprintf(stderr,"main: unable to allocate memory for the string field of the read record");
      exit(EXIT_FAILURE);
    }
    char delimit [] = ".,:;-_!?/+*[](){}<>|& ";
    string = strtok(read_line, delimit);
    printf("PAROLE SCONOSCIUTE: \n");
    while (string != NULL){
      char *tmp = malloc(strlen(string)+2);
      strcpy(tmp,string);
      strcat(tmp, end);
      
      if(strcasecmp(((char *)search_skip_list(dictionary, tmp)), tmp) != 0){
        printf("%s\n", string);
      }
      free(tmp);
      string = strtok(NULL, delimit);
    }
    
    bzero(buffer, 2048);
    free(read_line);
  }
  fclose(fp);
}

static void free_skip_list (SkipList* list){
  while(list->head!=NULL){
    free(list->head->item);
    list->head = list->head->next[0];
  }
  delete_skip_list(list);
}

int main(int argc, const char* argv[]){
    if(argc < 3){
    printf("Usage: ./skip_list_main  < file dictionary * name > < file to correct * name >\n");
  }
  printf("skip list is building...\n");
  clock_t start, end;
  double cpu_time_used;
  SkipList *dictionary = create_skip_list(precedes_record_string_field);

  start = clock();
  load_dictionary(argv[1], dictionary);
  end = clock();
  cpu_time_used = ((double)(end-start))/CLOCKS_PER_SEC;
  printf("\ntime spent to populate dictionary with height %d: %f\n\n", MAX_HEIGHT, cpu_time_used);

  start = clock();
  words_to_correct(argv[2], dictionary);
  end = clock();
  printf("\nEnd of file\n");
  cpu_time_used = ((double)(end-start))/CLOCKS_PER_SEC;
  printf("\ntime spent to search with height %d: %f\n\n", MAX_HEIGHT, cpu_time_used);
  free_skip_list(dictionary);

  return 0;
}