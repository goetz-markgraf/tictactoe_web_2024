package tictactoe.game

import org.springframework.stereotype.Repository

enum class Player {
    X, O
}

@Repository
class GameRepository {
    var nameX: String = ""
    var nameO: String = ""
    var turn: Player? = null
}