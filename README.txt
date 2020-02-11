NAME: Joe Butler
NETID: Jbutl18
ASSIGNMENT: Project 1
I did not collaborate with anyone on this assignment

In this project we had to use adversarial state space search to make an AI that plays checkers on both a 4x4 and
8x8 board. I implemented the Minimax algorithm, Minimax with Alpha-beta pruning, and H-Minimax with alpha-beta pruning.
For H-Minimax my Heuristic function just sums the number of pieces left on the board for the current player, with kings
being worth 3 and normal pieces being worth 2 and then it subtracts the opposing pieces left from that with the same value system.
I also made the depth limit for HMinimax 10 moves, with the maximum number of moves in a game 10 moves for a 4x4 board and 100
for an 8x8 board

To compile and run this program simply enter teh following commands into the terminal once you are in the folder containing the
source code
javac *.java
java Main