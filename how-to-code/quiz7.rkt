;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname quiz7) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct node (key val l r))

;; BST is one of:
;; - false
;; - (make-node Integer String BST BST)
;; interp. false means no BST, or empty BST
;;         key is the node key
;;         val is the node val
;;         l and r are left and right subtrees
;; INVARIANT: for a given node:
;;     key is > all keys in its l(eft)  child
;;     key is < all keys in its r(ight) child
;;     the same key never appears twice in the tree

(define BSTA (make-node 6 "a" false false))
(define BSTC (make-node 6 "a"
                        (make-node 4 "t"
                                   (make-node 3 "h" false false)
                                   (make-node 5 "j" false false))
                        (make-node 8 "v" false false)))
(define BSTD false)

#;
(define (fn-for-bst bst)
  (cond [(false? bst) (...)]
        [else (... (node-key bst)
                   (node-val bst)
                   (fn-for-bst (node-l bst))
                   (fn-for-bst (node-r bst)))]))


;; BST -> ListOfInteger
;; present the keys in the binary search tree in descending order as a list
(check-expect (tree-to-list false) empty)
(check-expect (tree-to-list (make-node 4 "e" 
                                       (make-node 1 "o" false false)
                                       (make-node 7 "p" false false)))
              (list 7 4 1))
(check-expect (tree-to-list (make-node 6 "a"
                                       (make-node 4 "b"
                                                  (make-node 1 "v" false false)
                                                  (make-node 5 "s" false false))
                                       (make-node 9 "q" 
                                                  false
                                                  (make-node 10 "x" false false))))
              (list 10 9 6 5 4 1))

;(define (tree-to-list bt) empty)   ;stub


(define (tree-to-list bst)
  (cond [(false? bst) empty]
        [else (append (tree-to-list (node-r bst))
                      (list (node-key bst))
                      (tree-to-list (node-l bst)))]))


; ========== ;


(define-struct junction (left straight right))
       
;; Maze is one of:
;; - false
;; - "finish"
;; - (make-junction Maze Maze Maze)
;; a maze where at each junction you can either go straight, left or right. 
;; false means dead end, "finish" means you've reached the end of the maze
(define M0 false)      ; a maze that is a dead end
(define M1 "finish")   ; a maze where you are already at the finish
(define M2 (make-junction 
            (make-junction false 
                           (make-junction false
                                          false 
                                          (make-junction false false false))
                           (make-junction false
                                          (make-junction (make-junction false false false)
                                                         (make-junction false "finish" false)
                                                         false)
                                          false))
            (make-junction false 
                           false 
                           (make-junction false false false))
            false))    ; a maze

(define (fn-for-maze m)
  (cond [(false? m) (...)]
        [(and (string? m) (string=? m "finish")) (...)]
        [else (... (fn-for-maze (junction-left m))
                   (fn-for-maze (junction-straight m))
                   (fn-for-maze (junction-right m)))]))

