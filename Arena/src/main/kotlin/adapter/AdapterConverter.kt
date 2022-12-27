package adapter
import com.github.unqUi.model.Category
import com.github.unqUi.model.Movie

object AdapterConverter {
    fun convertMovieToAdapter(item: Any) : Adapter {
        item as Movie
        return MovieAdapter(item.id, item.title, item.description, item.poster, item.categories, item.relatedContent)
    }

    fun convertCategoryToAdapter(item: Any) : Adapter {
        item as Category
        return CategoryAdapter(item.id, item.name)
    }

    fun convertToAdapterCollection(collection: MutableList<*>, converterFunction: (Any) -> Adapter): MutableList<Adapter> {
        return collection.map { it -> converterFunction(it!!) }.toMutableList()
    }
}