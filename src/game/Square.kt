package game

import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.button

class Square():RComponent<Square.Props, RState>() {

    interface Props: RProps {
        var squares:Char
        var onClick: (Event) -> Unit
    }

    override fun RBuilder.render() {
        button(classes = "square") {
            +(props.squares.toString())
            attrs.onClickFunction = props.onClick
        }
    }
}

fun RBuilder.square(square: Char, onClick: (Event) -> Unit) = child(Square::class) {
    attrs.squares = square
    attrs.onClick = onClick
}