import java.util.Scanner;

public class PAC1Ex1 {

    public static void main(String[] args) {

        char[] letters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        int number;
        char l;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the DNI number: ");
        number = scanner.nextInt(); //assigns the variable "number" the integer entered by keyboard

        if (number < 10000000 || number > 99999999) {
            System.out.println("[ERROR]: number DNI not valid");

        } else {
            int position = getRest(number);
            l = getLetter(letters, position);
            System.out.printf("Letter: %c \n", l);
        }
    }

    public static int getRest(int number) {
        return number % 23;
    }

    public static char getLetter(char[] letters, int position) {
        return letters[position];
    }
}
