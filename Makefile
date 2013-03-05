all:
	echo uncomment line 29 in math.jar to see the threads
	javac *.java
	jar cfm life.jar Manifest *.class
clean:
	rm *.class
