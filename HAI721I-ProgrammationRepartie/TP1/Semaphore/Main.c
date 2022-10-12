#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/sem.h>

#include "Semaphore.h"
#include "Fork.h"

union semun {
	int              val;    /* Value for SETVAL */
	struct semid_ds* buf;    /* Buffer for IPC_STAT, IPC_SET */
	unsigned short*	 array;  /* Array for GETALL, SETALL */
	struct seminfo*  __buf;  /* Buffer for IPC_INFO (Linux-specific) */
};

Semaphore sem;

int semSync(void* data)
{
	int i = *((int*) data);
	
	printf("Starting processus n°%u with %ds of work\n", i, i);
	sleep(i);
	
	procureSemaphoreOne(sem);
	printf("Calling p from child n°%u then waiting\n", i);

	waitForZeroSemaphore(sem);
	printf("Finish at %lu\n", time(NULL));
	
	return 0;
}

int main(int argc, char* argv[])
{
	if(argc != 2)
	{
		printf("Usage: %s [number of children]\n", argv[0]);
		return 1;
	}

	srand((unsigned) time(NULL));
	
	unsigned int childCount = atoi(argv[1]);
	printf("Starting rendez-vous with %u participants\n", childCount);
	
	sem = createSemaphore();
	printf("Semaphore created\n");
	
	initSemaphore(sem, childCount);
	printf("Semaphore initialized\n");
	
	bool isParent = true;
	unsigned int i = 0;
	
	for(unsigned int i = 0; i < childCount; i++)
	{
		runChildProcess(semSync, &i);
	}

	printf("Bootstrap finished\n");
	
	return 0;
}
