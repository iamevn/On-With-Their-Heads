(ns wordgame.core-test
  (:require [clojure.test :refer :all]
            [wordgame.core :refer :all]))


(defn getFirstWord [str]
  "extracts the first word from the given string"
  (subs str 0 (.indexOf str " ")))

(defn rmChar [str ch]
  "removes instances of ch from str"
  (.replace str ch \space))

(defn splitOnSpaces [str]
  "splits str into a list of strings on spaces"
  (remove (fn [s]
            (= (.length s) 0))
          (clojure.string/split str #" "))
  )

(defn getHints [puzzStr]
  "extracts the hints from the given puzzle string
   returns them as a list of strings"
  (splitOnSpaces (clojure.string/trim (rmChar (rmChar (subs puzzStr (+ 2 (.lastIndexOf puzzStr ":"))) \,) \-)))
  )

(deftest a-test
  (testing "Generate a puzzle and try to solve it."
    (let [puzzle (-genPuzz)]
      (let [key (getFirstWord puzzle)
            hints (getHints puzzle)]
        (let [solution (-solvePuzzle hints)]
          (do (println puzzle)
              (println solution))
          (is (> (.indexOf solution key)
                 -1)))))))

