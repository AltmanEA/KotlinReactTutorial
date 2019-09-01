package game

import react.*
import react.dom.div

class Board: RComponent<Board.Props, RState>() {
    interface Props: RProps {
        var squares: Array<Char>
        var onClick: (Int) -> Unit
    }

    fun RBuilder.renderSquare(i: Int) {
        square(props.squares[i], { props.onClick(i) })
    }

    override fun RBuilder.render() {
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

fun RBuilder.board(squares: Array<Char>, onClick: (Int) -> Unit) = child(Board::class) {
    attrs.squares = squares
    attrs.onClick = onClick
}