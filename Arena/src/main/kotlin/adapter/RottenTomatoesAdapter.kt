package adapter
import adapter.draft.DraftCategoryAdapter
import adapter.draft.DraftConverter
import adapter.draft.DraftMovieAdapter
import com.github.unqUi.model.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class RottenTomatoesAdapter(val rottenTomatoes: RottenTomatoesSystem) {
    var movies: MutableList<Adapter> = AdapterConverter.convertToAdapterCollection(rottenTomatoes.movies, AdapterConverter::convertMovieToAdapter)
    var categories: MutableList<Adapter> = AdapterConverter.convertToAdapterCollection(rottenTomatoes.categories, AdapterConverter::convertCategoryToAdapter)
    var activateMovieEditButton = false
    var activateCategoryEditButton = false
    var selectedMovie: MovieAdapter? = null
        set(value) {
            field = value
            this.activateMovieEditButton = value != null
        }
    var selectedCategory: CategoryAdapter? = null
        set(value) {
            field = value
            this.activateCategoryEditButton = value != null
        }

    fun addCategory(draftCategoryAdapter: DraftCategoryAdapter) {
        rottenTomatoes.addCategory(DraftConverter.convertDraftCategoryAdapterToDraftCategory(draftCategoryAdapter))
        categories = AdapterConverter.convertToAdapterCollection(rottenTomatoes.categories, AdapterConverter::convertCategoryToAdapter)
    }

    fun editCategory(selectedCategoryID: String, draftCategoryAdapter: DraftCategoryAdapter) {
        rottenTomatoes.editCategory(selectedCategoryID, DraftConverter.convertDraftCategoryAdapterToDraftCategory(draftCategoryAdapter))
        categories = AdapterConverter.convertToAdapterCollection(rottenTomatoes.categories, AdapterConverter::convertCategoryToAdapter)
    }

    fun addMovie(draftMovieAdapter: DraftMovieAdapter) {
        rottenTomatoes.addMovie(DraftConverter.convertDraftMovieAdapterToDraftMovie(draftMovieAdapter))
        movies = AdapterConverter.convertToAdapterCollection(rottenTomatoes.movies, AdapterConverter::convertMovieToAdapter)
    }

    fun editMovie(selectedMovieID: String, draftMovieAdapter: DraftMovieAdapter) {
        rottenTomatoes.editMovie(selectedMovieID, DraftConverter.convertDraftMovieAdapterToDraftMovie(draftMovieAdapter))
        movies = AdapterConverter.convertToAdapterCollection(rottenTomatoes.movies, AdapterConverter::convertMovieToAdapter)
    }
}
