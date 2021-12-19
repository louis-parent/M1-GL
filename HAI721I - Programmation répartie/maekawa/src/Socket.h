#ifndef __SOCKET_H__
#define __SOCKET_H__

#define MAX_CONNECTION 128

#include <netinet/ip.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>

#include <functional>
#include <string>

using namespace std;

class Socket{
    private:
        int socketfd;
        struct sockaddr_in sockAddr;

        Socket(int socketfd, struct sockaddr_in sockAddr);
        struct sockaddr_in createSockAddresse(string addresse, unsigned short port);

        void callFuncWith(int socketClient, struct sockaddr_in addr_client, std::function<void* (void*, void*)> func, void* context);
        void* threadCallback(void* parameters);

    public:
        Socket(string addresse, unsigned short port);

        void startListening(std::function<void* (void*, void*)> func, void* context);
        void tryConnect();
	
		void sendData(const void* data, size_t length);
		size_t receiveData(void* data, size_t length);
	
		string getAddress() const;
		unsigned short getPort() const;

        bool equals(const Socket& socket);
        void closeConnection();
};

#endif
