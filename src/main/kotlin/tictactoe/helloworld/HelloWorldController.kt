package tictactoe.helloworld

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HelloWorldController(
    private val nameRepository: NameRepository
) {

    @GetMapping("/")
    fun helloWorld(model: Model): String {
        model.addAttribute("message", "Hello, World!")

        if (nameRepository.getName().isNotEmpty()) {
            model.addAttribute("savedName", nameRepository.getName())
        }

        return "index"
    }

    @PostMapping("/setname")
    fun setName(model: Model, name: String): String {
        nameRepository.setName(name)

        if (name.isEmpty()) {
            model.addAttribute("name", "man without a name")
        } else {
            model.addAttribute("name", nameRepository.getName())
        }

        return "greeting"
    }
}
