(ns experiments)


(def atm-images {:row1-col1 "pictures.com/image.jpg"})


(def testrow "row1")
(def testcol "col1")

(defn join-keywords [row col]
  (keyword (str row "-" col)))

(join-keywords testrow testcol)

(defn window-pane-block [rowidx]
  [:img {:src ((keyword rowidx)@atm-images)}]
  [:img {:src (atm-images)}])

(def atm-images (atom {}))

{
 :row-1-col1 {:img-url "hi"}}


(conj {} [:row1-col1 {:src "test"}])

(defn add-photo [rowidx colidx img-url]
  (println "adding pic")
  (swap! atm-images conj {:img-url img-url :row rowidx :col colidx}))



(def test-img-map {#_#_:row0 "combinators rock"
                   :row0-col0 {:img-url "http://localhost:3000/guy-running.png"}
                   :row0-col1 {:img-url "http://localhost:3000/tyler.png"}
                   :row1-col0 {:img-url "http://localhost:3000/guy-running.png"}
                   :row2-col0 {:img-url "http://localhost:3000/guy-running.png"}})
                   



(defn find-key
  [regex-pattern string]
  (not (nil? (re-find regex-pattern (name string)))))

(defn delete-layout-block [rowidx]
  (as-> rowidx thingy
    (filter #(find-key
              (re-pattern (name (keyword (str "row" thingy))))
              %)
            (keys test-img-map))
    (apply dissoc test-img-map thingy)))
  
  
(delete-layout-block [0])



(find-key #"a" "hello andy")