(ns filter-indexed)

;; Filter indexed

;; Clojure already has a function called filter. You pass it a predicate and it calls the predicate on each element. But what it doesn’t have is a function called filter-indexed that calls a predicate function with both the element and its index

(reduce (fn [acc next]
          (println acc)
          (conj acc next)) [1 2 3] [4 5 6])

{:acc [1 2 3]
 :cur-index 0}


([1 2 3] 2)

(def index 0)
(def collection [true])

(defn predicate [idx thing] (= thing true))

(defn filter-indexed
  ([predicate collection]
   (filter-indexed predicate collection 0))
  ([predicate collection index]
   (let [passes? (true? (predicate index (collection index)))]
     (cond
       (= index (- 1 (count collection))) (println collection)
       passes? (filter-indexed predicate collection (+ index 1))
       :else (filter-indexed predicate (rest collection) (+ index 1))))))

(defn test-pred [idx itm]
  (println idx)
  (println itm)
  (and (= itm "s") (= idx 6)))

(filter-indexed test-pred ["greetings ""hi" "hello"])


(def test-coll-2 ["one" "two" "three"])

(defn filter-indexed [predicate collection]
  (reduce-kv
   (fn [res idx itm] (if (predicate idx itm) (conj res itm) res))
   []
   collection))

(println (filter-indexed test-pred (seq "hello stuart")))