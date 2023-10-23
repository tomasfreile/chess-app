package chess.factory;

import chess.factory.NormalChessRules;
import chess.factory.piece.*;
import chess.factory.stalemateConditions.NormalChessStalemate;
import chess.factory.winConditions.NormalChessCheckmate;
import commons.GameCreator;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.*;
import chess.*;

import java.util.ArrayList;
import java.util.List;

public class RegularGameCreator implements GameCreator {
    public RegularGameCreator() {
    }

    @Override
    public Game createGame(){
        //rook
        PieceCreator rookCreator = new RookCreator();
        Piece rookW = rookCreator.createPiece(Color.WHITE);
        Piece rookB = rookCreator.createPiece(Color.BLACK);

        //pawn
        PieceCreator pawnCreator = new PawnCreator();
        Piece pawnW = pawnCreator.createPiece(Color.WHITE);
        Piece pawnB = pawnCreator.createPiece(Color.BLACK);

        //knight
        PieceCreator knightCreator = new KnightCreator();
        Piece knightW = knightCreator.createPiece(Color.WHITE);
        Piece knightB = knightCreator.createPiece(Color.BLACK);

        //bishop
        PieceCreator bishopCreator = new BishopCreator();
        Piece bishopW = bishopCreator.createPiece(Color.WHITE);
        Piece bishopB = bishopCreator.createPiece(Color.BLACK);

        //queen
        PieceCreator queenCreator = new QueenCreator();
        Piece queenW = queenCreator.createPiece(Color.WHITE);
        Piece queenB = queenCreator.createPiece(Color.BLACK);

        //king
        PieceCreator kingCreator = new KingCreator();
        Piece kingW = kingCreator.createPiece(Color.WHITE);
        Piece kingB = kingCreator.createPiece(Color.BLACK);

        List<Tile> startingPositions = new ArrayList<>();
        startingPositions.add(new Tile(0, 0, rookW));
        startingPositions.add(new Tile(0, 1, knightW));
        startingPositions.add(new Tile(0, 2,bishopW));
        startingPositions.add(new Tile(0, 3,queenW));
        startingPositions.add(new Tile(0, 4, kingW));
        startingPositions.add(new Tile(0, 5, bishopW));
        startingPositions.add(new Tile(0, 6, knightW));
        startingPositions.add(new Tile(0, 7, rookW));
        startingPositions.add(new Tile(7, 0, rookB));
        startingPositions.add(new Tile(7, 1, knightB));
        startingPositions.add(new Tile(7, 2, bishopB));
        startingPositions.add(new Tile(7, 3,queenB));
        startingPositions.add(new Tile(7, 4, kingB));
        startingPositions.add(new Tile(7, 5,bishopB));
        startingPositions.add(new Tile(7, 6, knightB));
        startingPositions.add(new Tile(7, 7, rookB));
        for (int i = 0; i < 8; i++){
            startingPositions.add(new Tile(1, i, pawnW));
            startingPositions.add(new Tile(6, i, pawnB));
        }


        List<WinCondition> winConditions = new ArrayList<>();
        WinCondition winCondition = new NormalChessCheckmate();
        winConditions.add(winCondition);

        List<StalemateCondition> stalemateConditions = new ArrayList<>();
        StalemateCondition stalemateCondition = new NormalChessStalemate();
        stalemateConditions.add(stalemateCondition);

        Rules rules = new NormalChessRules(startingPositions, winConditions, stalemateConditions);
        Board board = new Board(8,8);

        for (Tile tile : rules.getStartingPositions()) {
            board = board.replacePosition(tile);
        }

        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new ChessGame(board, player1, player2, rules, player1, false);
    }


}
