(ns game-katas.level-map)

(defn- cell-type [c]
  (condp = c
    \. :cells
    nil))

(defmulti state (fn [a b c] a))
(defmethod state :cells [t x y] {:cells #{[x y]}})
(defmethod state :default [_ _ _] {})

(defn- cell-state [c x y]
  (-> (cell-type c)
      (state x y)))

(defn- combine-state [a b]
  (let [ca (:cells a)
        cb (:cells b)
        cs (clojure.set/union ca cb)]
    {:cells cs}))

(defn- parse-line [y cs]
  (reduce combine-state {} (map-indexed (fn [x c] (cell-state c x y)) cs))
  )

(defn parse [lines]
  (->> lines
       (map-indexed parse-line)
       (reduce combine-state {})))
