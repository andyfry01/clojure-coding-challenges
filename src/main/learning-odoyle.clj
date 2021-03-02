(ns learning-odoyle
  (:require [odoyle.rules :as o]))

(def rules
  (o/ruleset
   {::print-time
    [:what
     [::time ::time-total tt]
     :then
     (println tt)]
    
    ::move-player
    [:what
     [::time ::total tt]
     :then
     (-> o/*session*
         (o/insert ::player ::x tt)
         o/reset!)]
    
    ::player
    [:what
     [::player ::x x]
     [::player ::y y]]}))

(def *session
  (atom (reduce o/add-rule (o/->session) rules)))

(swap! *session
       #(-> %
            (o/insert ::player ::x 21)
            (o/insert ::player ::y 13)
            (o/insert ::time ::time-total 100)
            #_(o/insert ::time ::total 100)
            (o/fire-rules)))

(o/query-all @*session ::player)
(o/query-all @*session ::print-time)

(def testatm (atom "hi"))
(reset! testatm "hello")
