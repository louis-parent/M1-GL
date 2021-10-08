#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void* thread(void* args);

int main(int argc, char* argv[])
{
	if(argc != 2)
	{
		printf("Usage: %s [thread count]\n", argv[0]);
		return 1;
	}
	
	int threadCount = atoi(argv[1]);
	
	pthread_t threads[threadCount];
	int shared = 0;
	
	for(unsigned int i = 0; i < threadCount; i++)
	{
		pthread_t id;
		pthread_create(&id, NULL, thread, &shared);
		threads[i] = id;
	}

	for(unsigned int i = 0; i < threadCount; i++)
	{
		int r = pthread_join(threads[i], NULL);
	}

	printf("Ends with %d\n", shared);
	
	return 0;
}

void* thread(void* args)
{
	int* shared = (int*) args;
	(*shared)++;
	pthread_exit(NULL);
}
