package com.letscode.java;

import java.util.Scanner;

public class TicTocToe {

    public static void play() {
        char[][] board = {
                {'¹', '|', '²', '|', '³'},
                {'⁴', '|', '⁵', '|', '⁶'},
                {'⁷', '|', '⁸', '|', '⁹'}
        };
        Scanner input = new Scanner(System.in);
        int player = 1, position;
        boolean playerOneTurn = true;
        boolean[] moveCount = new boolean[9];

        do {
            System.out.printf("Jogador %d, insira posição: ", player);
            position = checkMove(input, moveCount);
            if (position != 0) {
                updateBoard(position, player, board);
                playerOneTurn = !playerOneTurn;
                player = playerOneTurn ? 1 : 2;
            }
        } while (verifyWinner(board) == 0 && position != 0);

        input.close();

        if (verifyWinner(board) == 0) {
            System.out.println("Jogadores finalizaram a partida sem resultado final obtido");
        } else
            System.out.printf("Jogador %d venceu", verifyWinner(board));
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public static void updateBoard(int position, int player, char[][] board) {

        char character = (player == 1) ? 'X' : 'O';

        switch (position) {
            case 1:
                board[0][0] = character;
                break;
            case 2:
                board[0][2] = character;
                break;
            case 3:
                board[0][4] = character;
                break;
            case 4:
                board[1][0] = character;
                break;
            case 5:
                board[1][2] = character;
                break;
            case 6:
                board[1][4] = character;
                break;
            case 7:
                board[2][0] = character;
                break;
            case 8:
                board[2][2] = character;
                break;
            case 9:
                board[2][4] = character;
                break;
        }
        printBoard(board);
    }

    public static int verifyWinner(char[][] board) {
        char result = 0;
        for (int i = 0; i < board.length; i++) { // verifica linhas
            if ((board[i][0] == board[i][2] && board[i][0] == board[i][4])) {
                result = board[i][0];
            }
            for (int j = 0; j < board[i].length; j += 2) { // verifica colunas
                if ((board[0][j] == board[1][j] && board[0][j] == board[2][j])) {
                    result = board[0][j];
                }
            }
        }
        if ((board[0][0] == board[1][2] && board[0][0] == board[2][4]) || // verifica diagonais
                (board[0][4] == board[1][2] && board[0][4] == board[2][0])) {
            result = board[1][2];
        }
        return result == 'X' ? 1 : result == 'O' ? 2 : 0;
    }

    public static int checkMove(Scanner input, boolean[] moveCount) {
        int position = -1;
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                position = input.nextInt();
                if (position > 0 && position < 10) {
                    if (!moveCount[position - 1]) {
                        moveCount[position - 1] = true;
                        break;
                    } else {
                        System.out.print("Posição já ocupada, escolha outra:");
                        input.nextLine();
                    }
                } else if (position == 0) {
                    break;
                }
            } else {
                System.out.print("Insira uma posição válida (1 - 9): ");
                input.nextLine();
            }
        }
        return position;
    }

}
