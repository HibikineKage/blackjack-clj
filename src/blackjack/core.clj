(ns blackjack.core
  (:gen-class)
  (:require [clojure.math.combinatorics :as combo])
  (:use clojure.test))

(defn -main "I don't do a whole lot ... yet." [& args] (println "Hello, World!"))
(def card-numbers (range 1 14))

(def card-marks #{:heart :club :diamond :spade})

(defn gen-card [num mark] {:mark mark, :number num})

(defn cross-seq [f colls] (map #(apply f %) (apply combo/cartesian-product colls)))

(def a (cross-seq + [[1 2 3] [4 5 6]])) ;[5 6 7 6 7 8 7 8 9]

(defn apply-n [f a n] (reduce #(%2 %1) a (take n (repeat f))))
(defn gen-deck [] (shuffle (cross-seq gen-card [card-numbers card-marks])))
(defn gen-game [] {:deck (gen-deck),
                   :player [],
                   :host [],
                   :trash []})
(defn move-card [game from to]
  (assoc game
         from (rest (game from))
         to (conj (game to)
                  (first (game from)))))
(defn draw-player [game] (move-card game :deck :player))
(defn draw-host [game] (move-card game :deck :host))
(defn init-game [] (apply-n draw-host
                            (apply-n draw-player (gen-game) 2)
                            2))
(init-game)
(defn card-to-num
  "Convert card to number."
  [card] (min 10 (card :number)))
(defn max-sum [cards] (apply + (map card-to-num cards)))
(defn host-need-to-draw
  "Returns that the host need to draw."
  [game] (> 17 (max-sum (game :host))))
;(deftest host-need-to-draw-test
;  (testing "host-need-to-draw "
;    (testing "returns true when host value is under 17."
;      (are [cards] (= true (host-need-to-draw {:host cards}))
;        [{:number 5} {:number 6}]
;        [{:number 10} {:number 6}]))))
;(defn init-game [game] {})
()

;(defn gen-seq [n] (take n (inc 0)))

