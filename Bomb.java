package ExamPreparation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bomb {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        char[][] field = new char[size][size];

        int sapperRow = 0;
        int sapperCol = 0;
        int bombCounter = 0;

        for (int row = 0; row < size; row++) {
            List<Character> characterList = Arrays.stream(scanner.nextLine().split(" ")).map(e -> e.charAt(0)).collect(Collectors.toList());

            for (int col = 0; col < characterList.size(); col++) {
                char currentChar = characterList.get(col);

                if (currentChar == 's') {
                    sapperRow = row;
                    sapperCol = col;
                } else if (currentChar == 'B') {
                    bombCounter++;
                }
                field[row][col] = currentChar;
            }
        }

        int bombsFound = 0;

        for (int i = 0; i < commands.length; i++) {
            String commandName = commands[i];

            switch (commandName) {
                case "up":
                    if (sapperRow != 0) {
                        sapperRow--;
                    }
                    break;
                case "down":
                    if (sapperRow != size - 1) {
                        sapperRow++;
                    }
                    break;
                case "right":
                    if (sapperCol != size - 1) {
                        sapperCol++;
                    }
                    break;
                case "left":
                    if (sapperCol != 0) {
                        sapperCol--;
                    }
                    break;
            }

            if (field[sapperRow][sapperCol] == 'B') {
                System.out.println("You found a bomb!");
                field[sapperRow][sapperCol] = '+';
                bombsFound++;

                if (bombsFound == bombCounter) {
                    System.out.println("Congratulations! You found all bombs!");
                    return;
                }
            } else if (field[sapperRow][sapperCol] == 'e') {
                System.out.printf("END! %d bombs left on the field%n", bombCounter - bombsFound);
                return;
            }
        }
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", bombCounter - bombsFound, sapperRow, sapperCol);
        }
    }

