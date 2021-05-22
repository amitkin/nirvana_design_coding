package com.mylearning.design.solid.examples.chess.helpers;

import static com.mylearning.design.solid.examples.chess.model.Color.WHITE;
import static com.mylearning.design.solid.examples.chess.model.PieceType.BISHOP;
import static com.mylearning.design.solid.examples.chess.model.PieceType.KING;
import static com.mylearning.design.solid.examples.chess.model.PieceType.KNIGHT;
import static com.mylearning.design.solid.examples.chess.model.PieceType.PAWN;
import static com.mylearning.design.solid.examples.chess.model.PieceType.QUEEN;
import static com.mylearning.design.solid.examples.chess.model.PieceType.ROOK;
import static com.mylearning.design.solid.examples.chess.moves.VerticalMoveDirection.BOTH;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mylearning.design.solid.examples.chess.conditions.MoveBaseConditionFirstMove;
import com.mylearning.design.solid.examples.chess.conditions.NoMoveBaseCondition;
import com.mylearning.design.solid.examples.chess.conditions.PieceCellOccupyBlocker;
import com.mylearning.design.solid.examples.chess.conditions.PieceCellOccupyBlockerFactory;
import com.mylearning.design.solid.examples.chess.conditions.PieceMoveFurtherConditionDefault;
import com.mylearning.design.solid.examples.chess.model.Board;
import com.mylearning.design.solid.examples.chess.model.Cell;
import com.mylearning.design.solid.examples.chess.model.Color;
import com.mylearning.design.solid.examples.chess.model.Piece;
import com.mylearning.design.solid.examples.chess.model.PieceType;
import com.mylearning.design.solid.examples.chess.moves.PossibleMovesProvider;
import com.mylearning.design.solid.examples.chess.moves.PossibleMovesProviderDiagonal;
import com.mylearning.design.solid.examples.chess.moves.PossibleMovesProviderHorizontal;
import com.mylearning.design.solid.examples.chess.moves.PossibleMovesProviderVertical;
import com.mylearning.design.solid.examples.chess.moves.VerticalMoveDirection;

public class TestHelpers {

    public static Board createBoard() {
        Cell[][] cells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        return new Board(8, cells);
    }

    public static List<Piece> piecesSet(Color color, Board board, int mainPiecesRownNum, int pawnPiecesRowNum, VerticalMoveDirection pawnDirection) {
        List<Piece> allPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Piece pawn = pawn(color, pawnDirection);
            addPieceToBoard(board, pawn, pawnPiecesRowNum, i);
            allPieces.add(pawn);
        }

        Piece king = king(color);
        addPieceToBoard(board, king, mainPiecesRownNum, 3);
        Piece queen = queen(color);
        addPieceToBoard(board, queen, mainPiecesRownNum, 4);
        Piece rook1 = rook(color);
        addPieceToBoard(board, rook1, mainPiecesRownNum, 0);
        Piece rook2 = rook(color);
        addPieceToBoard(board, rook2, mainPiecesRownNum, 7);
        Piece bishop1 = bishop(color);
        addPieceToBoard(board, bishop1, mainPiecesRownNum, 2);
        Piece bishop2 = bishop(color);
        addPieceToBoard(board, bishop2, mainPiecesRownNum, 5);
        Piece knight1 = knight(color);
        addPieceToBoard(board, knight1, mainPiecesRownNum, 1);
        Piece knight2 = knight(color);
        addPieceToBoard(board, knight2, mainPiecesRownNum, 6);

        ImmutableList<Piece> mainPieces = ImmutableList.of(king, queen, rook1, rook2, bishop1, bishop2, knight1, knight2);
        allPieces.addAll(mainPieces);
        return allPieces;
    }

    private static void addPieceToBoard(Board board, Piece piece, int rowNum, int colNum) {
        Cell cell = board.getCellAtLocation(rowNum, colNum);
        piece.setCurrentCell(cell);
        cell.setCurrentPiece(piece);
    }

    public static Piece randomPiece() {
        return pawn(WHITE, BOTH);
    }

    public static Piece testPiece(Color color, PieceType pieceType) {
        return new Piece(color, ImmutableList.of(), pieceType);
    }

    public static Piece pawn(Color color, VerticalMoveDirection pawnDirection) {
        ImmutableList<PossibleMovesProvider> pawnMoveProviders = ImmutableList.of(
            new PossibleMovesProviderVertical(1, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker(), pawnDirection),
            new PossibleMovesProviderVertical(2, new MoveBaseConditionFirstMove(), defaultMoveFurtherCondition(), defaultBaseBlocker(), pawnDirection)
        );
        return new Piece(color, pawnMoveProviders, PAWN);
    }

    public static Piece king(Color color) {
        ImmutableList<PossibleMovesProvider> pawnMoveProviders = ImmutableList.of(
            new PossibleMovesProviderVertical(1, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker(), BOTH),
            new PossibleMovesProviderHorizontal(1, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker()),
            new PossibleMovesProviderDiagonal(1, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker())
        );
        return new Piece(color, pawnMoveProviders, KING);
    }

    public static Piece queen(Color color) {
        ImmutableList<PossibleMovesProvider> pawnMoveProviders = ImmutableList.of(
                new PossibleMovesProviderVertical(8, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker(), BOTH),
                new PossibleMovesProviderHorizontal(8, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker()),
                new PossibleMovesProviderDiagonal(8, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker())
        );
        return new Piece(color, pawnMoveProviders, QUEEN);
    }

    public static Piece rook(Color color) {
        ImmutableList<PossibleMovesProvider> pawnMoveProviders = ImmutableList.of(
            new PossibleMovesProviderVertical(8, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker(), BOTH),
            new PossibleMovesProviderHorizontal(8, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker())
        );
        return new Piece(color, pawnMoveProviders, ROOK);
    }

    public static Piece bishop(Color color) {
        ImmutableList<PossibleMovesProvider> pawnMoveProviders = ImmutableList.of(
            new PossibleMovesProviderDiagonal(8, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), defaultBaseBlocker())
        );
        return new Piece(color, pawnMoveProviders, BISHOP);
    }

    public static Piece knight(Color color) {
        ImmutableList<PossibleMovesProvider> pawnMoveProviders = ImmutableList.of(
                new PossibleMovesProviderDiagonal(1, new NoMoveBaseCondition(), defaultMoveFurtherCondition(), null)
        );
        return new Piece(color, pawnMoveProviders, KNIGHT);
    }

    private static PieceMoveFurtherConditionDefault defaultMoveFurtherCondition() {
        return new PieceMoveFurtherConditionDefault();
    }

    private static PieceCellOccupyBlocker defaultBaseBlocker() {
        return PieceCellOccupyBlockerFactory.defaultBaseBlocker();
    }
}
