# N entier

.data
message: .asciiz "Afficher n entier :\nn = "
space: .asciiz " "

.text
main: 	li $v0, 4
	la $a0, message
	syscall
	
	li $v0, 5
	syscall
	move $t0, $v0
	
	li $t1, 1
	li $t2, 1
	
printloop:
	bgt $t1, $t0, afterloop
	
	li $v0, 1
	move $a0, $t1
	syscall
	
	add $t1, $t1, $t2
	
	li $v0, 4
	la $a0, space
	syscall
	
	j printloop
	
afterloop:
	li $v0, 10
	syscall