import java.util.List;
import java.util.Map;

public interface Rules {
    List<Tile> getStartingPositions();
    Map<PieceName, List<Movement>> getPieceMovements();
    boolean checkWin(Board board, Color color);
    boolean validateSpecialMovement(Tile origin, Tile destination, Board board);
}
