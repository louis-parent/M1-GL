#ifndef __REQUEST_H__
#define __REQUEST_H__

enum class RequestType {
	ADD,
	SYNC,
	DEMAND,
	AGREEMENT,
	FAIL,
	POLL,
	RESTITUTION,
	RELEASE
};

struct RequestCriticalArg {
	bool isCriticalAccessInUse;
	char address[15];
	unsigned short port;
	long timeStamp;
};

union RequestArgs {
	RequestCriticalArg critical;
	long timeStamp;
};

struct Request {
	RequestType type;
	unsigned short port;
	
	RequestArgs args;
};

inline Request newRequest(RequestType type, unsigned short port) {
	Request request;
	request.type = type;
	request.port = port;
	return request;
}

#endif