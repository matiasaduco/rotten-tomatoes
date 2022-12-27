package adapter
import com.github.unqUi.model.Category
import com.github.unqUi.model.Movie
import org.uqbar.commons.model.annotations.Observable

@Observable
class MovieAdapter(id: String,
                   var title: String,
                   var description: String,
                   var poster: String,
                   var categories: MutableList<Category>,
                   var relatedContent: MutableList<Movie>) : Adapter(id)


