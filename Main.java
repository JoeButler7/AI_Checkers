import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Board make_MoveHuman(String move, Board curr) {
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
            Move m = new Move(start, result, null);
            Board newb = curr.getBoardFromMove(m);
            newb.update();
            return newb;
        } else if (move.contains("x")) {
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
                caps.add(new Coord(cap0, cap1));
            }
            Coord start = new Coord(src1, src2);
            Coord result = new Coord(dst1, dst2);
            Move m = new Move(start, result, caps);
            Board newb = curr.getBoardFromMove(m);
            newb.update();
            return newb;
        }
        return null;
    }


    public static void main (String[] args) {
        Scanner scan=new Scanner(System.in);
        Board b=new Board(4);
        b.printBoard();
        AI agent=new AI();

        while (!b.isTerm()){
            if(b.isTurn()){
                Board temp=make_MoveHuman(scan.nextLine(),b );
                b=temp;
            }
            else
                b=agent.miniMax_Base(b);
            b.printBoard();
        }
    }
}
