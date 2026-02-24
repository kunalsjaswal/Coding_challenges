import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class Mywc {
  private static void countBytes(File file, FileInputStream fin, String filePath) {
    try {
      System.out.println(file.length() + " " + filePath);
    } catch (Exception e) {
      System.out.println("Error decoding bytes: " + e.getMessage());
    }
  }

  private static void countLines(File file, FileInputStream fin, String filePath) {
    int lines = 0;
    
    try(Scanner sc = new Scanner(file)){
      while(sc.hasNextLine()) {
        lines++;
        sc.nextLine();
      }

      System.out.println(lines + " " + filePath);
    } catch (Exception e) {
      System.out.println("Error decoding lines: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    if(args.length < 2) {
      System.out.println("Please enter arguments");
      return;
    }


    String command = args[0];
    String filePath = args[1];

    try (FileInputStream fin = new FileInputStream(filePath)){
      File file = new File(filePath); // cannot close file

      if(command.equals("-c")) 
      {
        countBytes(file, fin, filePath); // output: 342190 test.txt
      } 
      else if(command.equals("-l")) 
      {
        countLines(file, fin, filePath); // output: 7145 test.txt
      }

    } catch (Exception e) {
      System.out.println("Error reading file.." + e.getMessage());
    }
  }
}