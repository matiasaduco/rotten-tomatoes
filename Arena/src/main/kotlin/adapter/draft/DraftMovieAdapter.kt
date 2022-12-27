package adapter.draft
import adapter.Adapter
import adapter.AdapterConverter
import adapter.AdapterConverter.convertToAdapterCollection
import adapter.CategoryAdapter
import adapter.MovieAdapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class DraftMovieAdapter() : DraftAdapter() {
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var selectedMovie : MovieAdapter? = null
    var selectedCategory : CategoryAdapter? = null
    var categories: MutableList<Adapter> = mutableListOf()
    var relatedContent: MutableList<Adapter> = mutableListOf()
    var categoriesSys: MutableList<Adapter> = mutableListOf()
    var relatedContentSys: MutableList<Adapter> = mutableListOf()

    override fun loadBasicDataAdapter(adapter : Adapter) {
        adapter as MovieAdapter
        title = adapter.title
        description = adapter.description
        poster = adapter.poster
        relatedContent.addAll(convertToAdapterCollection(adapter.relatedContent, AdapterConverter :: convertMovieToAdapter))
        categories.addAll(convertToAdapterCollection(adapter.categories, AdapterConverter :: convertCategoryToAdapter))
    }

    fun loadBasicDataSys(categoriesSys: MutableList<Adapter>, relatedContentSys: MutableList<Adapter>) {
        categoriesSys.removeIf { adapterSys -> this.categories.any { it ->  adapterSys.id == it.id} }
        relatedContentSys.removeIf { adapterSys -> this.relatedContent.any { it ->  adapterSys.id == it.id} }
        this.categoriesSys.addAll(categoriesSys)
        this.relatedContentSys.addAll(relatedContentSys)
        this.relatedContentSys.removeIf { movie -> movie as MovieAdapter; movie.title == title }
    }

    fun moveMovieToRelatedContent(movie: MovieAdapter) {
        moveTo(relatedContentSys, relatedContent, movie)
    }

    fun moveMovieToMoviesSys(movie: MovieAdapter) {
        moveTo(relatedContent, relatedContentSys, movie)
    }

    fun moveCategoryToCategories(category: CategoryAdapter) {
        moveTo(categoriesSys,categories, category)
    }

    fun moveCategoryToCategoriesSys(category: CategoryAdapter) {
        moveTo(categories, categoriesSys, category)
    }

    private fun moveTo(collectionA : MutableList<Adapter>, collectionB : MutableList<Adapter>, item: Adapter) {
        val movie = collectionA.find { it -> it.id == item.id}!!
        collectionA.remove(movie)
        collectionB.add(item)
    }
}



