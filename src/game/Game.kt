package game

import react.*
import react.dom.div

class Game(): RComponent<RProps, Game.State>() {
    init {
        state.apply {
            history = mutableListOf(
                    HistoryEntry(Array<Char>(9) { ' ' })
            )
            xIsNext = true
        }
    }

    interface State: RState {
        var history: List<HistoryEntry>
        var xIsNext: Boolean
    }

    data class HistoryEntry(var squares: Array<Char>)

    private fun calculateWinner(squares: Array<Char>): Char {
        val solutions = arrayOf(
                arrayOf(0, 1, 2),
                arrayOf(3, 4, 5),
                arrayOf(6, 7, 8),
                arrayOf(0, 3, 6),
                arrayOf(1, 4, 7),
                arrayOf(2, 5, 8),
                arrayOf(0, 4, 8),
                arrayOf(2, 4, 6))
        for (solution in solutions){
            val (a, b, c) = solution
            if (squares[a] != ' ' && squares[a] == squares[b] && squares[a] == squares[c])
                return squares[a]
        }
        return ' '
    }

    private fun handleClick(i: Int) {
        var tmpHistory = state.history
        val current = tmpHistory.last()
        val squares = current.squares.copyOf()
        if (calculateWinner(squares) != ' ' ||
                squares[i] != ' ')
            return
        squares[i] = if (state.xIsNext) 'X' else '0'
        tmpHistory += HistoryEntry(squares)
        setState{
            history = tmpHistory
            xIsNext = !xIsNext
        }
    }

    override fun RBuilder.render() {
        val history = state.history
        val current = history.last()
        val winner = calculateWinner(current.squares)
        val status =
                if (winner != ' ')
                    "Winner: $winner"
                else
                    "Next player: ${if (state.xIsNext) "X" else "O"}"

        div(classes = "game") {
            div(classes = "game-board") {
                board(current.squares) {handleClick(it)}
            }
            div(classes = "game-info") {
                div { +status }
                // TO DO
            }
        }
    }
}

fun RBuilder.game() = child(Game::class) {}