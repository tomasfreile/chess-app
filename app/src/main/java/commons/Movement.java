package commons;

public class Movement {

    private final int incrementRow;
    private final int incrementColumn;
    private final boolean isTake;
    private final boolean canJump;
    private final boolean limitless;


    public Movement(int incrementRow, int incrementColumn, boolean isTake, boolean canJump, boolean limitless) {
        this.incrementRow = incrementRow;
        this.incrementColumn = incrementColumn;
        this.isTake = isTake;
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

    public boolean isTake() {
        return isTake;
    }

    public static interface StalemateCondition {
    }
}
