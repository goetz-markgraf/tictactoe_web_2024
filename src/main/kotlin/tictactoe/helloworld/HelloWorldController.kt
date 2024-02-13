package tictactoe.helloworld

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HelloWorldController {

    @GetMapping("/")
    fun helloWorld(model: Model): String {
        model.addAttribute("message", "Hello, World!")
        return "index"
    }

    @PostMapping("/setname")
    fun setName(model: Model, name: String): String {
        model.addAttribute("name", "Hello, $name!")
        return "greeting"
    }
}
