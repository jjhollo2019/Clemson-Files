default: cpsc2150/labs/lab2/*.java
	javac cpsc2150/labs/lab2/MortgageApp.java
	javac cpsc2150/labs/lab2/Customer.java
	javac cpsc2150/labs/lab2/Mortgage.java
	
test: cpsc2150/labs/lab2/TestMortgage.java  
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/labs/lab2/TestMortgage.java

run: cpsc2150/labs/lab2/*.class
	java cpsc2150.labs.lab2.MortgageApp

runtest: cpsc2150/labs/lab2/MortgageApp.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.labs.lab2.TestMortgage

clean: cpsc2150/MortgageApp/*.class
	rm -f *.class
