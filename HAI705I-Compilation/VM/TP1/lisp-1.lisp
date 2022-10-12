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

(defun appendl (l1 l2) (if (null l1)
							l2
							(if (listp l1)
								(cons (car l1) (appendl (cdr l1) l2))
								l1
							)
						)
)

(appendl (list 1 2 3) (list 4 5 6))

(defun adjoinl (x l) (if (null (car l))
						x
						(if (= (car l) x)
							l
							(cons (car l) (adjoinl x (cdr l)))
						)
					)
)

(adjoinl 5 (list 1 2 3 4))
(adjoinl 5 (list 1 5 3 4))

(defun treesize (tree) (if (null tree)
						0
						(if (listp tree)
							(+ 1 (+ (treesize (car tree)) (treesize (cdr tree))))
							1
						)
					)
)

(treesize (cons (cons (cons 1 2) (cons 3 4)) (cons (cons 5 6) (cons 7 8))))

(defun leafsize (tree) (if (null tree)
						0
						(if (listp tree)
							(+ (leafsize (car tree)) (leafsize (cdr tree)))
							1
						)
					)
)

(leafsize (cons (cons (cons 1 2) (cons 3 4)) (cons (cons 5 6) (cons 7 8))))

(defun copytree (tree) (if (null tree)
							()
							(if (listp tree)
								(cons (copytree (car tree)) (copytree (cdr tree)))
								tree
							)
						)
)

(copytree (cons (cons (cons 1 2) (cons 3 4)) (cons (cons 5 6) (cons 7 8))))

(defun substree (x y tree) (if (null tree)
							()
							(if (listp tree)
								(cons (substree x y (car tree)) (substree x y (cdr tree)))
								(if (= tree x)
									y
									tree
								)
							)
						)
)

(substree 1 2 (cons (cons (cons 1 2) (cons 2 1)) (cons (cons 2 1) (cons 1 2))))

(defun tree-leaves (tree) (if (null tree)
								()
								(if (listp tree)
									(appendl (tree-leaves (car tree)) (tree-leaves (cdr tree)))
									(cons tree ())
								)
							)
)

(tree-leaves (cons (cons (cons 1 2) (cons 3 4)) (cons (cons 5 6) (cons 7 8))))
