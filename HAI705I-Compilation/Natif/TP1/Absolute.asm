# Absolue

.data
input: .asciiz "Saisir un nombre : "
abstr: .asciiz "Valeur absolue : "

.text
main:	li $v0, 4
	la $a0, input
	syscall
	
	li $v0, 5
	syscall
	move $a0, $v0
	
	jal absolute
	move $s0, $v0
	
	li $v0, 4
	la $a0, abstr
	syscall
	
	li $v0, 1
	move $a0, $s0
	syscall
	
	li $v0, 10
	syscall
	
absolute: move $t0, $a0
	  bgez $t0, ret
	  li $t1, -1
	  mul $t0, $t0, $t1
ret: 	  move $v0, $t0
	  jr $ra
