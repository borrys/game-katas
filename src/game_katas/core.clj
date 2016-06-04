(ns game-katas.core
  (:require [quil.core :as q]
            [quil.middleware :as m]

            [game-katas.level-map :refer [parse]]
            [game-katas.render :as render]
            [game-katas.input :as input]))

(def level
  ["S........##"
   "...##.#...#"
   ".#.#..#..#E"
   "##.####..#."
   "...#......."])

(def version (atom 1))

(defn setup []
  (-> (parse level)
      (assoc :version @version)
      (assoc :value 80)
      (assoc-in [:player :dir] :up)
      (#(assoc-in % [:player :pos] (:start %)))))

(defn move [pos]
  (update-in pos [:x] #(+ 5 %)))

(defn update-state [state]
  (if (= @version (:version state))
    state
    (setup)))

(defn draw [state]
  (q/background 200 200 200)

  (render/world (:world state))
  (render/start (:start state))
  (render/end (:end state))
  (render/progress (:value state) [1 9])
  (render/arrow (:pos (:player state)) (:dir (:player state)))
)

(defn start []
  (q/defsketch my-sketch
    :title "Hello, quil!"
    :size [800, 600]
    :setup setup
    :update update-state
    :draw draw
    :key-pressed input/on-key
    :middleware [m/fun-mode]))

(start)
