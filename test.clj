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

(def L 3)
(def dict "/usr/share/dict/words")
;; (def dict "/home/evan/desktop/wordgame/abc.txt")

(use 'clojure.java.io)
(defn addDict [dictfile]
  "Read words from dictionary file into wordset, returns that set"
  (with-open [rdr (reader dictfile)]
    (apply hash-set (line-seq rdr))))

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
          (let [prefix (.toLowerCase (.substring word 0 L))]
            ;; (println prefix)
            (buildMap (assoc premap prefix (cons word (get premap prefix)))
                      (rest wordset)))
          (buildMap premap (rest wordset)))
        premap)))


  (buildMap (hash-map) wordset)
  )

(def premap (prefixmap wordset))
