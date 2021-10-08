#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

typedef struct {
	unsigned int meetingPoint;
	unsigned int target;
} MeetingPoint;


void* doubleTaskWithMeetingPoint(void* args);

int main(int argc, char* argv[])
{
	srand(time(NULL));
	if(argc != 2)
	{
		printf("Usage: %s [thread count]\n", argv[0]);
		return 1;
	}
	
	int threadCount = atoi(argv[1]);
	MeetingPoint meetingPoint = {0, threadCount};
	pthread_t threads[threadCount];
	
	for(unsigned int i = 0; i < threadCount; i++)
	{
		pthread_create(&(threads[i]), NULL, doubleTaskWithMeetingPoint, &meetingPoint);
	}
	
	for(unsigned int i = 0; i < threadCount; i++)
	{
		pthread_join(threads[i], NULL);
	}
	
	return 0;
}

void* doubleTaskWithMeetingPoint(void* args)
{
	MeetingPoint* meetingPoint = (MeetingPoint*) args;
	
	printf("Doing task 1\n");
	sleep(rand()%3);
	meetingPoint->meetingPoint++;
	
	while(meetingPoint->meetingPoint < meetingPoint->target) {}
	
	printf("Doing task 2\n");
	sleep(rand()%3);
	
	pthread_exit(NULL);
}
