import java.util.Scanner;
public class StudentGradeCalculator 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Student Grade Calculator");
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];
        int total = 0;
        for (int i = 0; i < numSubjects; i++) 
        {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            if (marks[i] < 0 || marks[i] > 100) 
            {
                System.out.println("Invalid mark! Please enter a number between 0 and 100.");
                i--; // repeat this subject
                continue;
            }
            total += marks[i];
        }
        double average = (double) total / numSubjects;
        String grade;
        if (average >= 90) 
        {
            grade = "A+";
        } 
        else if (average >= 80) 
        {
            grade = "A";
        } 
        else if (average >= 70) 
        {
            grade = "B";
        } 
        else if (average >= 60) 
        {
            grade = "C";
        }
        else if (average >= 50) 
        {
            grade = "D";
        }
        else 
        {
            grade = "F (Fail)";
        }

        System.out.println("\n Results:");
        System.out.println("Total Marks: " + total + "/" + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);
        scanner.close();
    }
}
