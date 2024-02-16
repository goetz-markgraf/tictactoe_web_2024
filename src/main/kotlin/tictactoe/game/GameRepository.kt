package tictactoe.game

import org.springframework.stereotype.Repository
import org.springframework.ui.Model

enum class Player {
    X, O
}



@Repository
class GameRepository {

    var nameX: String = ""
    var nameO: String = ""
    var turn: Player? = null
    var field = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')


    fun fillModel(model: Model) {
        model.addAttribute("field1", field[0])
        model.addAttribute("field2", field[1])
        model.addAttribute("field3", field[2])
        model.addAttribute("field4", field[3])
        model.addAttribute("field5", field[4])
        model.addAttribute("field6", field[5])
        model.addAttribute("field7", field[6])
        model.addAttribute("field8", field[7])
        model.addAttribute("field9", field[8])
    }
}
