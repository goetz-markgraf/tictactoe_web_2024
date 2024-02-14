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
            return "questionNameX"
        } else if (gameRepository.nameO.isEmpty()) {
            return "questionNameO"
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

        return "error"
    }

    @PostMapping("/setnameO")
    fun setNameO(model: Model, name: String): String {

        if (gameRepository.nameO.isEmpty()) {
            model.addAttribute("name", name)
            gameRepository.nameO = name
            gameRepository.turn = Player.X
            return "waitO"
        }

        return "error"
    }
}
