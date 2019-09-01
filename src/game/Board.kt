package game

import react.*
import react.dom.div

class Board: RComponent<RProps, Board.State>() {

    init {
        state.squares = Array(9){' '}
        state.xIsNext = true
    }

    interface State:RState{
        var squares: Array<Char>
        var xIsNext: Boolean
    }

    fun handleClick(i: Int) {
        if (calculateWinner(state.squares) != ' ' ||
                state.squares[i] != ' ')
            return
        val tmp = state.squares.copyOf()
        tmp[i] = if (state.xIsNext) 'X' else '0'
        setState{
            squares = tmp
            xIsNext = !xIsNext
        }
    }

    fun calculateWinner(squares: Array<Char>): Char {
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

    fun RBuilder.renderSquare(i: Int) {
        square(state.squares[i], {handleClick(i)})
    }

    override fun RBuilder.render() {
        val winner = calculateWinner(state.squares)
        val status =
                if (winner != ' ')
                    "Winner: $winner"
                else
                    "Next player: ${if (state.xIsNext) "X" else "O"}"
        div(classes = "status"){ +status }
        div {
            div(classes = "board-row") {
                renderSquare(0)
                renderSquare(1)
                renderSquare(2)
            }
            div(classes = "board-row") {
                renderSquare(3)
                renderSquare(4)
                renderSquare(5)
            }
            div(classes = "board-row") {
                renderSquare(6)
                renderSquare(7)
                renderSquare(8)
            }
        }
    }
}

fun RBuilder.board() = child(Board::class) {}