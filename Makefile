all:
	javac -d build ./src/*.java

run:
	java -cp build Main