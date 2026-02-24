import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

class Mywc {
  private static int countBytes(File file, FileInputStream fin, String filePath) {
    int bytes = 0;

    try {
      bytes = (int) file.length();

    } catch (Exception e) {
      System.out.println("Error decoding bytes: " + e.getMessage());
    }

    return bytes;
  }

  private static int countLines(File file, FileInputStream fin, String filePath) {
    int lines = 0;
    
    try(Scanner sc = new Scanner(file)){
      while(sc.hasNextLine()) {
        lines++;
        sc.nextLine();
      }

    } catch (Exception e) {
      System.out.println("Error decoding lines: " + e.getMessage());
    }

    return lines;
  }

  private static int countWords(File file, FileInputStream fin, String filePath) {
    int words = 0;

    try(Scanner sc = new Scanner(file)) {

      while(sc.hasNext()) {
        sc.next();
        words++;
      }

    } catch (Exception e) {
      System.out.println("Error decoding words: " + e.getMessage());
    }

    return words;
  }

  private static int countCharacters(File file, FileInputStream fin, String filePath) {
    int characters = 0;
    int ch;

    try(BufferedReader bf = Files.newBufferedReader(Paths.get(filePath))) {
      
      while((ch = bf.read()) != -1) {
        characters++;
      }      
    } catch (Exception e) {
      System.out.println("Error decoding characters: " + e.getMessage());
    }

    return characters;
  }

  public static void main(String[] args) {
    String command = args.length > 0 ? args[0] : null;
    String filePath = args.length > 1 ? args[1] : null;

    if(args.length == 1) {
      filePath = args[0];
      command = null;
    }
    try (FileInputStream fin = new FileInputStream(filePath)){
      File file = new File(filePath); // cannot close file

      if(command == null) {
        // -c, -l and -w
        int bytes = countBytes(file, fin, filePath);
        int lines = countLines(file, fin, filePath);
        int words = countWords(file, fin, filePath);

        System.out.println(bytes + "\t" + lines + "\t" + words + "\t" + filePath);
      }
      else if(command.equals("-c")) 
      {
        int count = countBytes(file, fin, filePath); // output: 342190 test.txt
        System.out.println(count + " " + filePath);
      } 
      else if(command.equals("-l")) 
      {
        int count = countLines(file, fin, filePath); // output: 7145 test.txt
        System.out.println(count + " " + filePath);
      }
      else if(command.equals("-w")) 
      {
        int count = countWords(file, fin, filePath); // output: 58164 test.txt
        System.out.println(count + " " + filePath);
      }
      else if(command.equals("-m")) 
      {
        int count = countCharacters(file, fin, filePath); // output: 339292 test.txt
        System.out.println(count + " " + filePath);
      }
    } catch (Exception e) {
      System.out.println("Error reading file.." + e.getMessage());
    }
  }
}