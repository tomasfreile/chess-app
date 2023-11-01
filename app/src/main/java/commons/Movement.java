package commons;

public class Movement {

    private final int incrementRow;
    private final int incrementColumn;
    private final boolean isCapture;
    private final boolean canJump;
    private final boolean limitless;


    public Movement(int incrementRow, int incrementColumn, boolean isTake, boolean canJump, boolean limitless) {
        this.incrementRow = incrementRow;
        this.incrementColumn = incrementColumn;
        this.isCapture = isTake;
        this.canJump = canJump;
        this.limitless = limitless;
    }

    public int getIncrementRow() {
        return incrementRow;
    }

    public int getIncrementColumn() {
        return incrementColumn;
    }

    public boolean canJump() {
        return canJump;
    }

    public boolean isLimitless() {
        return limitless;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public boolean isDiagonal() {
        return incrementRow != 0 && incrementColumn != 0 && Math.abs(incrementRow) == Math.abs(incrementColumn);
    }

    public boolean isHorizontal() {
        return incrementRow == 0 && incrementColumn != 0;
    }

    public boolean isVertical() {
        return incrementRow != 0 && incrementColumn == 0;
    }

    public boolean isLShaped() {
        return incrementRow != 0 && incrementColumn != 0 && Math.abs(incrementRow) != Math.abs(incrementColumn);
    }

}
