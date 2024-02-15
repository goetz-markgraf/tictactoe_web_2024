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
    var field = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
}