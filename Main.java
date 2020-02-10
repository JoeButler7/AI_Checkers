import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Board make_MoveHuman(String move, Board curr) {
        move=move.toLowerCase();

        if (move.contains("-") && move.contains("x")) {
            System.out.println("Invalid move sequence, please re-enter move");
            return null;
        }
        if (move.contains("-")) {
            String[] parts = move.split("-");
            String src = parts[0];
            String dst = parts[parts.length - 1];
            int src1 = (int) src.charAt(0);
            src1 -= 97;
            int src2 = (int) src.charAt(1);
            src2 -= 49;
            int dst1 = (int) dst.charAt(0);
            dst1 -= 97;
            int dst2 = (int) dst.charAt(1);
            dst2 -= 49d;

            Coord start = new Coord(src2, src1);
            Coord result = new Coord(dst2, dst1);
            Move m = new Move(start, result, null,0);
            Board newb = curr.getBoardFromMove(m);
            if(!m.isLegal(curr)){
                System.out.print("ILLEGAL MOVE\nPLEASE ENTER A LEGAL MOVE \n");
                return null;
            }

            newb.update();
            return newb;
        }
        else if (move.contains("x")) {
            String[] parts = move.split("x");
            String src = parts[0];
            String dst = parts[parts.length - 1];
            int src1 = (int) src.charAt(0);
            src1 -= 97;
            int src2 = (int) src.charAt(1);
            src2 -= 49;
            int dst1 = (int) dst.charAt(0);
            dst1 -= 97;
            int dst2 = (int) dst.charAt(1);
            dst2 -= 49;
            String temp;
            ArrayList<Coord> caps = new ArrayList<>();
            for (int i = 1; i < parts.length - 1; i++) {
                temp = parts[i];
                int cap0 = (int) temp.charAt(0) - 97;
                int cap1 = (int) temp.charAt(1) - 49;
                caps.add(new Coord(cap1, cap0));
            }
            Coord start = new Coord(src2, src1);
            Coord result = new Coord(dst2, dst1);
            Move m = new Move(start, result, caps, caps.size());
            if(!m.isLegal(curr)) {
                System.out.print("ILLEGAL MOVE\nPLEASE ENTER A LEGAL MOVE \n");
                return null;
            }
            Board newb = curr.getBoardFromMove(m);
            newb.update();
            return newb;
        }
        return null;
    }


    public static void main (String[] args) {
        Scanner scan=new Scanner(System.in);
        Board b=new Board(8);
        b.printBoard();
        AI agent=new AI();
        Utility u=new Utility();

        while (!b.isTerm()){
            if(b.isTurn()){
                Board temp=make_MoveHuman(scan.nextLine(),b );
                while (temp==null){
                    temp=make_MoveHuman(scan.nextLine(),b );
                    b.printBoard();
                }
                b=temp;
            }
            else
                b=agent.ABH(b);
            b.printBoard();
            System.out.println("Moves: "+b.getMoveCount());
        }
        if(u.getUtil(b)==1)
            System.out.print("WHITE WINS");
        else if(u.getUtil(b)==-1)
            System.out.print("BLACK WINS");
    }
}
