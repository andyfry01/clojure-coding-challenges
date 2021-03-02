(ns second-cheapest
  (:require [odoyle.rules :as o]))

;; The Frugal Gentleman

;; George likes to appear generous, but he’s also very frugal, especially when he buys wine. Here’s his strategy: if there are two or more wines to choose from, he buys the second cheapest. That way, he doesn’t appear to buy the cheapest, but he avoid buying the more expensive options. Write a function that takes a collection of wines and returns the name of George’s choice.

;; Examples

(def ex1 [{:name "White" :price 10.99}
          {:name "Red"   :price 8.74}
          {:name "Rosé"  :price 12.22}]) ;=> "White"

(def ex2 [{:name "White" :price 10.99}]) ;=> "White"

(def ex3 [{:name "White" :price 10.99}
          {:name "Rosé"  :price 12.22}]) ;=> "Rosé"

;; You gotta know the rules to break them
(def rules
  (o/ruleset
   {::wines
    [:what
     [id ::name name]
     [id ::price price]
     :then
      (->> (o/query-all o/*session* ::wines)
           (o/insert o/*session* ::derived ::all-wines)
           o/reset!)]}))

;; create session and add rulef
(def *session
  (atom (reduce o/add-rule (o/->session) rules)))

;; (def session *session)
;; insert time value
(defn tester [id name price]
  (swap!
   *session
   (fn [session]
     (-> session
         (o/insert id ::name name)
         (o/insert id ::price price)
         o/fire-rules))))

(tester 1 "Rose" 500)
(tester 2 "Red" 1000)
(tester 3 "Whie" 600)

#_(o/query-all @*session ::wines)

(o/query-all @*session ::all-wines)