package game

import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.button

class Square():RComponent<Square.Props, Square.State>() {

    interface Props: RProps {
        var squares:Char
    }
    interface State: RState {
        var value:String?
    }

    val onClick: (event:Event)->Unit = {
        setState{
            state.value = "X"
        }
    }

    override fun RBuilder.render() {
        button(classes = "square") {
            +(state.value ?:"")
            attrs.onClickFunction = onClick
        }
    }
}

fun RBuilder.square(square: Char) = child(Square::class) {
    attrs.squares = square
}