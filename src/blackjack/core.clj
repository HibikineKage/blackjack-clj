(ns blackjack.core (:gen-class) (:require [clojure.math.combinatorics]))

(defn -main "I don't do a whole lot ... yet." [& args] (println "Hello, World!"))

(def card-numbers (range 1 14))

(def card-marks (:heart :club :diamond :spade))

(defn gen-card [num mark] {:mark mark, :num num})

(defn cross-seq [f colls] (map (cartesian-product colls) #(apply f %)))

;(defn cross-seq 


;      ([f colls] (cross-seq f colls []))


;      ([f colls coll]


;                (if (= colls nil) (conj coll (f)) 


;                                  (reduce #(cross-seq (partial f %2)


;                                                      (next colls)


;                                                      %1)


;                                          coll


;                                          (first colls)))))


(defn cross-seq [f colls] (map))

(def a (cross-seq + [[1 2 3] [4 5 6]])) ;[5 6 7 6 7 8 7 8 9]


(defn gen-deck [] (cross-seq gen-card [card-numbers card-marks]))

(defn gen-seq [n] (take n (inc 0)))

(a)