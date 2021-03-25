package ua.com.alevel.thirdLevel.conwaysGameOfLife.data;

public enum CellStatus {
    NONE,
    BORN,
    LIVE,
    DIED;

    public CellStatus prepare (int environment) {
        switch (this){
            case NONE: return (environment == 3) ? BORN : NONE;
            case LIVE: return (environment <= 1 || environment >= 4) ? DIED : LIVE;
            default: return this;
        }
    }
    public CellStatus replace () {
        switch (this) {
            case BORN: return LIVE;
            case DIED: return NONE;
            default: return this;
        }
    }

    public boolean isCell() {
        return this == LIVE || this == DIED;
    }
}
