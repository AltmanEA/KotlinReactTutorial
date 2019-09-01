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
        val tmp = state.squares.copyOf()
        tmp[i] = if (state.xIsNext) 'X' else '0'
        setState{
            squares = tmp
            xIsNext = !xIsNext
        }
    }

    fun RBuilder.renderSquare(i: Int) {
        square(state.squares[i], {handleClick(i)})
    }

    override fun RBuilder.render() {
        val status = "Next player: ${if (state.xIsNext) 'X' else '0'}"
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