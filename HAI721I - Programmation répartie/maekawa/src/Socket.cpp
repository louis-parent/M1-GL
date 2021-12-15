#include "Socket.h"
#include <string.h>
#include <thread>
#include <arpa/inet.h>
#include <iostream>
using namespace std;

Socket::Socket(string addresse, unsigned short port){
    this->sockAddr = this->createSockAddresse(addresse, port);

    this->socketfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
}

Socket::Socket(int socket, struct sockaddr_in sockAddr){
    this->sockAddr = sockAddr;
    this->socketfd = socket;
}

struct sockaddr_in Socket::createSockAddresse(string addresse, unsigned short port){
    struct sockaddr_in addr;

    struct hostent* server_host =  gethostbyname(addresse.c_str());

    bcopy(server_host->h_addr_list[0], &addr.sin_addr.s_addr, server_host->h_length);
    addr.sin_port = htons(port);
    addr.sin_family = AF_INET;

    return addr;
}

void Socket::startListening(std::function<void* (void*, void*)> func, void* context){
    bind(this->socketfd, (struct sockaddr*) &this->sockAddr, sizeof(this->sockAddr));

    listen(this->socketfd, MAX_CONNECTION);

    while(true){
        struct sockaddr_in addr_client;
        socklen_t size_client = sizeof(addr_client);

        int socket_client = accept(this->socketfd, (struct sockaddr*) &addr_client, &size_client);
        this->callFuncWith(socket_client, addr_client, func, context);
    }
}

void Socket::tryConnect(){
    connect(this->socketfd, (struct sockaddr*) &this->sockAddr, sizeof(this->sockAddr));
}

void Socket::sendData(const void* data, size_t length) {
	send(this->socketfd, data, length, 0);
}

size_t Socket::receiveData(void* data, size_t length) {
	return recv(this->socketfd, data, length, 0);
}

void Socket::callFuncWith(int socketClient, struct sockaddr_in addr_client, std::function<void* (void*, void*)> func, void* context){
    Socket* socket = new Socket(socketClient, addr_client);
    thread(func, context, socket).detach();
}

void* Socket::threadCallback(void* parameters){

    return NULL;
}

string Socket::getAddress() const
{
	return string(inet_ntoa(this->sockAddr.sin_addr));
}

bool Socket::equals(const Socket& socket){
    return this->socketfd && socket.socketfd;
}
