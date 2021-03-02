(ns eratosthenes)

(def starting-nums (range 1 121))
(def p (atom 2))
(def processed-numbers (atom []))

(defn adjust [idx item coll]
  (let [[before after] (split-at idx coll)]
    (vec (concat (drop-last before) [item] after))))

(def cur-number 2)
(def nums starting-nums)

(loop
 [nums starting-nums]
  (let [cur-number (first nums)]
    (when (= 0 (mod cur-number @p)) (adjust cur-number nil nums))
    (recur (next (adjust cur-number nil nums)))))
