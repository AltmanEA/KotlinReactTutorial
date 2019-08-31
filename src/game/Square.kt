package game

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button

class Square():RComponent<Square.Props, RState>() {

    interface Props: RProps {
        var squares:String?
    }

    override fun RBuilder.render() {
        button(classes = "square") {
            +(props.squares?:"")
        }
    }
}

fun RBuilder.square(square: String?) = child(Square::class) {
    attrs.squares = square
}