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

        if (nameRepository.getNameX().isNotEmpty()) {
            model.addAttribute("savedName", nameRepository.getNameX())
        } else if (nameRepository.getNameO().isNotEmpty()){
            model.addAttribute("savedName", nameRepository.getNameO())
        }

        return "name"
    }

    @PostMapping("/setname")
    fun setName(model: Model, nameX: String, nameO: String): String {

        if (nameX.isNotEmpty() && nameO.isNotEmpty()) {
            model.addAttribute("name", nameRepository.getNameX())
            model.addAttribute("name", nameRepository.getNameO())
        }
        else if (nameX.isEmpty() && nameO.isEmpty()) {
            model.addAttribute("name", "noname")
        }

        return "greeting"
    }
}