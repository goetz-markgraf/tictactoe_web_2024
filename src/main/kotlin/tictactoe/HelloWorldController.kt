package tictactoe

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloWorldController {
    @GetMapping("/")
    fun helloWorld(model: Model): String {
        model.addAttribute("message", "Hallo Spieler!")

        return "index"
    }
}
