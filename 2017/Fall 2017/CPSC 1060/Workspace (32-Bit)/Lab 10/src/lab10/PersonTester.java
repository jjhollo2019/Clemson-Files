package lab10;

public class PersonTester {

	public static void main(String[] args) {
	Person person1 = new Person("Roger", 23, false);
	Person person2 = new Person("Dorothy", 21, true);
	
	System.out.println("person1 = \n" + person1 + "\n");
	System.out.println("person2 = \n" + person2 + "\n");
	
	System.out.println("Before changing person1:");
	System.out.println("# of People:\t" + Person.howManyPeople());
	System.out.println("# of Females:\t" + Person.howManyFemales());
	
	person1.setAge(person2.getAge());
	person1.setName(person2.getName());
	person1.setIsFemale(person2.getIsFemale());
	
	System.out.println("After changing personnel:");
	System.out.println("# of People:\t" + Person.howManyPeople());
	System.out.println("# of Females:\t" + Person.howManyFemales());
	System.out.println();
	System.out.println("person1 equals person2? " +person1.equals(person2));
	
	
	}

}
