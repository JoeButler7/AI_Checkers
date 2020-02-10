import java.util.ArrayList;

public class Board implements State{
    private Piece [][] board;
    private ArrayList<Piece> black;
    private ArrayList<Piece> white;
    private boolean turn;
    private int moveCount;
    private int utility;

    public Board(int size){
        this.board=new Piece[size][size];
        this.black=new ArrayList<Piece>();
        this.white=new ArrayList<Piece>();
        if(size==4){
            this.black.add(new Piece(Color.Black,new Coord(1,0),false));
            this.black.add(new Piece(Color.Black,new Coord(3,0),false));
            this.white.add(new Piece(Color.White,new Coord(0,3),false));
            this.white.add(new Piece(Color.White,new Coord(2,3),false));
        }
        this.turn=true;
        this.moveCount=0;
        this.utility=0;
        update();
    }

    public Board(Piece[][] board, ArrayList<Piece> black, ArrayList<Piece> white, boolean turn, int moveCount) {
        this.board = board;
        this.black = black;
        this.white = white;
        this.turn = turn;
        this.moveCount = moveCount;
    }

    public ArrayList<Board> getChildren(){
        ArrayList<Board> ret=new ArrayList<Board>();
        ArrayList<Move> moves=getMoves();
        for(Move m: moves){
            ret.add(getBoardFromMove(m));
        }
        return ret;
    }

    public ArrayList<Move> getMoves(){
        ArrayList<Move> ret=new ArrayList<Move>();
        if(turn) {
            for (Piece p : white) {
                ret.addAll(getMovesFromPos(p));
                ret.addAll(getCapFromPos(p, new ArrayList<Coord>()));
            }
        }
        else {
            for (Piece p : black) {
                ret.addAll(getMovesFromPos(p));
                ret.addAll(getCapFromPos(p, new ArrayList<Coord>()));
            }
        }
        return ret;
    }
    public ArrayList<Move> getMovesFromPos(Piece p){
        ArrayList<Move> ret=new ArrayList<Move>();
        int x=p.getPos().getColumn();
        int y=p.getPos().getRow();
        int size=board.length;
        if(p.getColor()==Color.White||p.getColor()==Color.WhiteKing){
            //gets white moves
            if(x+1<size&&y-1>=0){
                if(board[x+1][y-1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x+1,y-1),null));
                }
            }
            if(x-1>=0&&y-1>=0){
                if(board[x-1][y-1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x-1,y-1),null));
                }
            }
        }
        if(p.getColor()==Color.WhiteKing){
            if(x+1<size&&y+1<size){
                if(board[x+1][y+1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x+1,y+1),null));
                }
            }
            if(x-1>=0&&y+1<size){
                if(board[x-1][y+1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x-1,y+1),null));
                }
            }
        }

        if(p.getColor()==Color.Black||p.getColor()==Color.BlackKing){
            //gets black moves
            if(x+1<size&&y+1<size){
                if(board[x+1][y+1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x+1,y+1),null));
                }
            }
            if(x-1>=0&&y+1<size){
                if(board[x-1][y+1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x-1,y+1),null));
                }
            }
        }
        if(p.getColor()==Color.BlackKing){
            if(x+1<size&&y-1>=0){
                if(board[x+1][y-1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x+1,y-1),null));
                }
            }
            if(x-1>=0&&y-1>=0){
                if(board[x-1][y-1].getColor()==Color.empty){
                    ret.add(new Move(new Coord(x,y), new Coord(x-1,y-1),null));
                }
            }
        }
        return ret;
    }
    public ArrayList<Move> getCapFromPos(Piece p, ArrayList<Coord> Cap){
        ArrayList<Coord> tempCap=deepCpyCoord(Cap);
        ArrayList<Move> ret=new ArrayList<Move>();
        int x=p.getPos().getColumn();
        int y=p.getPos().getRow();
        int size=board.length;
        if(p.getColor()==Color.White||p.getColor()==Color.WhiteKing){
            if(x+2<size&&y-2>=0){
                if(board[x+2][y-2].getColor()==Color.empty){
                    if(board[x+1][y-1].getColor()==Color.BlackKing||board[x+1][y-1].getColor()==Color.Black){
                        tempCap.add(new Coord(x+1,y-1));
                        ret.add(new Move(new Coord(x,y),new Coord(x+2,y-2),tempCap));
                    }
                }
            }
            if((x-2)>=0&&(y-2)>=0){
                if(board[x-2][y-2].getColor()==Color.empty){
                    if(board[x-1][y-1].getColor()==Color.BlackKing||board[x-1][y-1].getColor()==Color.Black){
                        tempCap.add(new Coord(x-1,y-1));
                        ret.add(new Move(new Coord(x,y),new Coord(x-2,y-2),tempCap));
                    }
                }
            }
            if(x+2<size&&y+2<size){
                if(board[x+2][y+2].getColor()==Color.empty){
                    if(board[x+1][y+1].getColor()==Color.BlackKing||board[x+1][y+1].getColor()==Color.Black){
                        tempCap.add(new Coord(x+1,y+1));
                        ret.add(new Move(new Coord(x,y),new Coord(x+2,y+2),tempCap));
                    }
                }
            }
            if(x-2>=0&&y+2<size){
                if(board[x-2][y+2].getColor()==Color.empty){
                    if(board[x-1][y+1].getColor()==Color.BlackKing||board[x-1][y+1].getColor()==Color.Black){
                        tempCap.add(new Coord(x-1,y+1));
                        ret.add(new Move(new Coord(x,y),new Coord(x-2,y+2),tempCap));
                    }
                }
            }
        }

        if(p.getColor()==Color.Black){
            if(x+2<size&&y+2<size){
                if(board[x+2][y+2].getColor()==Color.empty){
                    if(board[x+1][y+1].getColor()==Color.WhiteKing||board[x+1][y+1].getColor()==Color.White){
                        tempCap.add(new Coord(x+1,y+1));
                        ret.add(new Move(new Coord(x,y),new Coord(x+2,y+2),tempCap));
                    }
                }
            }
            if(x-2>=0&&y+2<size){
                if(board[x-2][y+2].getColor()==Color.empty){
                    if(board[x-1][y+1].getColor()==Color.WhiteKing||board[x-1][y+1].getColor()==Color.White){
                        tempCap.add(new Coord(x-1,y+1));
                        ret.add(new Move(new Coord(x,y),new Coord(x-2,y+2),tempCap));
                    }
                }
            }
            if(x+2<size&&y-2>=0){
                if(board[x+2][y-2].getColor()==Color.empty){
                    if(board[x+1][y-1].getColor()==Color.White||board[x+1][y-1].getColor()==Color.WhiteKing){
                        tempCap.add(new Coord(x+1,y-1));
                        ret.add(new Move(new Coord(x,y),new Coord(x+2,y-2),tempCap));
                    }
                }
            }
            if((x-2)>=0&&(y-2)>=0){
                if(board[x-2][y-2].getColor()==Color.empty){
                    if(board[x-1][y-1].getColor()==Color.WhiteKing||board[x-1][y-1].getColor()==Color.White){
                        tempCap.add(new Coord(x-1,y-1));
                        ret.add(new Move(new Coord(x,y),new Coord(x-2,y-2),tempCap));
                    }
                }
            }
        }
        return ret;
    }
    private ArrayList<Piece> deepCpy(ArrayList<Piece> pieces){
        ArrayList<Piece> ret=new ArrayList<Piece>();
        for(Piece p: pieces){
            ret.add(new Piece(p.getColor(),p.getPos(),p.isKing()));
        }
        return ret;
    }
    private ArrayList<Coord> deepCpyCoord(ArrayList<Coord> coord){
        ArrayList<Coord> ret=new ArrayList<Coord>();
        for(Coord c: coord){
            ret.add(new Coord(c.getColumn(),c.getRow()));
        }
        return ret;
    }
    public Board getBoardFromMove(Move m){
        ArrayList<Piece> btemp=deepCpy(black);
        ArrayList<Piece> wtemp=deepCpy(white);
        Board ret;
        Piece pt;
        if(turn){
            for(Piece p: wtemp){
                if(p.getPos().equals(m.getStart())){
                    p.setPos(m.getResult());
                }
            }
            if(m.getCapList()!=null) {
                for (Coord c : m.getCapList()) {
                    for (int i=0;i<btemp.size();i++) {
                        pt=btemp.get(i);
                        if (pt.getPos().equals(c))
                            btemp.remove(i);
                    }
                }
            }
            ret= new Board(new Piece[board.length][board.length],btemp,wtemp,false,moveCount);
            ret.update();
        }
        else {
            for(Piece p: btemp){
                if(p.getPos().equals(m.getStart())){
                    p.setPos(m.getResult());
                }
            }
            if(m.getCapList()!=null) {
                for (Coord c : m.getCapList()) {
                    for (int i=0;i<wtemp.size();i++) {
                        pt=wtemp.get(i);
                        if (pt.getPos().equals(c))
                            wtemp.remove(i);
                    }
                }
            }
            ret=new Board(new Piece[board.length][board.length],btemp,wtemp,true,moveCount);
            ret.update();
        }
        return ret;
    }


    public boolean equals(Board b){
       for(int i=0;i<board.length;i++){
           for(int j=0;j<board.length;j++){
               if(board[i][j].getColor()!=b.getBoard()[i][j].getColor())
                   return false;
           }
       }
       return true;
    }




    @Override
    public boolean isTerm() {
        if(black.isEmpty()||white.isEmpty())
            return true;
        if(moveCount==10)
            return true;
        return false;
    }
    @Override
    public void update() {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                board[i][j]=new Piece(Color.empty,new Coord(j,i),false);
            }
        }
        for(Piece p: white){
            if(p.getPos().getRow()==0){
                p.setColor(Color.WhiteKing);
            }
            board[p.getPos().getColumn()][p.getPos().getRow()]=p;
        }
        for(Piece p: black){
            if(p.getPos().getRow()==3)
                p.setColor(Color.BlackKing);
            board[p.getPos().getColumn()][p.getPos().getRow()]=p;
        }
    }
    public void printBoard(){
        System.out.print("  ");
        for (int i=0;i<board.length;i++){
            System.out.print((i+1)+" ");
        }
        System.out.print("\n");
        for(int i=0;i<board.length;i++){
            if(i==0)
                System.out.print("A");
            if(i==1)
                System.out.print("B");
            if(i==2)
                System.out.print("C");
            if(i==3)
                System.out.print("D");
            if(i==4)
                System.out.print("E");
            if(i==5)
                System.out.print("F");
            if(i==6)
                System.out.print("G");
            if(i==7)
                System.out.print("H");
            System.out.print("|");
            for (int j=0;j<board.length;j++){
                if(board[j][i].getColor()==Color.empty)
                    System.out.print("-");
                if(board[j][i].getColor()==Color.White)
                    System.out.print('w');
                if(board[j][i].getColor()==Color.WhiteKing)
                    System.out.print('W');
                if(board[j][i].getColor()==Color.Black)
                    System.out.print('b');
                if(board[j][i].getColor()==Color.BlackKing)
                    System.out.print('B');
                System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
    }






    //getters and setters
    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public ArrayList<Piece> getBlack() {
        return black;
    }

    public void setBlack(ArrayList<Piece> black) {
        this.black = black;
    }

    public ArrayList<Piece> getWhite() {
        return white;
    }

    public void setWhite(ArrayList<Piece> white) {
        this.white = white;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }
}
