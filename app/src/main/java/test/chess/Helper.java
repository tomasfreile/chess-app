package test.chess;

import commons.Tile;

import java.util.List;

public class Helper {
    public static boolean containsTile(List<Tile> list, Tile tile){
        for (Tile t : list){
            if (t.getRow() == tile.getRow() && t.getColumn() == tile.getColumn()){
                return true;
            }
        }
        return false;
    }
}
