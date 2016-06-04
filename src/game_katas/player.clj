(ns game-katas.player)

(def ^:private move-vector
  {:up [0 -1]
   :down [0 1]
   :left [-1 0]
   :right [1 0]})

(defn move [player dir]
  (update-in player [:pos] #(map + (move-vector dir) %)))

(defn turn [player dir]
  (assoc-in player [:dir] dir))
