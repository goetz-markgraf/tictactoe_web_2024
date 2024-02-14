package tictactoe.name

import org.springframework.stereotype.Repository

@Repository
class NameRepository {
    private var nameX: String = ""
    private var nameO: String = ""

    fun getNameX(): String {
        return nameX
    }

    fun getNameO(): String {
        return nameO
    }

    fun setNameX(nameX: String) {
        this.nameX = nameX
    }

    fun setNameO(nameO: String) {
        this.nameO = nameO
    }
}
