import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void play(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 0 for a 4x4 board and 1 for an 8x8 board");
        int size;
        Board b;
        while (true) {
            size = scan.nextInt();
            if (size == 0) {
                b = new Board(4);
                break;
            } else if (size == 1) {
                b = new Board(8);
                break;
            } else
                System.out.println("Invalid input, please re-enter. 0 for 4x4 board, 1 for 8x8 board");
        }
        int player1 = -1;
        int player2 = -1;
        int input;
        while (true) {
            System.out.println("Select Player 1 (white): 0 for Human, 1 for Minimax, 2 for Minimax with Alpha-Beta Pruning, 3 for H-Minimax with " +
                    "Alpha-Beta pruning \n(Note: Minimax and Minimax with Alpha-Beta will be slow on 8x8 board)");
            input = scan.nextInt();
            if (input == 0 || input == 1 || input == 2 || input == 3) {
                player1 = input;
                break;
            } else
                System.out.println("Invalid input, please try again");
        }
        while (true) {
            System.out.println("Select Player 2 (black): 0 for Human, 1 for Minimax, 2 for Minimax with Alpha-Beta Pruning, 3 for H-Minimax with " +
                    "Alpha-Beta pruning \n(Note: Minimax and Minimax with Alpha-Beta will be slow on 8x8 board)");
            input = scan.nextInt();
            if (input == 0 || input == 1 || input == 2 || input == 3) {
                player2 = input;
                break;
            } else
                System.out.println("Invalid input, please try again");
        }
        if((player1==1||player1==2||player1==3)&&(player2==1||player2==2||player2==3)){
            b.setAi_player(3);
        }
        else if(player1==1||player1==2||player1==3)
            b.setAi_player(1);
        else if(player2==1||player2==2||player2==3)
            b.setAi_player(2);
        else
            b.setAi_player(0);
        System.out.println("To make a move enter the start position - end position, for example a1-b2 \n" +
                "To make a capture move enter the start position x captureposition1xcaptureposition2x...capturepositionnxendposition" +
                "\nfor example a1xb2xc3 to move from a1 to c3 capturing b2");
        AI agent = new AI();
        Utility u = new Utility();
        b.printBoard();
        while (!b.isTerm()) {
            if (b.isTurn()) {
                switch (player1) {
                    case 0:
                        Board temp = make_MoveHuman(scan.next(), b);
                        while (temp == null) {
                            temp = make_MoveHuman(scan.next(), b);
                        }
                        b = temp;
                        b.printBoard();
                        break;
                    case 1:
                        b = agent.miniMax_Base(b);
                        break;
                    case 2:
                        b = agent.Alpha_Beta(b);
                        break;
                    case 3:
                        b = agent.ABH(b);
                }
            } else {
                switch (player2) {
                    case 0:
                        Board temp = make_MoveHuman(scan.next(), b);
                        while (temp == null) {
                            temp = make_MoveHuman(scan.next(), b);
                        }
                        b = temp;
                        b.printBoard();
                        break;
                    case 1:
                        b = agent.miniMax_Base(b);
                        break;
                    case 2:
                        b = agent.Alpha_Beta(b);
                        break;
                    case 3:
                        b = agent.ABH(b);
                }
                b.printBoard();
                System.out.println("Moves Thus Far: " + b.getMoveCount());
            }
        }
        if (u.getUtil(b) == 1)
            System.out.print("WHITE WINS");
        else if (u.getUtil(b) == -1)
            System.out.print("BLACK WINS");
        else if(u.getUtil(b)==0)
            System.out.println("IT'S A TIE");
    }

    public static Board make_MoveHuman(String move, Board curr) {
        move = move.toLowerCase();

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
            Move m = new Move(start, result, null, 0);
            Board newb = curr.getBoardFromMove(m);
            if (!m.isLegal(curr)) {
                System.out.print("ILLEGAL MOVE\nPLEASE ENTER A LEGAL MOVE \n");
                return null;
            }

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
                caps.add(new Coord(cap1, cap0));
            }
            Coord start = new Coord(src2, src1);
            Coord result = new Coord(dst2, dst1);
            Move m = new Move(start, result, caps, caps.size());
            if (!m.isLegal(curr)) {
                System.out.print("ILLEGAL MOVE\nPLEASE ENTER A LEGAL MOVE \n");
                return null;
            }
            Board newb = curr.getBoardFromMove(m);
            newb.update();
            return newb;
        }
        return null;
    }


    public static void main(String[] args) {
        play();
    }

}
