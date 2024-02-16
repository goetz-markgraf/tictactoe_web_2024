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
        return if (gameRepository.turn == Player.X) {
            "placeX"
        } else {
            "waitX"
        }
    }

    @PostMapping("/placeX")
    fun placeX(model: Model, input: String?): String {

        model.addAttribute("name", gameRepository.nameX)

        if (input == null) {
            return "placeX"
        }
        val cell = input.toIntOrNull() ?: return "placeX"

        if (cell in 1..9) {
            if (gameRepository.field[cell - 1] !in listOf('X', 'O')) {
                val newField = gameRepository.field.toMutableList()
                newField[cell - 1] = 'X'
                gameRepository.field = newField
                gameRepository.turn = Player.O
                return "waitX"
            }
        }

        return "placeX"
    }

    @GetMapping("/checkTurnO")
    fun checkTurnO(model: Model): String {
        model.addAttribute("name", gameRepository.nameO)
        return if (gameRepository.turn == Player.O) {
            "placeO"
        } else {
            "waitO"
        }
    }

    @PostMapping("/placeO")
    fun placeO(model: Model, input: String?): String {

        model.addAttribute("name", gameRepository.nameO)
        if (input == null) {
            return "placeO"
        }
        val cell = input.toIntOrNull() ?: return "placeO"
        if (cell in 1..9) {
            if (gameRepository.field[cell - 1] !in listOf('X', 'O')) {
                val newField = gameRepository.field.toMutableList()
                newField[cell - 1] = 'O'
                gameRepository.field = newField
                gameRepository.turn = Player.X
                return "waitO"
            }
        }

        return "placeO"
    }
}
