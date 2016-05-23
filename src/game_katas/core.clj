(ns game-katas.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  {:pos {:x 100 :y 100}
   :dir 1})

(defn move [pos]
  (update-in pos [:x] #(+ 5 %)))


(defn update-state [state]
  (update-in state [:pos] move))

(defn draw [{{x :x y :y} :pos}]
  (q/background 200 200 200)
  (q/rect x y 50 50 10))

(q/defsketch my-sketch
  :title "Hello, quil!"
  :size [800, 600]
  :setup setup
  :update update-state
  :draw draw
  :middleware [m/fun-mode])
