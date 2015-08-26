echo "lein uberjar"
lein uberjar

echo "# Generating class files..."
echo "(compile 'wordgame.core)" | lein repl &> /dev/null
echo "# Generated"

echo "cd ./target/"
cd ./target/

# echo "mv ./wordgame-0.0.1-standalone.jar ./wordgame.jar"
# mv ./wordgame-0.0.1-standalone.jar ./wordgame.jar

echo "cd ./classes/"
cd ./classes/


echo "jar uf ../wordgame-0.0.1-standalone.jar *"
jar uf ../wordgame-0.0.1-standalone.jar *

echo "jar uf ../wordgame-0.0.1.jar ./META-INF/ ./wordgame/"
jar uf ../wordgame-0.0.1.jar ./META-INF/ ./wordgame/

echo "cd ../../"
cd ../../


echo "cp ./target/wordgame-0.0.1-standalone.jar ./libs/com.uofantarctica.headson/wordgame-0.0.1-standalone/0.0.1/wordgame-0.0.1-standalone.jar"
cp ./target/wordgame-0.0.1-standalone.jar ./libs/wordgame/wordgame/0.0.1/wordgame-0.0.1-standalone.jar


echo "done"
echo
