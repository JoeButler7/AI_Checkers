public class Coord {
    private int column;
    private int row;

    public Coord(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public boolean equals(Coord one){
        if(one.getRow()==this.getRow()&&one.getColumn()==this.getColumn())
            return true;
        return false;
    }
    //getters and setters
    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
