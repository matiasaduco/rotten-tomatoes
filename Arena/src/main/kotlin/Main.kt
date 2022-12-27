import adapter.RottenTomatoesAdapter
import com.github.unqUi.model.getRottenTomatoesSystem
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import ui.RottenTomatoesView

class Main : Application() {
    override fun createMainWindow(): Window<*> {
        val rottenTomatoesSystem = getRottenTomatoesSystem()
        val rottenTomatoesAdapter = RottenTomatoesAdapter(rottenTomatoesSystem)
        return RottenTomatoesView(this, rottenTomatoesAdapter)
    }
}

fun main() {
    Main().start()
}
