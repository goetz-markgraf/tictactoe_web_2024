package tictactoe.game

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class GameController(
    private val gameRepository: GameRepository
) {

    @GetMapping("/tictactoe")
    fun helloWorld(model: Model): String {

        if (gameRepository.nameX.isEmpty()) {
            return "askNameX"
        } else if (gameRepository.nameO.isEmpty()) {
            return "askNameO"
        }

        return "gameRunning"
    }

    @PostMapping("/setnameX")
    fun setNameX(model: Model, name: String): String {

        if (gameRepository.nameX.isEmpty()) {
            model.addAttribute("name", name)
            gameRepository.nameX = name
            return "waitX"
        }

        if (gameRepository.nameO.isEmpty()) {
            return "askNameO"
        }

        return "gameRunning"
    }

    @PostMapping("/setnameO")
    fun setNameO(model: Model, name: String): String {

        if (gameRepository.nameO.isEmpty()) {
            model.addAttribute("name", name)
            gameRepository.nameO = name
            gameRepository.turn = Player.X
            return "waitO"
        }

        return "gameRunning"
    }

    @GetMapping("/checkTurnX")
    fun checkTurnX(model: Model): String {

        model.addAttribute("name", gameRepository.nameX)
        gameRepository.fillModel(model)

        return if (gameRepository.turn == Player.X) {
            "placeX"
        } else {
            "waitX"
        }
    }

    @PostMapping("/placeX")
    fun placeX(model: Model, input: String?): String {

        model.addAttribute("name", gameRepository.nameX)
        gameRepository.fillModel(model)

        if (input == null) {
            return "placeX"
        }
        val cell = input.toIntOrNull()

        if (cell == null) {
            return "placeX"
        }

        if (cell in 1..9) {
            if (gameRepository.field[cell - 1] !in listOf('X', 'O')) {
                val newField = gameRepository.field.toMutableList()
                newField[cell - 1] = 'X'
                gameRepository.field = newField
                gameRepository.turn = Player.O
                gameRepository.fillModel(model)
                return "waitX"
            } else {
                return "placeX"
            }
        }

        return "placeX"
    }

    @GetMapping("/checkTurnO")
    fun checkTurnO(model: Model): String {
        model.addAttribute("name", gameRepository.nameO)
        gameRepository.fillModel(model)
        return if (gameRepository.turn == Player.O) {
            "placeO"
        } else {
            "waitO"
        }
    }

    @PostMapping("/placeO")
    fun placeO(model: Model, input: String?): String {

        model.addAttribute("name", gameRepository.nameO)
        gameRepository.fillModel(model)

        if (input == null) {
            return "placeO"
        }
        val cell = input.toIntOrNull()

        if (cell == null) {
            return "placeO"
        }

        if (cell in 1..9) {
            if (gameRepository.field[cell - 1] !in listOf('X', 'O')) {
                val newField = gameRepository.field.toMutableList()
                newField[cell - 1] = 'O'
                gameRepository.field = newField
                gameRepository.turn = Player.X
                gameRepository.fillModel(model)
                return "waitO"
            } else {
                return "placeO"
            }
        }

        return "placeO"
    }

    @GetMapping("/checkWin")
    fun check(model: Model, player: Player) {
        val symbol = if (player == Player.X) 'X' else 'O'

        if (gameRepository.field[0] == symbol && gameRepository.field[1] == symbol && gameRepository.field[2] == symbol ||
            gameRepository.field[3] == symbol && gameRepository.field[4] == symbol && gameRepository.field[5] == symbol ||
            gameRepository.field[6] == symbol && gameRepository.field[7] == symbol && gameRepository.field[8] == symbol ||
            gameRepository.field[0] == symbol && gameRepository.field[3] == symbol && gameRepository.field[6] == symbol ||
            gameRepository.field[1] == symbol && gameRepository.field[4] == symbol && gameRepository.field[7] == symbol ||
            gameRepository.field[2] == symbol && gameRepository.field[5] == symbol && gameRepository.field[8] == symbol ||
            gameRepository.field[0] == symbol && gameRepository.field[4] == symbol && gameRepository.field[8] == symbol ||
            gameRepository.field[2] == symbol && gameRepository.field[4] == symbol && gameRepository.field[6] == symbol
        ) {
            val winner = if (symbol == 'X') Player.X else Player.O
            model.addAttribute("winner", winner)
        } else {
            model.addAttribute("tie", "tie")
        }
    }

    @GetMapping("/reset")
    fun reset(model: Model, ): String {
        gameRepository.nameX = ""
        gameRepository.nameO = ""
        gameRepository.turn = null
        gameRepository.field = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
        return "askNameX"
    }
}