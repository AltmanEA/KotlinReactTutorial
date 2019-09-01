package game

import react.*
import react.dom.div

class Board: RComponent<RProps, Board.State>() {

    init {
        state.squares = Array(9){' '}
    }

    interface State:RState{
        var squares: Array<Char>
    }

    fun handleClick(i: Int) {
        val tmp = state.squares.copyOf()
        tmp[i]='X'
        setState{
            squares = tmp
        }
    }

    val status = "Next player: X"
    fun RBuilder.renderSquare(i: Int) {
        square(state.squares[i], {handleClick(i)})
    }

    override fun RBuilder.render() {
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