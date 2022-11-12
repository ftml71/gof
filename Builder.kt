/**
A creational design pattern that provides 
stepwise construction of complex objects.
**/

class GameEnvironmentBuilder {

    private var quality = RenderQuality.MEDIUM
    private var frequency = 0.0f
    private var timeOfDay = TimeOfDay.MORNING
    private var scene = Scene.BARRACKS

    fun setRenderQuality(quality: RenderQuality): GameEnvironmentBuilder {
        this.quality = quality
        return this
    }

    fun setRocksFrequency(
        @FloatRange(from = 0.0, to = 1.0) frequency: Float
    ): GameEnvironmentBuilder {
        this.frequency = frequency
        return this
    }

    fun setTimeOfDay(timeOfDay: TimeOfDay): GameEnvironmentBuilder {
        this.timeOfDay = timeOfDay
        return this
    }

    fun setScene(scene: Scene): GameEnvironmentBuilder {
        this.scene = scene
        return this
    }

    fun build(): GameEnvironment {
        return GameEnvironment(quality, frequency, timeOfDay, scene)
    }

}

enum class TimeOfDay {
    MORNING, NOON, AFTERNOON, EVENING, NIGHT
}

enum class Scene {
    FOREST, CITY, CAVE, BARRACKS, CLUB
}

enum class RenderQuality {
    LOW, MEDIUM, MAX, ULTRA
}

data class GameEnvironment(
    val quality: RenderQuality,
    val frequencyOfRocks: Float,
    val timeOfDay: TimeOfDay,
    val scene: Scene
)

fun main(){
    val gv = GameEnvironmentBuilder().run {
                setRenderQuality(RenderQuality.ULTRA)
                setRocksFrequency(0.8f)
                setScene(Scene.CLUB)
                setTimeOfDay(TimeOfDay.MORNING)
                build()
             }

    assert(gv.quality == RenderQuality.ULTRA)
}
