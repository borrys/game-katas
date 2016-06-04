(ns game-katas.render
  (:require [quil.core :as q]))

(def tile-size 50)
(def corners (/ tile-size 5))
(def point-size (* tile-size 0.8))
(def half-tile (/ tile-size 2))

(defn path-tile [[x y]]
  (q/fill 255 255 255)
  (q/rect (* tile-size x) (* tile-size y) tile-size tile-size corners))

(defn world [ts]
  (doseq [t ts] (path-tile t)))

(defn point [[x y] color]
  (apply q/fill color)
  (q/ellipse (+ half-tile (* tile-size x)) (+ half-tile (* tile-size y)) point-size point-size))

(defn start [p] (point p [100 200 100]))

(defn end [p] (point p [100 100 200]))

(defn multiply [[x y] [n01 n02 n11 n12]]
  [(+ (* x n01) (* y n11))
   (+ (* x n02) (* y n12))])

(defn triangle [size angle]
  (let [hf (- (/ size 2))
        nhf (- hf)
        base-points [[0 hf]
                     [nhf nhf]
                     [hf nhf]]
        cos (Math/cos angle)
        sin (Math/sin angle)
        rot-matrix [cos (- sin)
                    sin cos]]
    (map #(multiply % rot-matrix) base-points)
    )
  )

(defn dir-to-angle [dir]
  (let [right-angle (/ Math/PI 2)]
    (* right-angle 
       (case dir
         :up 0
         :down 2
         :left 1
         :right 3))))

(defn arrow [[x y] direction]
  (println direction)
  (q/fill 250 100 100)
  (let [angle (dir-to-angle direction)
        size tile-size
        sx (+ half-tile (* tile-size x))
        sy (+ half-tile  (* tile-size y))
        ]
    (->> (triangle size angle)
         (map #(vector (+ sx (first %)) (+ sy (second %))))
         (flatten)
         (apply q/triangle))))

(defn progress [val [x y]]
  (let [width (/ val 10)]
    (q/fill 200 200 200)
    (q/rect (* tile-size x) (* tile-size y) (* tile-size 10) half-tile )
    (q/fill 100 100 250)
    (q/rect (* tile-size x) (* tile-size y) (* tile-size width) half-tile )

    ))

(let [angle (dir-to-angle :up)
      size tile-size
      sx (+ half-tile (* tile-size 1))
      sy (+ half-tile  (* tile-size 2))]
    (->> (triangle size angle)
         (map #(vector (+ sx (first %)) (+ sy (second %))))
         (flatten)
         (map #(/ tile-size %))))
