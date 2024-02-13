package tictactoe.helloworld

import org.springframework.stereotype.Service

@Service
class NameService {
    private var name: String = ""

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}
