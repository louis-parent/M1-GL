#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>

#include "Constants.h"

int queue = 0;

void init();
void loop();

pid_t receiveDemand();
void sendAuthorisationTo(pid_t pid);
void waitForRelease();

unsigned long getQueueLength();

int main(int argc, char* argv[])
{
	printf("[SERVER] Starting server...\n");
	
	init();
	
	printf("[SERVER] Wainting for client :\n");
	
	loop();
	
	return 0;
}

void init()
{
	queue = msgget(ftok(KEY, 0), IPC_CREAT | IPC_EXCL | 0666);
	printf("[SERVER] Queue created\n");
}

void loop()
{
	while(1)
	{
		pid_t pid = receiveDemand();
		
		printf("[SERVER] Autorisation client %d with %lu queued\n", pid, getQueueLength());
		
		sendAuthorisationTo(pid);
		waitForRelease();
		
		printf("[SERVER] Libération par le client %d\n", pid);
	}
}

pid_t receiveDemand()
{
	Message message;
	msgrcv(queue, &message, sizeof(message.pid), DEMAND, MSG_NOERROR);
	return message.pid;
}

void sendAuthorisationTo(pid_t pid)
{
	Message message;
	message.label = pid;
	message.pid = getpid();
	msgsnd(queue, &message, sizeof(Message), 0);
}

void waitForRelease()
{
	Message message;
	msgrcv(queue, &message, sizeof(message.pid), LIBERATE, 0);
}

unsigned long getQueueLength()
{
	struct msqid_ds stat;
	msgctl(queue, IPC_STAT, &stat);
	return stat.msg_qnum;
}
