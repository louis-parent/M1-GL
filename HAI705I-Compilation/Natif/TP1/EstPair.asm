# Est Pair

.data
message: .asciiz "Tester la parité de : "
pair: .asciiz "Le chiffre est pair\n"
impair: .asciiz "Le chiffre est impair\n"

.text
main: 	li $v0, 4
	la $a0, message
	syscall
	
	li $v0, 5
	syscall
	
	move $t0, $v0
	li $t1, 2
	
	rem $t3, $t0, $t1
	
	beqz $t3, pairMsg
	
impairMsg: la $a0, impair
	   j msg
	   
pairMsg:   la $a0, pair
	   j msg
	   
msg:	li $v0, 4
	syscall