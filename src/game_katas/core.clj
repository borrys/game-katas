(ns game-katas.core
  (:require [quil.core :as q]
            [quil.middleware :as m]

            [game-katas.level-map :refer [parse]]
            [game-katas.render :as render]))

(def level
  ["S........##"
   "...##.#...#"
   ".#.#..#..#E"
   "##.####...."])


(defn setup []
  (parse level))

(defn move [pos]
  (update-in pos [:x] #(+ 5 %)))


(defn update-state [state]
  (update-in state [:pos] move))

(defn draw [state]
  (q/background 200 200 200)

  (render/world (:world state))
  (render/start (:start state))
  (render/end (:end state))
)

(defn start []
  (q/defsketch my-sketch
    :title "Hello, quil!"
    :size [800, 600]
    :setup setup
 ;  :update update-state
    :draw draw
    :middleware [m/fun-mode]))
