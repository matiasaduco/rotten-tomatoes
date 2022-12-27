package adapter.draft
import adapter.Adapter
import adapter.CategoryAdapter
import adapter.MovieAdapter
import com.github.unqUi.model.Category
import com.github.unqUi.model.DraftCategory
import com.github.unqUi.model.DraftMovie
import com.github.unqUi.model.Movie

object DraftConverter {

    fun convertDraftMovieAdapterToDraftMovie(draftMovieAdapter : DraftMovieAdapter) : DraftMovie {
        return DraftMovie(draftMovieAdapter.title, draftMovieAdapter.description, draftMovieAdapter.poster,
                          convertToCategoryCollection(draftMovieAdapter.categories),
                          convertToMovieCollection(draftMovieAdapter.relatedContent)
        )
    }

    fun convertDraftCategoryAdapterToDraftCategory(draftCategoryAdapter: DraftCategoryAdapter): DraftCategory {
        return DraftCategory(draftCategoryAdapter.name)
    }

    private fun convertToCategoryCollection(collection : MutableList<Adapter>) : MutableList<Category>{
        return collection.map { categoryAdapter -> categoryAdapter as CategoryAdapter;
            Category(categoryAdapter.id, categoryAdapter.name) }.toMutableList()
    }

    private fun convertToMovieCollection(collection : MutableList<Adapter>) : MutableList<Movie> {
        return collection.map { movieAdapter -> movieAdapter as MovieAdapter;
            Movie(movieAdapter.id, movieAdapter.title, movieAdapter.description, movieAdapter.poster,
                  movieAdapter.categories, movieAdapter.relatedContent) }.toMutableList()
    }
}