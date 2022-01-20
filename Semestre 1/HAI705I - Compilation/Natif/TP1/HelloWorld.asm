# Hello World

.data
hello: .asciiz "Hello World!\n"

.text
main: 	li $v0, 4
	la $a0, hello
	syscall