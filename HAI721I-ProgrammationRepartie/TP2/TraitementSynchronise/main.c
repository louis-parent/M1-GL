#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>
#include <stdlib.h>

typedef struct {
	unsigned int operatorNum;
	
	unsigned int* blocks;
	unsigned int blockCount;
	
	unsigned int* operatorStatus;
	unsigned int operatorCount;
} OperatorArgs;


/**
 * Functions
 */

void* createZeroInitilizedArray(size_t length);

OperatorArgs* newOperatorArgs(unsigned int operatorNum, unsigned int* blocks, unsigned int blockCount, unsigned int* operatorStatus, unsigned int operatorCount);

void* operator(void* args);

void joinAll(pthread_t* threads, size_t count);

void dumpOperatorStatus(unsigned int* status, unsigned int operatorCount);
void dumpBlocks(unsigned int* blocks, unsigned int blockCount);



/**
 * MAIN
 */

int main(int argc, char* argv[])
{
	if(argc != 3)
	{
		printf("Usage: %s [blocks count] [operator count]\n", argv[0]);
		return 1;
	}
	
	unsigned int blockCount = atoi(argv[1]);
	unsigned int operatorCount = atoi(argv[2]);
	
	printf("Starting processing of %u blocks by %u operator\n", blockCount, operatorCount);
	
	unsigned int* blocks = createZeroInitilizedArray(sizeof(unsigned int) * blockCount);
	unsigned int* operatorStatus = createZeroInitilizedArray(sizeof(unsigned int) * operatorCount);
	
	pthread_t operators[operatorCount];
		
	for(unsigned int i = 0; i < operatorCount; i++)
	{
		OperatorArgs* args = newOperatorArgs(i, blocks, blockCount, operatorStatus, operatorCount);
		
		int created = pthread_create(&(operators[i]), NULL, operator, args);
		
		if(created == 0)
		{
			printf("Operator %u started\n", i);
		}
	}
	
	joinAll(operators, operatorCount);
	
	dumpOperatorStatus(operatorStatus, operatorCount);
	dumpBlocks(blocks, blockCount);
	
	free(blocks);
	free(operatorStatus);
	
	return 0;
}

void* createZeroInitilizedArray(size_t size)
{
	char* array = malloc(size);
	
	for(size_t i = 0; i < size; i++)
	{
		array[i] = 0;
	}
	
	return array;
}

OperatorArgs* newOperatorArgs(unsigned int operatorNum, unsigned int* blocks, unsigned int blockCount, unsigned int* operatorStatus, unsigned int operatorCount)
{
	OperatorArgs* args = malloc(sizeof(OperatorArgs));
	
	args->operatorNum = operatorNum;
	args->blocks = blocks;
	args->blockCount = blockCount;
	args->operatorStatus = operatorStatus;
	args->operatorCount = operatorCount;
	
	return args;
}

void* operator(void* args)
{
	OperatorArgs* data = (OperatorArgs*) args;
	
	unsigned int me = data->operatorNum;
	unsigned int previous = me - 1;
	unsigned int currentBlock = data->operatorStatus[me];
	
	while(currentBlock < data->blockCount)
	{		
		if(me != 0)
		{
			while(data->operatorStatus[previous] <= currentBlock) {}
		}
		
		sleep(currentBlock%3);
		data->blocks[currentBlock] = me;

		currentBlock = ++(data->operatorStatus[me]);
	}
	
	free(data);
	
	pthread_exit(NULL);
}

void joinAll(pthread_t* threads, size_t count)
{
	for(size_t i = 0; i < count; i++)
	{
		pthread_join(threads[i], NULL);
	}
}

void dumpOperatorStatus(unsigned int* status, unsigned int operatorCount)
{
	printf("[DUMP] operator status :\n");
	for(unsigned int i = 0; i < operatorCount; i++)
	{
		printf("- %u on block %u\n", i, status[i]);
	}
}

void dumpBlocks(unsigned int* blocks, unsigned int blockCount)
{
	printf("\n[DUMP] blocks : [");
	
	for(unsigned int i = 0; i < blockCount; i++)
	{
		printf("%u, ", blocks[i]);
	}
	
	printf("]\n");
}
