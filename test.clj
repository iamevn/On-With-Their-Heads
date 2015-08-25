;; Prefix length L
;;
;; Solving:
;; -------
;; Read words into hashmap and hashset
;;   map: ending -> list of words with that ending
;;   set: words
;;
;; given n word endings:
;;   print possibilities for each ending
;;   try each order of each possibility to test if they exist
;;   print those that work
;;
;;
;; Generating:
;; ----------
;; Read words into hashmap of prefixes to words
;;
;; given a word with length of n*L, n subset of integers:
;;   split word into L/n prefixes
;;   for each prefix:
;;     display possible words starting with that prefix
(ns puzz
  (:require [clojure.math.combinatorics :as combo]))

(def L 3)
(def dict "/usr/share/dict/words")

(defn getRandom [set]
  "gets a random element from a set"
  (rand-nth (seq set)))

(defn suffix [word]
  "word with first L characters cut out"
  (.toLowerCase (.substring word L)))

(defn prefix [word]
  "first L characters of word"
  (.toLowerCase (subs word 0 L)))

(use 'clojure.java.io)

(defn addDict [dictfile]
  "Read words from dictionary file into wordset, returns that set"
  (with-open [rdr (reader dictfile)]
    (apply hash-set
           (remove (fn [str] (or (re-find #"[-']" str)
                                 (not (.equals str (.toLowerCase str)))))
                   (line-seq rdr)))))

(def wordset (addDict dict))

(defn prefixmap [wordset]
  "Build map of prefixes to words from set of words"
  ;; Make a new hash-map
  ;; for each element in wordset
  ;; use first L characters as key
  ;; if key exists: cons element to value for that key
  ;; else insert key and the list containing element to map
  (defn buildMap [premap wordset]
    (let [word (first wordset)]
      (if word
        (if (> (.length word) L)
          (let [prefix (prefix word)]
            (recur (assoc premap prefix (cons word (get premap prefix)))
                   (rest wordset)))
          (recur premap (rest wordset)))
        premap)))
  (buildMap (hash-map) wordset))

(defn suffixmap [wordset]
  "Build a map of suffixes to words from a set of words"
  (defn buildMap [suffmap wordset]
    (let [word (first wordset)]
      (if word
        (if (> (.length word) L)
          (let [suffix (.toLowerCase (suffix word))]
            (recur (assoc suffmap suffix (cons word (get suffmap suffix)))
                   (rest wordset)))
          (recur suffmap (rest wordset)))
        suffmap)))
  (buildMap (hash-map) wordset))

(def premap (prefixmap wordset))
(def suffmap (suffixmap wordset))

(defn str-split [str L]
  "splits str into a list of (/ (.length str) L)
  substrings of length L and returns them in a list
  ex: (str-split \"foobar\" 4) => '(\"foob\" \"ar\")"
  (map (fn [idx]
         (.substring str
                     (* L idx)
                     (min (* L (+ idx 1))
                          (.length str))))
       (range (int (Math/ceil (/ (.length str) L)))))
  )

(def rnd (java.util.Random.))

(defn get-word-for-prefix [prefix]
  "returns a random word for the given prefix (uses premap)
  returns nil if no words for given prefix"
  (let [poss-words (get premap prefix)]
    (if poss-words
      (nth poss-words (.nextInt rnd (count poss-words)))
      nil)))

(defn generateForWord [word]
  "if word isn't length multiple of L return false
  split word into L length segments
  get an option for each segment
  if any segment doesn't have an option return false
  return in a list, each option"
  (if (not= (mod (.length word) L) 0)
    false
    (let [seg-list (str-split word L)
          res-list (map get-word-for-prefix seg-list)]
      (if (reduce (fn [a b] (and a b)) true res-list)
        res-list
        false))))

(defn generatePuzzles [n]
  "generates n puzzles by finding a random word
  of length L that forms a puzzle"
  ((fn [n strs]
     (if (<= n 0) strs
       (do
         (let [possWord (getRandom wordset)
               possPuzz (generateForWord possWord)]
           (if possPuzz 
             (recur (- n 1) 
                    (cons (str possWord " : "
                               (clojure.string/join ", " possPuzz) " : " 
                               (clojure.string/join ", "
                                      (map (fn [word] (.concat "-" (suffix word)))
                                           possPuzz))
                               ;; "\n"
                               )
                          strs))
             (recur n strs))))))
   n '()))

(defn genPuzz []
  "generate single puzzle (and outputs just its string)"
  (first (generatePuzzles 1)))

(defn permutations [s]
  "sequence of permutations of s"
  (combo/permutations s))

(defn cart-prod [ls]
  "for each sublist in the list, interleave the elements
  (combos '((a b) (1 2))) => '((a 1) (a 2) (b 1) (b 2))"
  (apply combo/cartesian-product ls))

(defn findSolution [wordLst]
  "takes a list of words and uses their prefixes to try
  to find another word:
  (findSolution '(\"alacrity\" \"terminus\" \"bassinet\")) -> '(\"alabaster\" () () ())"
  (let [prefixLst (map (fn [word] (.substring word 0 L)) wordLst)
        possStrings (map (fn [strLst] (apply str strLst))
                         (permutations prefixLst))]
    (filter (fn [string] (get wordset string))
            possStrings)))

(defn solvePuzzle [clueLst]
  "--solver--
  takes list of suffixes, outputs possible solutions
  (solvePuzzle '(\"crity\" \"minus\" \"sinet\"))"
  (let [possibleWords (map (fn [end] (get suffmap end)) clueLst)]
    (remove (fn [ls] (empty? ls))
            (map findSolution (cart-prod possibleWords)))))
