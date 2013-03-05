all:
	echo uncomment line 31 in math.jar to see the threads
	javac *.java
	jar cfm life.jar Manifest *.class
clean:
	rm *.class
