(ns color-averaging)
;; RGB Color Mixing

;; Here’s an algorithm for mixing multiple RGB colors to create a single color.
;; 1) Separate the colors into Red, Green, and Blue components.
;; 2) Average all the Reds together, average all the Greens together, average all the Blues together.
;; 3) Put the average values back together into a resulting RGB.

;; Your task is to take a collection of RGB strings of the form "#FF021A", mix them like the algorithm above, and return the resulting RGB string.
;; Note that each RGB string contains two hexadecimal digits for each component. You can round the averages to integers however you want.

;; Examples
;; (mix ["#FFFFFF" "#000000"]) ;=> "#7F7F7F" or "#808080" depending on how you round
;; (mix ["#FF0000" "#00FF00" "#0000FF"]) ;=> "#555555"

;; How to convert a hex to a base 10 number? read-string!
(read-string "0xFF") ;; -> 255

(defn average
  "Util for averaging many numbers, and rounding them down to the nearest whole number"
  [& nums]
  (int (Math/floor (/ (apply + nums) (count nums)))))

(comment
  ;; average playground
  (average 5 10 15) ; -> 10
  )

(defn hex->numtriple 
  "Takes a hex number and turns it into a vector of base-10 numbers, e.g.  #FFFFFF -> [255 255 255]"
  [hex]
  (as-> hex $
    (drop 1 $)
    (partition 2 $)
    (mapv (fn [chars]
           (->> (apply str chars)
                (str "0x")
                (read-string))) $)))

(comment
  ;; hex->numtriple playground
  (hex->numtriple "#FEFEFE") ; -> [254 254 254]
  )

(defn numtriple->hex 
  "Takes a vector of base-10 numbers and turns them into a hex color, e.g.
   [255 255 255] -> #FFFFFF"
  [numtriple]
  (reduce str (cons "#" (map #(format "%02X" %) numtriple))))

(comment
  ;; numtriple->hex playground
  (numtriple->hex [254 254 254]) ; -> "#FEFEFE"
  )

(defn mix 
  "Magic happens here, e.g (mix '#FFFFFF' '#000000') -> '#7F7F7F'"
  [& stuff]
  (let [rgb-nums (map hex->numtriple stuff)]
    (numtriple->hex (apply map average rgb-nums))))

(comment
  ;; Function demos!
  ;;   
  (mix "#000000" "#FFFFFF") ; -> "#7F7F7F"
  (mix "#000000" "#7F7F7F" "#111111") ; -> "#303030"
  (mix "#000000") ; -> "#000000"
  )
