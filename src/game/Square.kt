package game

import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.button

fun RBuilder.square(square: Char, onClick: (Event) -> Unit) =
        button(classes = "square") {
            +(square.toString())
            attrs.onClickFunction = onClick
        }