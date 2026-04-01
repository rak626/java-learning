package LLD.questions.ChessGame;

import LLD.questions.ChessGame.enums.Color;
import LLD.questions.ChessGame.enums.GameStatus;
import LLD.questions.ChessGame.pieces.*;

import java.util.List;

public class Game {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private ChessBoard board;
    private Player currentPlayer;
    private GameStatus status;

    public Game(String whiteName, String blackName) {
        board = new ChessBoard();
        whitePlayer = new Player(1, whiteName, Color.WHITE);
        blackPlayer = new Player(2, blackName, Color.BLACK);
        currentPlayer = whitePlayer;
        status = GameStatus.ONGOING;
        initializePieces();
    }

    private void initializePieces() {
        // TODO: Place all pieces in starting positions

        // Pawns
        for (int col = 0; col < 8; col++) {
            board.getCell(1, col).setPiece(new Pawn(Color.BLACK, board.getCell(1, col)));
            board.getCell(6, col).setPiece(new Pawn(Color.WHITE, board.getCell(6, col)));
        }

        // Rooks
        board.getCell(0, 0).setPiece(new Rook(Color.BLACK, board.getCell(0, 0)));
        board.getCell(0, 7).setPiece(new Rook(Color.BLACK, board.getCell(0, 7)));
        board.getCell(7, 0).setPiece(new Rook(Color.WHITE, board.getCell(7, 0)));
        board.getCell(7, 7).setPiece(new Rook(Color.WHITE, board.getCell(7, 7)));

        // Knights
        board.getCell(0, 1).setPiece(new Knight(Color.BLACK, board.getCell(0, 1)));
        board.getCell(0, 6).setPiece(new Knight(Color.BLACK, board.getCell(0, 6)));
        board.getCell(7, 1).setPiece(new Knight(Color.WHITE, board.getCell(7, 1)));
        board.getCell(7, 6).setPiece(new Knight(Color.WHITE, board.getCell(7, 6)));

        // Bishops
        board.getCell(0, 2).setPiece(new Bishop(Color.BLACK, board.getCell(0, 2)));
        board.getCell(0, 5).setPiece(new Bishop(Color.BLACK, board.getCell(0, 5)));
        board.getCell(7, 2).setPiece(new Bishop(Color.WHITE, board.getCell(7, 2)));
        board.getCell(7, 5).setPiece(new Bishop(Color.WHITE, board.getCell(7, 5)));

        // Queens
        board.getCell(0, 3).setPiece(new Queen(Color.BLACK, board.getCell(0, 3)));
        board.getCell(7, 3).setPiece(new Queen(Color.WHITE, board.getCell(7, 3)));

        // Kings
        board.getCell(0, 4).setPiece(new King(Color.BLACK, board.getCell(0, 4)));
        board.getCell(7, 4).setPiece(new King(Color.WHITE, board.getCell(7, 4)));
    }


    public void switchTurn() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public void move(ChessBoardCell from, ChessBoardCell to) {

        Piece movingPiece = from.getPiece();
        if (movingPiece == null) {
            System.out.println("No piece at source!");
            return;
        }

        List<ChessBoardCell> legalMoves = movingPiece.getLegalMoves(board);
        if (!legalMoves.contains(to)) {
            System.out.println("Illegal move!");
            return;
        }

        if (to.isOccupied()) {
            Piece captured = to.getPiece();
            getCurrentPlayer().addCapturedPiece(captured); // Track captured piece
            System.out.println("Captured: " + captured.getClass().getSimpleName());
        }

        board.movePiece(from, to);

        // After move
        currentPlayer.getTimer().decreaseTime(5); // example 5 seconds per move
        if (currentPlayer.getTimer().isTimeUp()) {
            status = GameStatus.CHECKMATE; // Opponent wins
            System.out.println(currentPlayer.getName() + " ran out of time!");
        }
        // TODO: Add check/checkmate detection
        // Check check and checkmate for the opponent
        Color opponentColor = (currentPlayer.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;

        if (isCheckmate(opponentColor)) {
            status = GameStatus.CHECKMATE;
            System.out.println("Checkmate! " + currentPlayer.getName() + " wins!");
        } else if (isInCheck(opponentColor)) {
            System.out.println("Check!");
        }
        switchTurn();


    }

    public boolean isCheckmate(Color color) {
        if (!isInCheck(color)) return false;

        // Try all possible moves for the player
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessBoardCell cell = board.getCell(i, j);
                if (cell.isOccupied() && cell.getPiece().getColor() == color) {
                    Piece piece = cell.getPiece();
                    List<ChessBoardCell> moves = piece.getLegalMoves(board);
                    for (ChessBoardCell move : moves) {
                        // Simulate the move
                        Piece captured = move.getPiece();
                        board.movePiece(cell, move);

                        boolean stillInCheck = isInCheck(color);

                        // Undo move
                        board.movePiece(move, cell);
                        move.setPiece(captured);

                        if (!stillInCheck) {
                            return false; // At least __1__ move saves the king
                        }
                    }
                }
            }
        }

        return true; // No move can save the king → checkmate
    }


    public boolean isInCheck(Color color) {
        // 1. Find the king of the given color
        King king = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessBoardCell cell = board.getCell(i, j);
                if (cell.isOccupied() && cell.getPiece() instanceof King && cell.getPiece().getColor() == color) {
                    king = (King) cell.getPiece();
                    break;
                }
            }
            if (king != null) break;
        }

        if (king == null) return false; // Should never happen

        ChessBoardCell kingPos = king.getPosition();

        // 2. Check all opponent pieces
        Color opponentColor = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessBoardCell cell = board.getCell(i, j);
                if (cell.isOccupied() && cell.getPiece().getColor() == opponentColor) {
                    List<ChessBoardCell> moves = cell.getPiece().getLegalMoves(board);
                    if (moves.contains(kingPos)) {
                        return true; // King can be captured → check
                    }
                }
            }
        }

        return false;
    }


    public void restart() {
        board = new ChessBoard();
        initializePieces();
        currentPlayer = whitePlayer;
        status = GameStatus.ONGOING;
        whitePlayer.getCapturedPieces().clear();
        blackPlayer.getCapturedPieces().clear();
    }

    public void offerDraw(Player player) {
        System.out.println(player.getName() + " offers a draw.");
        status = GameStatus.DRAW;
    }


    public ChessBoard getBoard() {
        return board;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getStatus() {
        return status;
    }
}
