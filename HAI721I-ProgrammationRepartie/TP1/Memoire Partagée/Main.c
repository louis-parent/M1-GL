#include <stdlib.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <unistd.h>
#include <time.h>

#define ZONE_AMOUNT 5

int main(int argc, char* argv[])
{
	/*
	 * Args
	 */
	if(argc != 2)
	{
		printf("Usage: %s [Amount of treatments]\n", argv[0]);
		return 1;
	}
	
	int treatmentCount = atoi(argv[1]);
	
	/*
	 * Init
	 */
	key_t key = ftok("SHARED_MEMORY", 0);
	int memory = shmget(key, ZONE_AMOUNT * sizeof(int), IPC_CREAT | 0666);
	int semaphores = semget(key, ZONE_AMOUNT, IPC_CREAT | 0666);
	
	for(unsigned int i = 0; i < ZONE_AMOUNT; i++)		
		semctl(semaphores, i, SETVAL, 1);
		
	int* zones = (int*) shmat(memory, NULL, 0);
	
	for(unsigned int i = 0; i < ZONE_AMOUNT; i++)
		zones[i] = 0;
		
	/*
	 * Main
	 */		
	
	for(unsigned int i = 0; i < treatmentCount; i++)
	{
		if(fork() == 0)
		{
			int realNumber = i + 1;
			
			struct sembuf waiter;
			waiter.sem_flg = 0;
			
			for(unsigned int zone = 0; zone < ZONE_AMOUNT; zone++)
			{
				printf("N°%d waiting for zone %d at %lu\n", realNumber, zone, time(NULL));
				waiter.sem_num = zone;
				waiter.sem_op = realNumber * -1;
				semop(semaphores, &waiter, 1);
				printf("N°%d accessing to zone %d at %lu\n", realNumber, zone, time(NULL));
				
				zones[zone] = realNumber;
				sleep(i);
				
				printf("N°%d releasing place for zone %d at %lu\n", realNumber, zone, time(NULL));
				sleep(1);
				waiter.sem_op = realNumber + 1;
				semop(semaphores, &waiter, 1);
			}
			
			exit(0);
		}
	}
	
	/*
	 * End
	 */
	struct sembuf waitAll[ZONE_AMOUNT];
	for(unsigned int i = 0; i < ZONE_AMOUNT; i++)
	{
		waitAll[i].sem_num = i;
		waitAll[i].sem_op = treatmentCount+2;
		waitAll[i].sem_flg = 0;
	}	
	semop(semaphores, waitAll, ZONE_AMOUNT);
	
	for(unsigned int i = 0; i < ZONE_AMOUNT; i++)
	{
		printf("Résultat zone %d : %d\n", i, zones[i]);
		semctl(memory, i, IPC_RMID, NULL);
	}
	
	shmctl(memory, IPC_RMID, NULL);
	return 0;
}
