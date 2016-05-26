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
