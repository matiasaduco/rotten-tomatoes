package ui
import adapter.*
import adapter.draft.DraftCategoryAdapter
import adapter.draft.DraftMovieAdapter
import adapter.draft.validateFieldsStrategy.AddCategoryValidateFieldsStrategy
import adapter.draft.validateFieldsStrategy.AddMovieValidateFieldsStrategy
import adapter.draft.validateFieldsStrategy.EditCategoryValidateFieldsStrategy
import adapter.draft.validateFieldsStrategy.EditMovieValidateFieldsStrategy
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class RottenTomatoesView(owner: WindowOwner, model: RottenTomatoesAdapter) : SimpleWindow<RottenTomatoesAdapter>(owner, model) {
    override fun addActions(mainPanel: Panel) {
    }

    override fun createFormPanel(mainPanel: Panel) {
        Panel(mainPanel) with {
            asHorizontal()
            table<CategoryAdapter>(it) with {
                bindItemsTo("categories")
                bindSelectionTo("selectedCategory")
                visibleRows = 7
                column {
                    title = "ID"
                    fixedSize = 50
                    bindContentsTo("id")
                }
                column {
                    title = "Nombre"
                    fixedSize = 330
                    bindContentsTo("name")
                }
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Add Category"
                onClick {
                    val draftCategory = DraftCategoryAdapter()
                    draftCategory.validateFieldsStrategy = AddCategoryValidateFieldsStrategy(thisWindow.modelObject.categories)
                    val window = CategoryView(thisWindow, draftCategory)
                    window.tituloActual = "Add Category"
                    window.onAccept {
                        thisWindow.modelObject.addCategory(draftCategory)
                    }
                    window.open()
                }
            }
            Button(it) with {
                caption = "Edit Category"
                bindEnabledTo("activateCategoryEditButton")
                onClick {
                    val draftCategory = DraftCategoryAdapter()
                    draftCategory.loadBasicDataAdapter(thisWindow.modelObject.selectedCategory!!)
                    draftCategory.validateFieldsStrategy = EditCategoryValidateFieldsStrategy()
                    val window = CategoryView(thisWindow, draftCategory)
                    val selectedCategoryID = thisWindow.modelObject.selectedCategory!!.id
                    window.tituloActual = "Edit Category"
                    window.onAccept {
                        thisWindow.modelObject.editCategory(selectedCategoryID, draftCategory)
                    }
                    window.open()
                }
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            table<MovieAdapter>(it) with {
                bindItemsTo("movies")
                bindSelectionTo("selectedMovie")
                visibleRows = 7
                column {
                    title = "ID"
                    fixedSize = 30
                    bindContentsTo("id")
                }
                column {
                    title = "Título"
                    fixedSize = 180
                    bindContentsTo("title")
                }
                column {
                    title = "Poster"
                    fixedSize = 180
                    bindContentsTo("poster")
                }
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Add Movie"
                onClick {
                    val draftMovie = DraftMovieAdapter()
                    draftMovie.loadBasicDataSys(thisWindow.modelObject.categories, thisWindow.modelObject.movies)
                    draftMovie.validateFieldsStrategy = AddMovieValidateFieldsStrategy()
                    val window = MovieDataView(thisWindow, draftMovie)
                    window.tituloActual = "Add Movie"
                    window.onAccept {
                        thisWindow.modelObject.addMovie(draftMovie)
                    }
                    window.open()
                }
            }

            Button(it) with {
                caption = "Edit Movie"
                bindEnabledTo("activateMovieEditButton")
                onClick {
                    val movieAdapter = thisWindow.modelObject.selectedMovie!!
                    val draftMovie = DraftMovieAdapter()
                    draftMovie.loadBasicDataAdapter(movieAdapter)
                    draftMovie.loadBasicDataSys(thisWindow.modelObject.categories, thisWindow.modelObject.movies)
                    draftMovie.validateFieldsStrategy = EditMovieValidateFieldsStrategy()
                    val window = MovieDataView(thisWindow, draftMovie)
                    window.tituloActual = "Edit Movie"
                    window.onAccept {
                        thisWindow.modelObject.editMovie(movieAdapter.id, draftMovie)
                    }
                    window.open()
                }
            }
        }
    }
}
