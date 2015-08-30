(ns wordgame.core-test
  (:require [clojure.test :refer :all]
            [wordgame.Puzzle :refer :all]
            [wordgame.PuzzleGen :refer :all]))


(deftest a-test
  (testing "Generate a puzzle and try to solve it."
    (let [puzzle (genPuzz)]
      (let [key (getFirstWord puzzle)
            hints (map (fn [s] (rmChar s \-))
                    (getHints puzzle))]
        (let [solution (solvePuzzle hints)]
          (do (println puzzle)
              (println hints)
              (println solution))
          (is (> (.indexOf solution key)
                 -1)))))))

