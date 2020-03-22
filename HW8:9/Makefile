JAVAC=javac

build: src/*.java
	@echo ----- creating a jar file with java -----
	javac src/*.java
	jar cfm 141OS.jar src/MANIFEST.MF src/*.java

clean: 
	rm src/*.class