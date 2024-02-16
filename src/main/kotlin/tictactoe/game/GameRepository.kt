package tictactoe.game

import org.springframework.stereotype.Repository
import org.springframework.ui.Model
import tictactoe.game.Result

enum class Player {
    X, O
}
enum class Result{
    WON, TIE, RUNNING
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

        if (turn != null) {
            val result = check(turn!!)
            when (result) {
                Result.WON -> model.addAttribute("winMessage", "$turn has won")
                Result.TIE -> model.addAttribute("winMessage", "It´s a tie!")
                else -> {}
            }

        }
    }

    fun check(player: Player): Result {
        val symbol = if (player == Player.X) 'X' else 'O'

        if (field[0] == symbol && field[1] == symbol && field[2] == symbol ||
            field[3] == symbol && field[4] == symbol && field[5] == symbol ||
            field[6] == symbol && field[7] == symbol && field[8] == symbol ||
            field[0] == symbol && field[3] == symbol && field[6] == symbol ||
            field[1] == symbol && field[4] == symbol && field[7] == symbol ||
            field[2] == symbol && field[5] == symbol && field[8] == symbol ||
            field[0] == symbol && field[4] == symbol && field[8] == symbol ||
            field[2] == symbol && field[4] == symbol && field[6] == symbol
        ) {
          return Result.WON

        } else if(field.all{it == 'X' || it == 'O'}) {

            return Result.TIE
        }
        else{
            return Result.RUNNING
        }
    }

}