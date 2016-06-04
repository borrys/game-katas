(ns game-katas.input
  (:require [game-katas.player :as p]))

(defn turn-and-move [dir]
  (fn [player] (-> player (p/turn dir) (p/move dir))))

(defn- handler [key]
  (condp = key
    (keyword " ") {:value #(rem (inc %) 100)}

    :up {:player (turn-and-move :up)}
    :down {:player (turn-and-move :down)}
    :left {:player (turn-and-move :left)}
    :right {:player (turn-and-move :right)}
    []))

(defn- update-part [st [prop f]]
  (update-in st [prop] f))

(defn on-key [state {key :key}]
  (reduce update-part state (handler key)))
