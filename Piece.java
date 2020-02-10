public class Piece {
    private Color color;
    private Coord pos;
    private boolean isKing;

    public Piece(Color color, Coord pos, boolean isKing) {
        this.color = color;
        this.pos = pos;
        this.isKing = isKing;
    }

    //getter and setters


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coord getPos() {
        return pos;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }
}
