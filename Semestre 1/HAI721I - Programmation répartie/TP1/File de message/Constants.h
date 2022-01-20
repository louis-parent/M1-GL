#define KEY "TD1"

typedef enum { DEMAND = 1, LIBERATE = 2 } Label;

typedef struct {
	long label;
	pid_t pid;
} Message;
