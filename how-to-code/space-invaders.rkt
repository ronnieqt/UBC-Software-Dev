;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname space-invaders) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require 2htdp/universe)
(require 2htdp/image)

;; Space Invaders


;; Constants:

(define WIDTH  300)
(define HEIGHT 500)

(define INVADER-X-SPEED 1.5)  ;speeds (not velocities) in pixels per tick
(define INVADER-Y-SPEED 1.5)
(define TANK-SPEED 2)
(define MISSILE-SPEED 10)

(define HIT-RANGE 10)

(define INVADE-RATE 100)

(define BACKGROUND (empty-scene WIDTH HEIGHT))

(define INVADER
  (overlay/xy (ellipse 10 15 "outline" "blue")              ;cockpit cover
              -5 6
              (ellipse 20 10 "solid"   "blue")))            ;saucer

(define TANK
  (overlay/xy (overlay (ellipse 28 8 "solid" "black")       ;tread center
                       (ellipse 30 10 "solid" "green"))     ;tread outline
              5 -14
              (above (rectangle 5 10 "solid" "black")       ;gun
                     (rectangle 20 10 "solid" "black"))))   ;main body

(define TANK-HEIGHT/2 (/ (image-height TANK) 2))

(define MISSILE (ellipse 5 15 "solid" "red"))



;; Data Definitions:

(define-struct game (invaders missiles tank))
;; Game is (make-game  (listof Invader) (listof Missile) Tank)
;; interp. the current state of a space invaders game
;;         with the current invaders, missiles and tank position

;; Game constants defined below Missile data definition

#;
(define (fn-for-game s)
  (... (fn-for-loinvader (game-invaders s))
       (fn-for-lom (game-missiles s))
       (fn-for-tank (game-tank s))))


(define-struct tank (x dir))
;; Tank is (make-tank Number Integer[-1, 1])
;; interp. the tank location is x, HEIGHT - TANK-HEIGHT/2 in screen coordinates
;;         the tank moves TANK-SPEED pixels per clock tick left if dir -1, right if dir 1

(define T0 (make-tank (/ WIDTH 2) 1))   ;center going right
(define T1 (make-tank 50 1))            ;going right
(define T2 (make-tank 50 -1))           ;going left

#;
(define (fn-for-tank t)
  (... (tank-x t)
       (tank-dir t)))


(define-struct invader (x y dx))
;; Invader is (make-invader Number Number Number)
;; interp. the invader is at (x, y) in screen coordinates
;;         the invader along x by dx pixels per clock tick

(define I1 (make-invader 150 100 12))           ;not landed, moving right
(define I2 (make-invader 150 HEIGHT -10))       ;exactly landed, moving left
(define I3 (make-invader 150 (+ HEIGHT 10) 10)) ;> landed, moving right

#;
(define (fn-for-invader invader)
  (... (invader-x invader)
       (invader-y invader)
       (invader-dx invader)))


;; ListOfInvader is one of:
;;  - empty
;;  - (cons Invader ListOfInvader)
;; interp. a list of invaders

(define LOI0 empty)
(define LOI1 (list I1))
(define LOI2 (list I1 I2 I3))

#;
(define (fn-for-loinvader loi)
  (cond [(empty? loi) (...)]
        [else (... (fn-for-invader (first loi))
                   (fn-for-loinvader (rest loi)))]))

;; Template Rules used:
;; - one-of: 2 cases
;; - atomic distinct: empty
;; - compound: (cons Invader ListOfInvader)
;; - reference: (first loi) is Invader
;; - self reference: (rest loi) is ListOfInvader


(define-struct missile (x y))
;; Missile is (make-missile Number Number)
;; interp. the missile's location is x y in screen coordinates

(define M1 (make-missile 150 300))                               ;not hit U1
(define M2 (make-missile (invader-x I1) (+ (invader-y I1) 10)))  ;exactly hit U1
(define M3 (make-missile (invader-x I1) (+ (invader-y I1)  5)))  ;> hit U1

#;
(define (fn-for-missile m)
  (... (missile-x m)
       (missile-y m)))


;; ListOfMissile is one of:
;;  - empty
;;  - (cons Missile ListOfMissile)
;; interp. a list of missiles

(define LOM0 empty)
(define LOM1 (list M1))
(define LOM2 (list M1 M2 M3))

#;
(define (fn-for-lom lom)
  (cond [(empty? lom) (...)]
        [else (... (fn-for-missile (first lom))
                   (fn-for-lom (rest lom)))]))

;; Template Rules used:
;; - one-of: 2 cases
;; - atomic distinct: empty
;; - compound: (cons Missile ListOfMissile)
;; - reference: (first lom) is Missile
;; - self reference: (rest lom) is ListOfMissile


(define G0 (make-game empty empty T0))
(define G1 (make-game empty empty T1))
(define G2 (make-game (list I1) (list M1) T1))
(define G3 (make-game (list I1 I2) (list M1 M2) T1))


;; Functions:

;; ========================= ;;
;; === Functions on Game === ;;
;; ========================= ;;

;; Game -> Game
;; start the game with (main ...)  TODO
(define (main g)
  (big-bang g                 ; Game
    (on-tick   advance-game)  ; Game -> Game
    (to-draw   render-game)   ; Game -> Image
    (stop-when stop-game)))     ; Game -> Boolean
;(on-key    handle-key)))  ; Game KeyEvent -> Game


;; Game -> Game
;; produce the next state of the game
;; TODO
; between left and right corner
(check-expect (advance-game (make-game empty empty (make-tank 50 1)))
              (make-game empty empty (make-tank (+ 50 (* 1 TANK-SPEED)) 1)))
(check-expect (advance-game (make-game empty empty (make-tank 50 -1)))
              (make-game empty empty (make-tank (+ 50 (* -1 TANK-SPEED)) -1)))
; at the left corner
(check-expect (advance-game (make-game empty empty (make-tank 0 -1)))
              (make-game empty empty (make-tank 0 -1)))
(check-expect (advance-game (make-game empty empty (make-tank 0 1)))
              (make-game empty empty (make-tank (+ 0 (* 1 TANK-SPEED)) 1)))

(define (advance-game g) g)  ; stub







;; Game -> Image
;; TODO
(define (render-game g) BACKGROUND)  ; stub



;; Game -> Boolean
;; TODO
(define (stop-game g) false)  ; stub





;; --------------- ;;

;; ========================= ;;
;; === Functions on Tank === ;;
;; ========================= ;;

;; --------------- ;;

;; Tank -> Tank
;; produce the next state of the tank
; between the left and right corner
(check-expect (advance-tank (make-tank 50 1))
              (make-tank (+ 50 (* 1 TANK-SPEED)) 1))
(check-expect (advance-tank (make-tank 50 -1))
              (make-tank (+ 50 (* -1 TANK-SPEED)) -1))
; at the left corner
(check-expect (advance-tank (make-tank 0 1))
              (make-tank (+ 0 (* 1 TANK-SPEED)) 1))
(check-expect (advance-tank (make-tank 0 -1))
              (make-tank 0 -1))
; at the right corner
(check-expect (advance-tank (make-tank WIDTH 1))
              (make-tank WIDTH 1))
(check-expect (advance-tank (make-tank WIDTH -1))
              (make-tank (+ WIDTH (* -1 TANK-SPEED)) -1))

;(define (advance-tank t) t)  ; stub

; use <fn-for-tank> template
(define (advance-tank t)
  (make-tank (max 0 (min WIDTH (+ (tank-x t) (* (tank-dir t) TANK-SPEED))))
             (tank-dir t)))

;; --------------- ;;

;; Tank -> Image
;; place tank image on the BACKGROUND at (tank-x t) and TANK-HEIGHT/2
(check-expect (render-tank (make-tank 50 1))
              (place-image TANK 50 (- HEIGHT TANK-HEIGHT/2) BACKGROUND))
(check-expect (render-tank (make-tank 100 -1))
              (place-image TANK 100 (- HEIGHT TANK-HEIGHT/2) BACKGROUND))

;(define (render-tank t) BACKGROUND)  ; stub

; use <fn-for-tank> template
(define (render-tank t)
  (place-image TANK (tank-x t) (- HEIGHT TANK-HEIGHT/2) BACKGROUND))

;; --------------- ;;

;; Tank KeyEvent -> Tank
;; change tank's direction to -1 when the left  arrow key is pressed
;; change tank's direction to  1 when the right arrow key is pressed
(check-expect (handle-key-tank (make-tank 50  1) "left") (make-tank 50 -1))
(check-expect (handle-key-tank (make-tank 50 -1) "left") (make-tank 50 -1))
(check-expect (handle-key-tank (make-tank 50  1) "right") (make-tank 50 1))
(check-expect (handle-key-tank (make-tank 50 -1) "right") (make-tank 50 1))
(check-expect (handle-key-tank (make-tank 50  1) "a") (make-tank 50  1))
(check-expect (handle-key-tank (make-tank 50 -1) "a") (make-tank 50 -1))


;(define (handle-key-tank t ke) t)  ; stub

; template for a mouse handler function
(define (handle-key-tank t ke)
  (cond [(key=? ke "left") (make-tank (tank-x t) -1)]
        [(key=? ke "right") (make-tank (tank-x t) 1)]
        [else (make-tank (tank-x t) (tank-dir t))]))

;; --------------- ;;

;; ============================ ;;
;; === Functions on Missile === ;;
;; ============================ ;;

;; --------------- ;;

;; Missile -> Missile
;; produce the next state of the missile (moves up at MISSILE-SPEED)
(check-expect (advance-missile (make-missile 150 300))
              (make-missile 150 (- 300 MISSILE-SPEED)))
(check-expect (advance-missile (make-missile 100 0))
              (make-missile 100 (- 0 MISSILE-SPEED)))
(check-expect (advance-missile (make-missile 100 HEIGHT))
              (make-missile 100 (- HEIGHT MISSILE-SPEED)))

;(define (advance-missile m) m)  ; stub

; use <fn-for-missile> template
(define (advance-missile m)
  (make-missile (missile-x m) (- (missile-y m) MISSILE-SPEED)))

;; --------------- ;;

;; Missile -> Image
;; place tank image on the BACKGROUND at (missile-x m) and (missile-y m)
(check-expect (render-missile (make-missile 150 300))
              (place-image MISSILE 150 300 BACKGROUND))

;(define (render-missile m) BACKGROUND)  ; stub

; use <fn-for-missile> template
(define (render-missile m)
  (place-image MISSILE (missile-x m) (missile-y m) BACKGROUND))

;; --------------- ;;

;; ================================== ;;
;; === Functions on ListOfMissile === ;;
;; ================================== ;;

;; --------------- ;;










;; --------------- ;;

;; ============================ ;;
;; === Functions on Invader === ;;
;; ============================ ;;

;; --------------- ;;

;; Invader -> Invader
;; produce the next state of the invader
;;  - move at a 45 degree angle
;;  - when hit a wall, bounce off and continue at a 45 degree angle in the other direction
; general cases
(check-expect (advance-invader (make-invader 150 100 12))
              (make-invader (+ 150 (* INVADER-X-SPEED 12))
                            (+ 100 (* INVADER-Y-SPEED 12)) 12))
(check-expect (advance-invader (make-invader 150 100 -12))
              (make-invader (+ 150 (* INVADER-X-SPEED -12))
                            (+ 100 (* INVADER-Y-SPEED 12)) -12))
; at left edge
(check-expect (advance-invader (make-invader 0 100 12))
              (make-invader (+ 0 (* INVADER-X-SPEED 12))
                            (+ 100 (* INVADER-Y-SPEED 12)) 12))
(check-expect (advance-invader (make-invader 0 100 -12))
              (make-invader (+ 0 (* INVADER-X-SPEED 12))
                            (+ 100 (* INVADER-Y-SPEED 12)) 12))
; at right edge
(check-expect (advance-invader (make-invader WIDTH 100 12))
              (make-invader (+ WIDTH (* INVADER-X-SPEED -12))
                            (+ 100 (* INVADER-Y-SPEED 12)) -12))
(check-expect (advance-invader (make-invader WIDTH 100 -12))
              (make-invader (+ WIDTH (* INVADER-X-SPEED -12))
                            (+ 100 (* INVADER-Y-SPEED 12)) -12))

;(define (advance-invader i) i)  ; stub

; use <fn-for-invader> template
; <template as function composition>
(define (advance-invader invader)
  (if (or (< (invader-next-x invader) 0) (> (invader-next-x invader) WIDTH))
      (advance-invader (make-invader (invader-x invader)
                                     (invader-y invader)
                                     (- (invader-dx invader))))
      (make-invader (invader-next-x invader)
                    (invader-next-y invader)
                    (invader-dx invader))))

;; --------------- ;;

;; Invader -> Number
;; produce the next x-coordinate of the invader (without boundary check)
(check-expect (invader-next-x (make-invader 150 100 12))
              (+ 150 (* INVADER-X-SPEED 12)))
(check-expect (invader-next-x (make-invader 150 100 -12))
              (+ 150 (* INVADER-X-SPEED -12)))

;(define (invader-next-x invader) 0)  ; stub

; use <fn-for-invader> tempalte
(define (invader-next-x invader)
  (+ (invader-x invader)
     (* INVADER-X-SPEED (invader-dx invader))))

;; --------------- ;;

;; Invader -> Number
;; produce the next y-coordinate of the invader
(check-expect (invader-next-y (make-invader 150 100 12))
              (+ 100 (* INVADER-Y-SPEED 12)))
(check-expect (invader-next-y (make-invader 150 100 -12))
              (+ 100 (* INVADER-Y-SPEED 12)))

;(define (invader-next-y invader) 0)  ; stub

; use <fn-for-invader> tempalte
(define (invader-next-y invader)
  (+ (invader-y invader)
     (* INVADER-Y-SPEED (abs (invader-dx invader)))))

;; --------------- ;;

;; Invader -> Image
;; place invader image on the BACKGROUND at (invader-x invader) and (invader-y invader)
(check-expect (render-invader (make-invader 150 100 12))
              (place-image INVADER 150 100 BACKGROUND))

;(define (render-invader invader) BACKGROUND)  ; stub

; use <fn-for-invader> template
(define (render-invader invader)
  (place-image INVADER (invader-x invader) (invader-y invader) BACKGROUND))

;; --------------- ;;

