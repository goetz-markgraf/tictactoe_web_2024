package tictactoe

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloWorldController {
    @GetMapping("/")
    fun helloWorld(): String {
        return "index"
    }
}
