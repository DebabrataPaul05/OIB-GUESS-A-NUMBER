import java.io.*;
import java.util.*;

public class GuessCompetition
{

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static final String PASSWORD = "5678";

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("\n==== GUESS NUMBER COMPETITION ====");
            System.out.println("1. Judge Mode");
            System.out.println("2. Participant Mode");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    judgeMode();
                    break;

                case 2:
                    participantMode();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void judgeMode()
    {
        System.out.print("Enter Judge Password: ");
        String pass = sc.nextLine();

        if (!pass.equals(PASSWORD))
        {
            System.out.println("Wrong Password! Access Denied.");
            return;
        }

        while (true)
        {
            System.out.println("\n--- JUDGE PANEL ---");
            System.out.println("1. Set Number of Attempts");
            System.out.println("2. View All Scores");
            System.out.println("3. View Individual Score");
            System.out.println("4. Back");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch)
            {
                case 1:
                    System.out.print("Enter Number of Attempts: ");
                    int attempts = sc.nextInt();
                    sc.nextLine();
                    saveAttempts(attempts);
                    System.out.println("Attempts Updated Successfully!");
                    break;

                case 2:
                    viewAllScores();
                    break;

                case 3:
                    System.out.print("Enter Participant Name: ");
                    String name = sc.nextLine();
                    viewIndividualScore(name);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void participantMode()
    {
        int attempts = loadAttempts();

        if (attempts == 0)
        {
            System.out.println("Judge Has Not Set Attempts Yet!");
            return;
        }

        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        int number = rand.nextInt(100) + 1;
        int remaining = attempts;
        int score = 0;
        boolean guessed = false;

        while (remaining > 0)
        {
            System.out.print("Guess Number (1-100): ");
            int guess = sc.nextInt();

            if (guess == number)
            {
                System.out.println("Correct Guess!");
                score = remaining * 10;
                guessed = true;
                break;
            }
            else if (guess > number)
            {
                System.out.println("Too High!");
            }
            else
            {
                System.out.println("Too Low!");
            }

            remaining--;
            System.out.println("Attempts Left: " + remaining);
        }

        if (!guessed)
        {
            System.out.println("You Lost! Number Was: " + number);
        }

        System.out.println("Your Score: " + score);

        saveScore(name, score);
    }

    static void saveAttempts(int attempts)
    {
        try
        {
            FileWriter fw = new FileWriter("config.txt");
            fw.write(String.valueOf(attempts));
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Error Saving Attempts!");
        }
    }

    static int loadAttempts()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            int attempts = Integer.parseInt(br.readLine());
            br.close();
            return attempts;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    static void saveScore(String name, int score)
    {
        try
        {
            FileWriter fw = new FileWriter("scores.txt", true);
            fw.write(name + "," + score + "\n");
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Error Saving Score!");
        }
    }

    static void viewAllScores()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("scores.txt"));
            String line;

            System.out.println("\n--- ALL PARTICIPANTS ---");

            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");
                System.out.println("Name: " + data[0] + " | Score: " + data[1]);
            }

            br.close();
        }
        catch (Exception e)
        {
            System.out.println("No Scores Found!");
        }
    }

    static void viewIndividualScore(String name)
    {
        boolean found = false;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("scores.txt"));
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");

                if (data[0].equalsIgnoreCase(name))
                {
                    System.out.println("Name: " + data[0] + " | Score: " + data[1]);
                    found = true;
                }
            }

            br.close();

            if (!found)
            {
                System.out.println("Participant Not Found!");
            }
        }
        catch (Exception e)
        {
            System.out.println("No Data Available!");
        }
    }
}
