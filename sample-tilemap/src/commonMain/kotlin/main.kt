import com.soywiz.klock.*
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.tiled.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.async.*
import com.soywiz.korio.file.std.*
import engineEmi.ScreenElements.CanvasElements.*

suspend fun main() = Korge(width = 512, height = 512) {
	val tiledMap = resourcesVfs["gfx/sample.tmx"].readTiledMap()
	fixedSizeContainer(256, 256, clip = true) {
		position(128, 128)
		val camera = camera {
			tiledMapView(tiledMap) {
			}
		}

		val bitmap = resourcesVfs["gfx/character.png"].readBitmap()


		val spriteView = SpriteView()
		val animationDown = SpriteAnimation(
			x = 100,
			y = 100,
			columns = 4,
			marginTop = 0,
			marginLeft = 1,
			lines = 1,
			spriteHeight = 32,
			spriteWidth = 16,
			bitmap = bitmap,
			spriteView = spriteView
		)

		val animationRight = SpriteAnimation(
			x = 100,
			y = 100,
			columns = 4,
			lines = 1,
			marginTop = 32,
			marginLeft = 1,
			spriteWidth = 16,
			spriteHeight = 32,
			bitmap = bitmap,
			spriteView = spriteView
		)

		val animationUp = SpriteAnimation(
			x = 100,
			y = 100,
			columns = 4,
			lines = 1,
			marginTop = 64,
			marginLeft = 1,
			spriteWidth = 16,
			spriteHeight = 32,
			bitmap = bitmap,
			spriteView = spriteView
		)

		val animationLeft = SpriteAnimation(
			x = 100,
			y = 100,
			columns = 4,
			lines = 1,
			marginTop = 96,
			marginLeft = 1,
			spriteWidth = 16,
			spriteHeight = 32,
			bitmap = bitmap,
			spriteView = spriteView
		)


		val animationController = AnimationController()


		animationController.addAnimation("left", animationLeft)
		animationController.addAnimation("right", animationRight)
		animationController.addAnimation("up", animationUp)
		animationController.addAnimation("down", animationDown)

		addChild(spriteView)

		launchImmediately{
			while(true) {
				spriteView.updateGraphics()
				delay(16.milliseconds)
			}
		}


		keys {
			down {
				launchImmediately {
					when (key) {
						Key.RIGHT -> {camera.moveBy(-16, 0, 0.25.seconds); animationController.play("right")}
						Key.LEFT -> {camera.moveBy(+16, 0, 0.25.seconds); animationController.play("left")}
						Key.DOWN -> {camera.moveBy(0, -16, 0.25.seconds); animationController.play("down")}
						Key.UP -> {camera.moveBy(0, +16, 0.25.seconds); animationController.play("up")}
					}
				}
			}
		}
	}
}
