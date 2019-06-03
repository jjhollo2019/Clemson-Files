/*
 * This class is used to test the methods in 
 *  the LetterFrequencies class
 */
public class LetterFrequenciesDriver{

  /*
   * The count array stores the count of each letter (a-z),
   *  where count[0] = # of a's, count[1] = # of b's,
   *  count[2] = # of c's, ..., count[25] = # of z's.
   */
  private static int[] count = new int[26];

  private static String[] s1 = {"Stark", "Lannister", "Tully"};

  private static String[] s2 = {
    "Zoey", "Gracie", "Roddie",
    "Darla", "Peanut", "Roxie", "Joe",
    "Cassie", "Sadie", "Scooby Doo",
    "Scrappy Doo", "Astro", "Balto",
    "Beethoven", "Benji", "Clifford",
    "Courage the Cowardly Dog", "Droopy",
    "Fido", "Goofy", "Snoopy", "Brian Griffin",
    "Pluto", "Underdog", "Odie",
    "Santa's Little Helper", "Lady",
    "Marley", "McGruff", "Hachiko",
    "Porthos", "Rin Tin Tin", "Lassie",
    "Todo", "Samantha"
  };

  private static String[] s3 = {
    "R2-D2", "Rosie", "Johnny 5",
    "Motoko Kusanagi", "Bishop",
    "HAL 9000", "Bender", "Data",
    "Megatron", "Optimus Prime",
    "Wall-E", "BB-8", "KIT",
    "Terminator", "Ultron", null, ""
  };

  /*
   *  Test initializeCount and letterCount methods
   */
  public static void main(String[] args){

    LetterFrequencies.initializeCount(count);
    LetterFrequencies.letterCount(s1, count);
    printCount(1);

    LetterFrequencies.initializeCount(count);
    LetterFrequencies.letterCount(s2, count);
    printCount(2);

    LetterFrequencies.initializeCount(count);
    LetterFrequencies.letterCount(s3, count);
    printCount(3);

    LetterFrequencies.initializeCount(count);
    LetterFrequencies.letterCount(args, count);
    printCount(4);

    LetterFrequencies.initializeCount(null);
    LetterFrequencies.letterCount(null, null);
    LetterFrequencies.initializeCount(count);
    LetterFrequencies.letterCount(null, count);
    printCount(5);

    LetterFrequencies.initializeCount(count);
    String[] empty = new String[0];
    LetterFrequencies.letterCount(empty, count);
    printCount(6);

  }

  /*
   * Print the testNum and values in count.
   */
  public static void printCount(int testNum){
    char c = 'a';
    System.out.printf("===Test %d===\n", testNum);
    for(int i = 0; i < 26; i++, c++){
      System.out.printf("%c count = %d\n", c, count[i]);
    }
    System.out.println();
  }

}
