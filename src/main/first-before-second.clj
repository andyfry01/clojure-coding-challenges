(ns first-before-second
  (:require [clojure.string :as str]))

;; First before second

;; Write a function that takes a string and two letters. The function should return true if every instance of the first letter comes before every instance of the second letter.

;; Examples

(def str1 "A rabbit jumps joyfully") 
(def letters1 ["a" "j"]) ;=> true

(def str2 "A jolly rabbit jumps joyfully") 
(def letters2 ["a" "j"]) ;=> false

(def str3 "Don't tread on me") 
(def letters3 ["t" "m"]) ;=> true

(def str4 "Every React podcast") 
(def letters4 ["t" "d"]) ;=> false

(defn first-before? [string letters]
  (let [pre-processed-letters (-> string
                                 (str/split ,,, #"")
                                 ((fn [vec] (filter #(not (= % " ")) vec)))
                                 (#(map str/lower-case %)))]
    (->> pre-processed-letters
         (drop-while #(not (= % (letters 1))))
         ((fn [list] (contains? (vec list) (letters 0)))))))

(first-before? str3 letters3)

(as-> "coding and coffe" thingy
  (str thingy ", it is a show about programming")
  (str "you should check out " thingy))
    
  
  
  

