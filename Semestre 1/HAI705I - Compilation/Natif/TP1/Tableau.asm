# Tableau

.data
tab: .space 12
ln: .asciiz "\n"

.text
main:	la $t0, tab

	li $t1, 1
	sw $t1, 0($t0)
	
	li $t1, 2
	sw $t1, 4($t0)
	
	li $t1, 3
	sw $t1, 8($t0)
	
	
	lw $a0, 0($t0)
	jal printi
	
	lw $a0, 4($t0)
	jal printi
	
	lw $a0, 8($t0)
	jal printi
	
	la $a0, ln
	jal prints
	
	lw $t1, 0($t0)
	lw $t2, 8($t0)
	sw $t2, 0($t0)
	sw $t1, 8($t0)
	
	lw $a0, 0($t0)
	jal printi
	
	lw $a0, 4($t0)
	jal printi
	
	lw $a0, 8($t0)
	jal printi
	
	j exit

printi: li $v0, 1
	syscall
	jr $ra
	
prints: li $v0, 4
	syscall
	jr $ra
	
exit:	li $v0, 10
	syscall