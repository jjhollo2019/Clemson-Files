JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	  ArrayQueueImp.java \
	  IntegerQueueI.java \
	  ListQueueImp.java \
	  QueueApp.java

MAIN = QueueApp

default: cpsc2150/QueueApp/*.java
	javac cpsc2150/QueueApp/QueueApp.java

run: cpsc2150/QueueApp/*.java
	java cpsc2150.QueueApp.QueueApp

clean: cpsc2150/QueueApp/*.class
	rm -f *.class
