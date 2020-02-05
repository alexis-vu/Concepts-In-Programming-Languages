; length function
(defun my-length (l)
  (cond
      ((null l) 0)
      (t (+ 1 (my-length (cdr l))))))

; memq function
(defun my-memq (a l)
  (cond ((null l) nil)
        ((eq a (car l)) l)
        (t (my-memq a (cdr l)))))

; append function
(defun my-append (l1 l2)
 (cond ((null l1) l2)
       (t (cons (car l1) (my-append (cdr l1) l2)))))

; attach function
(defun my-attach (o l)
  (cond ((null l) (cons o nil))
        (t (cons (car l) (my-attach o (cdr l))))))

; associate function
(defun my-assoc (a l)
  (cond ((null l) nil)
        ((eq a (car (car l))) (car l))
        (t (my-assoc a (cdr l)))))

; frequency function
(defun freq (a l)
    (cond ((null l) 0)
          ((atom l) (cond ((eq a l) 1)
                          (t 0)))
          (t (+ (freq a (cdr l)) (freq a (car l))))))

; mapping function
(defun mapping (l val)
    (cond ((null l) nil)
          ((< (car (car l)) val) (cons (car (cdr (car l))) (mapping (cdr l) val)))
          (t (mapping (cdr l) val))))

; last function
(defun my-last (a l)
  (cond ((eq (my-memq a (cdr l)) nil) (my-memq a l))
        (t (my-last a (my-memq a (cdr l))))))

; reverse function
(defun my-reverse (l)
  (cond ((null (cdr l)) l)
        (t (my-append (my-reverse (cdr l)) (cons (car l) nil)))))

; is-pattern? function
; helpter
(defun is-pattern-h (pat str cstr)
  (cond ((null pat) cstr)
        ((null str) nil)
        ((eq (car pat) (car str)) (is-pattern-h (cdr pat) (cdr str) cstr))
        (t (is-pattern-h pat (cdr cstr) (cdr cstr)))))

(defun is-pattern? (pat str)
  (cond ((null pat) nil)
        ((null str) nil)
        (t (is-pattern-h pat str str))))

; first-atom function
(defun first-atom (l)
  (cond ((atom (car l)) (car l))
        (t (first-atom (car l)))))

; find-all function
; helpter
(defun find-all-h (l)
  (cond ((atom (car l)) (cdr l))
        ((null (find-all-h (car l))) (cdr l))
        (t (cons (find-all-h (car l)) (cdr l)))))

(defun find-all (a l)
  (cond ((null l) nil)
        ((eq a (first-atom l)) (cons (first-atom (find-all-h l)) (find-all a (find-all-h l))))
        (t (find-all a (find-all-h l)))))

; test cases

(print "TESTING MY-LENGTH")
(print(my-length nil))
(print(my-length '(b (a b c))))
(print(my-length '(a (((b))) c)))
(print(my-length '(a b c)))

(print "TESTING MY-MEMQ")
(print(my-memq 'a nil))
(print(my-memq 'b '(a b c)))
(print(my-memq 'd '(a b c d e f g)))
(print(my-memq 'd '(a b c d)))
(print(my-memq 'd '(a b c)))

(print "TESTING MY-APPEND")
(print (my-append '(a b c) '(d e f)))
(print (my-append '((a) (b) (c)) '((d) (e) (f))))
(print (my-append nil '(d e f)))
(print (my-append '(a b c) nil))

(print "TESTING MY-ATTACH")
(print (my-attach 'a nil))
(print (my-attach 'd '(a b c)))
(print (my-attach '(a) '(b c)))

(print "TESTING MY-ASSOC")
(print (my-assoc 'a nil))
(print (my-assoc 'a '((a . b)(c e f)(b))))
(print (my-assoc 'c '((a . b)(c e f)(b))))
(print (my-assoc 'b '((a . b)(c e f)(b))))
(print (my-assoc 'f '((a . b)(c e f)(b))))

(print "TESTING FREQ")
(print (freq  'c '((a c) c e)))
(print (freq  'f '(((s) o ) d)))
(print (freq  'f '(((f) f) f f)))

(print "TESTING MAPPING")
(print (mapping '((35 kim) (67 clinton) (45 emma)) 40))
(print (mapping '((24 a) (15 b) (56 c) (19 d)) 26))
(print (mapping '((90 a) (80 b) (70 c))  40))

(print "TESTING MY-LAST")
(print (my-last 'a '(a b c a b c a b c d e f g)))
(print (my-last 'b '(a b c a b c a b c d e f g)))
(print (my-last 'g '(a b c a b c a b c d e f g)))
(print (my-last 'h '(a b c a b c a b c d e f g)))

(print "TESTING MY-REVERSE")
(print (my-reverse nil))
(print (my-reverse '(a)))
(print (my-reverse '(1 2 3 4 5)))

(print "TESTING IS-PATTERN")
(print (is-pattern? '(a b s) '(c d b a s)))
(print (is-pattern? '(c a c) '(b a j a c a c t u s)))
(print (is-pattern? nil '(a n y l i s t)))
(print (is-pattern? '(l i s p) nil))

(print "TESTING FIRST-ATOM")
(print (first-atom nil))
(print (first-atom '((2 (1) 4) 6)))
(print (first-atom '((((s)) o ))))
(print (first-atom '(1 (((2)) 3 4))))

(print "TESTING FIND-ALL")
(print (find-all 'a nil))
(print (find-all 'a '(b a c a e)))
(print (find-all 'a '(b d c e)))
(print (find-all 'a '(b (a a) c)))
(print (find-all 'a '((b a) ((c a b)))))
