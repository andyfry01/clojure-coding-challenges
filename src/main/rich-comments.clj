(ns rich-comments
   (:require [clojure.string :as str]
             [clojure.pprint :refer [pprint]]))

;; Have the function LongestWord (sen) take the sen parameter being passed and return the largest word in the string. If there are two or more words that are the same length, return the first word from the string with that length. Ignore punctuation and assume sen will not be empty.

(defn get-longest-word [sentence] 
  (str/split sentence #" "))



(comment
  (do
    (def sentence-one "Return the spectacularly longest word.")
    (def sentence-two "It’s a story that encapsulates quite a lot about life in 2021: the democratization of financial markets, the mobilization of a giant online community, and the ability of obsessed amateurs to alter reality when they put their minds to it, especially when there isn’t much else to do.")
    (def long-british-words "Incomprehensibilities (21 letters) The plural form of incomprehensibility, this noun is used for things or events that are difficult to understand because they are complex or shrouded in mystery. For example, the Bermuda Triangle and disappearance of flight MH370 are incomprehensibilities of the modern age that may never be solved. Or for some students, the incomprehensibilities of science and math made them choose a social science major. Interdisciplinary (17 letters) This adjective is often used in non-traditional schools to describe a new yet growing approach to education. An interdisciplinary method of teaching uses two or more different subjects to explore a concept, like government systems or humans’ impact on biodiversity. These concepts are then covered in, for example, art class, social science, language arts and mathematics. An increasing number of schools in big Indonesian cities are adopting the interdisciplinary philosophy."))

  (def example-vec ["Return" "the" "spectacularly" "longest" "word."])

  (defn get-longest-word [sentence]
    (let [split-str (str/split sentence #" ")]
      (->> split-str
           (map-indexed (fn [i word]
                          {:index i
                           :count (count word)}))
           (reduce (fn [cur next]
                     (if (> (:count cur) (:count next)) cur next))
                   {:index -1 :count 0})
           (:index)
           (get split-str))))

  (pprint (get-longest-word sentence-one))
  (pprint (get-longest-word sentence-two))
  (pprint (get-longest-word long-british-words))
  ;; (get example-vec 0)


  (map #(count %) example-vec)
  (map identity example-vec)

  (interleave (map count example-vec) example-vec)


  (get-longest-word sentence-one)
  (get-longest-word sentence-two))
