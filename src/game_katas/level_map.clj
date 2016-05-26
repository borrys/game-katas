(ns game-katas.level-map)

(defn- cell-type [c]
  (condp = c
    \. :cell
    \S :start
    \E :end
    nil))

(defmulti state (fn [a b c] a))
(defmethod state :cell [t x y] {:world #{[x y]}})
(defmethod state :start [t x y] {:start [x y] :world #{[x y]}})
(defmethod state :end [t x y] {:end [x y] :world #{[x y]}})
(defmethod state :default [_ _ _] {})

(defn- cell-state [c x y]
  (-> (cell-type c)
      (state x y)))

(defmacro combine-part [f k a b]
  [k (list f (list k a) (list k b))])

(combine-part clojure.set/union :world {:world #{[1 2]}} {:world #{[3 4]}})

(defn- combine-state [a b]
  (into {} [(combine-part clojure.set/union :world a b)
            (combine-part or :start a b)
            (combine-part or :end a b)]))

(defn- parse-line [y cs]
  (reduce combine-state {} (map-indexed (fn [x c] (cell-state c x y)) cs))
  )

(defn parse [lines]
  (->> lines
       (map-indexed parse-line)
       (reduce combine-state {})))
