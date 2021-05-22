package com.mylearning.design.solid.examples.chess;

import static com.mylearning.design.solid.examples.chess.conditions.PieceCellOccupyBlockerFactory.defaultAdditionalBlockers;

import java.util.List;

import com.mylearning.design.solid.examples.chess.contracts.PlayerMove;
import com.mylearning.design.solid.examples.chess.model.Board;
import com.mylearning.design.solid.examples.chess.model.Player;

public class GameController {

    public static void gameplay(List<Player> players, Board board) {
        int currentPlayer = 0;
        while (true) {
            Player player = players.get(currentPlayer);
            //TODO: Check if current player has any move possible. If no move possible, then its checkmate.
            PlayerMove playerMove = player.makeMove();
            playerMove.getPiece().move(player, playerMove.getToCell(), board, defaultAdditionalBlockers());
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }
}
