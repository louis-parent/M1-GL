#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>
#include <time.h>

#include "Constants.h"


int queue = 0;

void init();

void sendDemand();
void waitForAuthorisation();
void sendRelease();

int main(int argc, char* argv[])
{	
	printf("[%d] Starting client...\n", getpid());
	init();
	
	sendDemand();
	printf("[%d] Request sended at %lus\n", getpid(), time(NULL));
	
	waitForAuthorisation();
	printf("[%d] Autorisation reçue à %lu\n", getpid(), time(NULL));
	
	sleep(5);
	
	sendRelease();
	printf("[%d] Fin du client à %lu\n", getpid(), time(NULL));
}

void init()
{
	queue = msgget(ftok(KEY, 0), 0666);
}

void sendDemand()
{
	Message message;
	message.label = DEMAND;
	message.pid = getpid();
	msgsnd(queue, &message, sizeof(Message), 0);
}

void waitForAuthorisation()
{
	Message message;
	msgrcv(queue, &message, sizeof(message.pid), getpid(), 0);
}

void sendRelease()
{
	Message message;
	message.label = LIBERATE;
	message.pid = getpid();
	msgsnd(queue, &message, sizeof(Message), 0);
}
