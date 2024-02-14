package tictactoe.name

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class NameController(
    private val nameRepository: NameRepository
) {

    @GetMapping("/name")
    fun helloWorld(model: Model): String {
        if (nameRepository.getName().isNotEmpty()) {
            model.addAttribute("savedName", nameRepository.getName())
        }

        return "name"
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
