package commons.adapter;

import commons.Color;
import commons.Tile;
import commons.piece.Piece;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieceTranslator {
    public PieceTranslator() {
    }

    public static List<ChessPiece> translatePieceList(Map<Tile,Piece> positions){
        List<ChessPiece> pieces = new ArrayList<>();
        String id = "1";
        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
            Tile tile = entry.getKey();
            Piece piece = entry.getValue();
            Position position = new Position(tile.getRow()+1,tile.getColumn()+1);
            PlayerColor playerColor = piece.getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK;
            ChessPiece chessPiece = new ChessPiece(id, playerColor,position ,piece.getType().toString().toLowerCase());
            pieces.add(chessPiece);
            id = String.valueOf(Integer.parseInt(id) + 1);
        }
        return pieces;
    }
}
