package engineEmi.ScreenElements.CanvasElements
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice

/**
 * Ein SpriteView wird vor allem für Sprite-Animationen gebraucht
 * Bei der Erstellung einer [SpriteAnimation] wird immer ein [SpriteView]
 * benötigt, auf welchen die Animationen abgebildet werden.
 * @constructor
 */
class SpriteView : Container() {
    var sprite: BmpSlice = Bitmaps.transparent
        set(value) {
            field = value
            updateGraphics()
        }

    init {
        updateGraphics()
    }

    /**
     * Aktualisiert das angezeigte Sprite
     * @param sprite BmpSlice
     * @param skalierung Double
     */
    fun refreshViewWithSprite(sprite: BmpSlice, skalierung: Double, x : Double, y : Double) {
		this.sprite = sprite
        this.scale = skalierung
		this.x = x
		this.y = y
    }


    fun updateGraphics() {
        removeChildren()
        image(sprite) {
            position(x, y)
        }.scale(super.scale)
    }
}
