package com.mylearning.lld.chess;

import static com.mylearning.lld.chess.conditions.PieceCellOccupyBlockerFactory.defaultAdditionalBlockers;

import java.util.List;

import com.mylearning.lld.chess.contracts.PlayerMove;
import com.mylearning.lld.chess.model.Board;
import com.mylearning.lld.chess.model.Player;

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
