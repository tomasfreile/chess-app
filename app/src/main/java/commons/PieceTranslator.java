package commons;

import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;

public class PieceTranslator {
    public PieceTranslator() {
    }

    public List<ChessPiece> translatePieceList(List<Tile> positions){
        List<ChessPiece> pieces = new ArrayList<>();
        String id = "1";
        for (Tile tile : positions) {
            if (!tile.isEmpty()){
                Piece piece = tile.getPiece();
                ChessPiece newPiece = new ChessPiece(id,piece.getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK,new Position(tile.getRow()+1,tile.getColumn()+1),piece.getType().toString().toLowerCase());
                pieces.add(newPiece);
            }
            id = String.valueOf(Integer.parseInt(id)+1);
        }
        return pieces;
    }
}
