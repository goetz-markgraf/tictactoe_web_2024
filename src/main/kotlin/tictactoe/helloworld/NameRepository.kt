package tictactoe.helloworld

import org.springframework.stereotype.Repository

@Repository
class NameRepository {
    private var name: String = ""

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}
