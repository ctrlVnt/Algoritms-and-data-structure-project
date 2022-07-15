all:
	@javac -d ../classes graphlibrary/Graph.java
	@javac -d ../classes -cp '.:../junit-4.12.jar:../hamcrest-core-1.3.jar' graphlibrary/*.java
	@javac -d ../classes minimalheap/MinimalHeap.java
	@javac -d ../classes -cp '.:../junit-4.12.jar:../hamcrest-core-1.3.jar' minimalheap/*.java
	@javac -d ../classes graphusage/DijkstraMain.java

run:
	@make --no-print-directory -C ../

clean:
	rm -f ../classes/graphlibrary/*
	rm -d ../classes/graphlibrary
	rm -f ../classes/minimalheap/*
	rm -d ../classes/minimalheap