import chess.factory.regularChess.RegularGameCreator;

import commons.Game;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Game game = new RegularGameCreator().createGame();

        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            System.out.println(game.getCurrentPlayer().getName() + " turn");
            System.out.println("Enter the row and column of the piece you want to move");
            int fromRow = scanner.nextInt();
            int fromColumn = scanner.nextInt();
            System.out.println("Enter the row and column of the position you want to move to");
            int toRow = scanner.nextInt();
            int toColumn = scanner.nextInt();
            game = game.moveAndSwitchPlayer(game.getBoard().getPosition(fromRow, fromColumn), game.getBoard().getPosition(toRow, toColumn));
            System.out.println(game.getBoard().printBoard());
        }


    }

}