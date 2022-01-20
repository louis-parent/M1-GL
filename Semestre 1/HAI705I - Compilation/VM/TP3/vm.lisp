(defun make_vm (vm) (
	(cons vm (cons '() '()))
))

(defun vm_load_asm (vm asm) (
	(cons (car vm) (cons asm (cdr (cdr vm)))
))
(defun vm_load_asm_file (vm path) (let ((asm '()))
	(let ((in (open path :if-does-not-exist nil)))
		(when in
		  (loop for line = (read-line in nil) while line do (append asm (list line)))
		  (close in)
		)
	)
	asm
))

(defun vm_compile_load_asm (expr) (
))

(defun vm_exec (vm) (
))
