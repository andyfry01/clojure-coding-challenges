
(ns spec-names
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            #_[clojure.spec.gen.alpha]
            [clojure.test.check.generators]
            [clojure.pprint :as pp]
            [clojure.string :as str])
  (:gen-class))
;; A name is a sequence of terms separated by a space. It must have at least 2 terms. The last term must be a word.
;; A term is either an initial or a word.
;; An initial is a single capital letter followed by a period.
;; A word is a capital letter followed by one or more letters (upper or lower case) .

;; (def validnames ["Abraham Lincoln"
;;                  "George R. R. Martin"
;;                  "J. R. Bob Dobbs"
;;                  "H. G. Wells"])

;; (def invalidnames ["J R Tolkien"
;;                    "J. F. K."
;;                    "Franklin"])

;; (def demoMap {:hi "hello" :how "are you?"})

;; (defn isCapitalized? [string] (= string (str/capitalize string)))
;; (defn lenGt1? [string] (> (count string) 1))

;; (isCapitalized? "J")
;; (lenGt1? "hi")

;; (defn hasAtLeastTwoWords? [string] 
;;   (-> string
;;       (str/split ,,, #" ")
;;       count
;;       (>= ,,, 2)))

;; (hasAtLeastTwoWords? "hello beautiful world")

;; (s/def ::valid-name (s/and isCapitalized? lenGt1?))
;; (s/def ::separated-by-space (s/and string? hasAtLeastTwoWords?))

;; (s/conform ::valid-name "James")
;; (s/valid? ::separated-by-space "123 123 123")


;; (s/exercise ::valid-name 100 {::valid-name (:gen (s/gen (s/and string? lenGt1?)))})

;; (s/def ::stringg (s/and string? lenGt1?))

;; (def testname "Andy Duphraine")
;; (defn dothething [testname]
;;   (and (s/valid? ::valid-name testname) (s/valid? ::separated-by-space testname)))

;; (dothething "James Buchanan")

;; (def testname (first validnames))

;; (defn validname? [name] 
;;   (-> name
;;       (str/split ,,, #" ")
;;       ((fn [str] (map #(str/split % #"") str)))))

#_(s/valid? ::name (first (validname? testname)))



;; Basic initial exploration of spec 👇
(defn isgt10? [num] (> num 10))

(s/def ::big-num (s/and number? isgt10?))

(s/conform ::big-num 3)
(s/conform ::big-num 11)

(s/valid? ::big-num 3)
(s/valid? ::big-num 12)

(pp/pprint (s/explain-data ::big-num 3))
(pp/pprint (s/explain-data ::big-num "10"))




; 👆 here be dragons
; 
; 
; 
; 
; 


(ns spec-names
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

; input values
(def validnames ["Abraham Lincoln"
                 "George R. R. Martin"
                 "J. R. Bob Dobbs"
                 "H. G. Wells"])

(def invalidnames ["J R Tolkien"
                   "J. F. K."
                   "Franklin"])
; predicates
(defn isCapitalized? [string] (= string (str/capitalize string)))
(defn lenGt2? [string] (> (count string) 2))
(defn endsWithPeriod? [string] (= "." (str (last string))))

; specs
(s/def ::valid-name (s/and isCapitalized? lenGt2?))
(s/def ::valid-initial (s/and isCapitalized? endsWithPeriod?))
(s/def ::nameorinitial (s/or :name ::valid-name :initial ::valid-initial))

; validator
(defn name-validator [name]
  (let [splitname (str/split name #" ")
        lastname (last splitname)
        init-terms (drop-last splitname)]

    (every? true?
            [(s/valid? (s/+ ::nameorinitial) init-terms)
             (s/valid? ::valid-name lastname)])))

; run tests!
(mapv name-validator invalidnames)
(mapv name-validator validnames)