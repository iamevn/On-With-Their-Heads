echo "lein uberjar"
lein uberjar

echo "echo \"(compile 'wordgame.core)\" | lein repl"
echo "(compile 'wordgame.core)" | lein repl &> /dev/null
# echo "generated class files"

echo cp ./target/wordgame-0.0.1-standalone.jar ./wordgame.jar
cp ./target/wordgame-0.0.1-standalone.jar ./wordgame.jar &> /dev/null

echo "cd ./target/classes/"
cd ./target/classes/ &> /dev/null

# echo "adding classes to jar"
echo "jar uf ../../wordgame.jar *"
jar uf ../../wordgame.jar * &> /dev/null
echo "done"
echo

echo "need to include wordgame.jar in java classpath"
