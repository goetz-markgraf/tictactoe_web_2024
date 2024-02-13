package tictactoe.helloworld

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HelloWorldController(
    private val nameService: NameService
) {

    @GetMapping("/")
    fun helloWorld(model: Model): String {
        model.addAttribute("message", "Hello, World!")
        if (nameService.getName().isNotEmpty()) {
            model.addAttribute("savedName", nameService.getName())
        }
        return "index"
    }

    @PostMapping("/setname")
    fun setName(model: Model, name: String): String {
        nameService.setName(name)
        model.addAttribute("name", nameService.getName())
        return "greeting"
    }
}
