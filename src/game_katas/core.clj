(ns game-katas.core
  (:require [quil.core :as q]
            [quil.middleware :as m]

            [game-katas.level-map :refer [parse]]
            [game-katas.render :as render]))

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
      (assoc :dir :up)
      (assoc :pos [5 5])))

(defn move [pos]
  (update-in pos [:x] #(+ 5 %)))

(defn update-state [state]
  (if (= @version (:version state))
    state
    (setup)))

(defn handler [key]
  (condp = key
    (keyword " ") [[:value #(rem (inc %) 100)]]
    :up {:pos #(map + % [0 -1]) :dir (fn [_] :up)}
    :down {:pos #(map + % [0 1]) :dir (fn [_] :down)}
    :left {:pos #(map + % [-1 0]) :dir (fn [_] :left)}
    :right {:pos #(map + % [1 0]) :dir (fn [_] :right)}
    []))

(defn key-pressed [state {key :key}]
  (let [ops (handler key)]
    (reduce (fn [s [prop f]] (update-in s [prop] f))
            state
            ops)))

(defn draw [state]
  (q/background 200 200 200)

  (render/world (:world state))
  (render/start (:start state))
  (render/end (:end state))
  (render/progress (:value state) [1 9])
  (render/arrow (:pos state) (:dir state))
)

(defn start []
  (q/defsketch my-sketch
    :title "Hello, quil!"
    :size [800, 600]
    :setup setup
    :update update-state
    :draw draw
    :key-pressed key-pressed
    :middleware [m/fun-mode]))

(start)
