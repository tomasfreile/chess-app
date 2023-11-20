package chess.factory;

import chess.ChessPieceMover;
import chess.factory.piece.PieceFactory;
import chess.factory.stalemateConditions.NormalChessStalemate;
import chess.factory.winConditions.NormalChessCheckmate;
import chess.rules.NormalChessRules;
import chess.validator.NotInCheckValidator;
import commons.Board;
import commons.Color;
import commons.Player;
import commons.Tile;
import commons.game.Game;
import commons.game.GameCreator;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.validator.GameValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapablancaGameCreator implements GameCreator {
    public CapablancaGameCreator() {
    }

    @Override
    public Game createGame(){


        Piece rookW = PieceFactory.createPiece(PieceName.ROOK, Color.WHITE);
        Piece knightW = PieceFactory.createPiece(PieceName.KNIGHT, Color.WHITE);
        Piece bishopW = PieceFactory.createPiece(PieceName.BISHOP, Color.WHITE);
        Piece queenW = PieceFactory.createPiece(PieceName.QUEEN, Color.WHITE);
        Piece kingW = PieceFactory.createPiece(PieceName.KING, Color.WHITE);
        Piece pawnW = PieceFactory.createPiece(PieceName.PAWN, Color.WHITE);
        Piece archbishopW = PieceFactory.createPiece(PieceName.ARCHBISHOP, Color.WHITE);
        Piece archbishopB = PieceFactory.createPiece(PieceName.ARCHBISHOP, Color.BLACK);
        Piece rookB = PieceFactory.createPiece(PieceName.ROOK, Color.BLACK);
        Piece knightB = PieceFactory.createPiece(PieceName.KNIGHT, Color.BLACK);
        Piece bishopB = PieceFactory.createPiece(PieceName.BISHOP, Color.BLACK);
        Piece queenB = PieceFactory.createPiece(PieceName.QUEEN, Color.BLACK);
        Piece kingB = PieceFactory.createPiece(PieceName.KING, Color.BLACK);
        Piece pawnB = PieceFactory.createPiece(PieceName.PAWN, Color.BLACK);



        Map<Tile,Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(0,0),rookW);
        startingPositions.put(new Tile(0,1),knightW);
        startingPositions.put(new Tile(0,2),archbishopW);
        startingPositions.put(new Tile(0,3),bishopW);
        startingPositions.put(new Tile(0,4),queenW);
        startingPositions.put(new Tile(0,5),kingW);
        startingPositions.put(new Tile(0,6),bishopW);
        startingPositions.put(new Tile(0,7),archbishopW);
        startingPositions.put(new Tile(0,8),knightW);
        startingPositions.put(new Tile(0,9),rookW);
        startingPositions.put(new Tile(7,0),rookB);
        startingPositions.put(new Tile(7,1),knightB);
        startingPositions.put(new Tile(7,2),archbishopB);
        startingPositions.put(new Tile(7,3),bishopB);
        startingPositions.put(new Tile(7,4),queenB);
        startingPositions.put(new Tile(7,5),kingB);
        startingPositions.put(new Tile(7,6),bishopB);
        startingPositions.put(new Tile(7,7),archbishopB);
        startingPositions.put(new Tile(7,8),knightB);
        startingPositions.put(new Tile(7,9),rookB);

        for (int i = 0; i < 10; i++) {
            startingPositions.put(new Tile(1,i),pawnW);
            startingPositions.put(new Tile(6,i),pawnB);
        }

        List<WinCondition> winConditions = new ArrayList<>();
        WinCondition winCondition = new NormalChessCheckmate(PieceName.KING);
        winConditions.add(winCondition);

        List<StalemateCondition> stalemateConditions = new ArrayList<>();
        StalemateCondition stalemateCondition = new NormalChessStalemate();
        stalemateConditions.add(stalemateCondition);

        List<GameValidator> gameMoveValidators = List.of(new NotInCheckValidator(PieceName.KING));

        Rules rules = new NormalChessRules(winConditions, stalemateConditions, gameMoveValidators);

        Board board = new Board(startingPositions, 8, 10);

        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, new ChessPieceMover());
    }


}
