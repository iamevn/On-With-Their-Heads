# On With Their Heads

>We've taken 50 nine-letter words, broken them into three blocks of three letters, then found three uncapitalized words, each starting with a different one of those blocks. Given those words (alphabetized after deleting the first three letters), your goal is to deduce the word we started with, by restoring the missing letters and correctly arranging the three-letter blocks. So, for instance, the strings ARGOT, LTALE, and RETTA come from the original word TELESCOPE (making the words TELLTALE, ESCARGOT, and OPERETTA). There may be multiple ways to add three letters to the starts of some of the leftover letter strings, but only one choice will let you spell a nine-letter word. Can you crack them all?

-from [Brain Games For Word Nerds by Francis Heaney](http://www.amazon.com/Brain-Games-Nerds-Francis-Heaney/dp/1402770952)


```
$ lein repl
=> (load-file "test.clj")
#'puzz/solvePuzzle
=> (puzz/solvePuzzle '("oose" "page" "ume"))
(("cablegram"))
=> (puzz/genPuzz)
sentenced : (senor tenured ceder) : (-or -ured -er)
nil
=> (puzz/generatePuzzles 5)
engine : (engorge inequitably) : (-orge -quitably)
glades : (glazing desiccant) : (-zing -iccant)
metastasizes : (methodological asterisking Asiatics zestiest) : (-hodological -erisking -atics -tiest)
ibidem : (Ibiza demobbed) : (-za -obbed)
creeds : (credenza Edsel) : (-denza -el)
nil
```


# Spark set-up -- https://sparktutorials.github.io/2015/04/14/getting-started-with-spark-and-docker.html
