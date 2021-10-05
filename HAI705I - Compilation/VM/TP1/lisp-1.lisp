; Factorielle
(defun fact (n) (if (<= n 1)
					1
					(* n (fact (- n 1)))
				)
)

; Fibonacci
(defun fibo (n) (if (<= n 1)
					1
					(+ (fibo (- n 1)) (fibo (- n 2)))
				)
)

(defun memb (x l) (if (null l)
						()
						(if (= (car l) x)
							l
							(memb x (cdr l))
						)
					)
)

(defun len (l) (if (null l) 0 (+ (len (cdr l)) 1)))

(defun lastel (l) (if (null (cdr l)) (car l) (lastel (cdr l))))

(defun makelistdesc (n) (if (<= n 0) () (cons n (makelistdesc (- n 1)))))

(defun makelistasc (n) (if (<= n 0) () (cons (makelistasc (- n 1)) n)))

(defun copylist (l) (if (null l) () (cons (car l) (copylist (cdr l)))))

(defun removel (x l) (if (null l)
						() 
						(if (= (car l) x)
							(remove x (cdr l))
							(cons (car l) (remove x (cdr l)))
						)
					)
)

(defun removefel (x l) (if (null l)
						() 
						(if (= (car l) x)
							(copylist (cdr l))
							(cons (car l) (remove x (cdr l)))
						)
					)
)

(defun concatl (l1 l2) ())

(defun appendl (l1 l2) (if (null (car l2))
							l1
							(appendl (cons l1 (car l2)) (cdr l2))
						)
)

(appendl (list 1 2 3) (list 4 5 6))

(defun adjoin (x l) (if (null))
