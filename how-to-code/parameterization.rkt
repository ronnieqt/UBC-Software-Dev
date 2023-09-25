;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname parameterization) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; parameterization-starter.rkt

(* pi (sqr 4)) ;area of circle radius 4
(* pi (sqr 6)) ;area of circle radius 6

(define (area r)
  (* pi (sqr r)))

(area 4)
(area 6)

;; ====================

;; ListOfString -> Boolean
;; produce true if los includes "UBC"
(check-expect (contains-ubc? empty) false)
(check-expect (contains-ubc? (cons "McGill" empty)) false)
(check-expect (contains-ubc? (cons "UBC" empty)) true)
(check-expect (contains-ubc? (cons "McGill" (cons "UBC" empty))) true)

;(define (contains-ubc? los) false) ;stub

;<template from ListOfString>

(define (contains-ubc? los)
  (cond [(empty? los) false]
        [else
         (if (string=? (first los) "UBC")
             true
             (contains-ubc? (rest los)))]))

;; ListOfString -> Boolean
;; produce true if los includes "McGill"
(check-expect (contains-mcgill? empty) false)
(check-expect (contains-mcgill? (cons "UBC" empty)) false)
(check-expect (contains-mcgill? (cons "McGill" empty)) true)
(check-expect (contains-mcgill? (cons "UBC" (cons "McGill" empty))) true)

;(define (contains-mcgill? los) false) ;stub

;<template from ListOfString>

(define (contains-mcgill? los)
  (cond [(empty? los) false]
        [else
         (if (string=? (first los) "McGill")
             true
             (contains-mcgill? (rest los)))]))


;; String ListOfString -> Boolean
;; produce true if los includes s
(check-expect (contains? "UBC" empty) false)
(check-expect (contains? "UBC" (cons "McGill" empty)) false)
(check-expect (contains? "UBC" (cons "UBC" empty)) true)
(check-expect (contains? "UBC" (cons "McGill" (cons "UBC" empty))) true)
(check-expect (contains? "UBC" (cons "UBC" (cons "McGill" empty))) true)
(check-expect (contains? "Toronto" (cons "UBC" (cons "McGill" empty))) false)

(define (contains? s los)
  (cond [(empty? los) false]
        [else (if (string=? s (first los))
                  true
                  (contains? s (rest los)))]))

;; ====================

;; ListOfNumber -> ListOfNumber
;; produce list of sqr of every number in lon
(check-expect (squares empty) empty)
(check-expect (squares (list 3 4)) (list 9 16))

;(define (squares lon) empty) ;stub

;<template from ListOfNumber>

(define (squares lon)
  (cond [(empty? lon) empty]
        [else
         (cons (sqr (first lon))
               (squares (rest lon)))]))

;; ListOfNumber -> ListOfNumber
;; produce list of sqrt of every number in lon
(check-expect (square-roots empty) empty)
(check-expect (square-roots (list 9 16)) (list 3 4))

;(define (square-roots lon) empty) ;stub

;<template from ListOfNumber>
#;
(define (square-roots lon)
  (cond [(empty? lon) empty]
        [else
         (cons (sqrt (first lon))
               (square-roots (rest lon)))]))

;; (X -> Y) (listof X) -> (listof Y)
;; given fn and (listof x0 x1 ...) produce (listof (fn x0) (fn x1))
(check-expect (map2 string-length (list "ab" "c" "def")) (list 2 1 3))

(define (map2 fn lon)
  (cond [(empty? lon) empty]
        [else (cons (fn (first lon))
                    (map2 fn (rest lon)))]))

(define (square-roots lon)
  (map2 sqrt lon))

;; ====================

;; ListOfNumber -> ListOfNumber
;; produce list with only positive? elements of lon
(check-expect (positive-only empty) empty)
(check-expect (positive-only (list 1 -2 3 -4)) (list 1 3))

;(define (positive-only lon) empty) ;stub

;<template from ListOfNumber>
#;
(define (positive-only lon)
  (cond [(empty? lon) empty]
        [else
         (if (positive? (first lon))
             (cons (first lon)
                   (positive-only (rest lon)))
             (positive-only (rest lon)))]))


;; ListOfNumber -> ListOfNumber
;; produce list with only negative? elements of lon
(check-expect (negative-only empty) empty)
(check-expect (negative-only (list 1 -2 3 -4)) (list -2 -4))

;(define (negative-only lon) empty) ;stub

;<template from ListOfNumber>
#;
(define (negative-only lon)
  (cond [(empty? lon) empty]
        [else
         (if (negative? (first lon))
             (cons (first lon)
                   (negative-only (rest lon)))
             (negative-only (rest lon)))]))

;; (X -> Boolean) (listof X) -> (listof X)
;; filter lox to contain only those elements for which the pred preduces true
(define (filter2 fn lox)
  (cond [(empty? lox) empty]
        [else (if (fn (first lox))
                  (cons (first lox)
                        (filter2 fn (rest lox)))
                  (filter2 fn (rest lox)))]))

(define (positive-only lon) (filter2 positive? lon))
(define (negative-only lon) (filter2 negative? lon))
