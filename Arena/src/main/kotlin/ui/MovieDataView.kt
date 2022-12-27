package ui
import adapter.CategoryAdapter
import adapter.MovieAdapter
import adapter.draft.DraftMovieAdapter
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class MovieDataView(owner: WindowOwner, model: DraftMovieAdapter) : Dialog<DraftMovieAdapter>(owner, model) {
    var tituloActual = ""
    override fun addActions(mainPanel: Panel) {
    }

    override fun createFormPanel(mainPanel: Panel) {
        thisWindow.title = tituloActual
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Title"
            }
            TextBox(it) with {
                width = 200
                bindTo("title")
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Description"
            }
            TextBox(it) with {
                width = 200
                bindTo("description")
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Poster"
            }
            TextBox(it) with {
                width = 200
                bindTo( "poster")
            }
        }
        Label(mainPanel) with {
            text = "Categories"
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<CategoryAdapter>(it) with  {
                bindItemsTo("categoriesSys").adaptWithProp<CategoryAdapter>("name")
                bindSelectedTo("selectedCategory")
                width = 200
                height = 120
            }
            Button(it) with {
                caption = "→"
                onClick(Action {
                    try {
                    thisWindow.modelObject.moveCategoryToCategories(thisWindow.modelObject.selectedCategory!!)
                    } catch (_: NullPointerException) {}
                })
            }
            Button(it) with {
                caption = "←"
                onClick(Action {
                    try {
                        thisWindow.modelObject.moveCategoryToCategoriesSys(thisWindow.modelObject.selectedCategory!!)
                    } catch (_: NullPointerException) {}
                })
            }
            List<CategoryAdapter>(it) with  {
                bindItemsTo("categories").adaptWithProp<CategoryAdapter>("name")
                bindSelectedTo("selectedCategory")
                width = 200
                height = 120
            }
        }
        Label(mainPanel) with {
            text = "Related Content"
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<MovieAdapter>(it) with {
                bindItemsTo("relatedContentSys").adaptWithProp<MovieAdapter>("title")
                bindSelectedTo("selectedMovie")
                width = 200
                height = 120
            }
            Button(it) with {
                caption = "→"
                onClick(Action {
                    try {
                    thisWindow.modelObject.moveMovieToRelatedContent(thisWindow.modelObject.selectedMovie!!)
                    } catch (_: NullPointerException) {}
                })
            }
            Button(it) with {
                caption = "←"
                onClick(Action {
                    try {
                    thisWindow.modelObject.moveMovieToMoviesSys(thisWindow.modelObject.selectedMovie!!)
                    } catch (_: NullPointerException) {}
                })
            }
            List<MovieAdapter>(it) with {
                bindItemsTo("relatedContent").adaptWithProp<MovieAdapter>("title")
                bindSelectedTo("selectedMovie")
                width = 200
                height = 120
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick(Action {
                    try {
                        thisWindow.modelObject.validateFields()
                        thisWindow.accept()
                        thisWindow.close()
                    } catch (e: EmptyField) {
                        thisWindow.showError(e.message)
                    }
                })
            }
            Button(it) with {
                caption = "Cancel"
                onClick(Action {
                    thisWindow.cancel()
                    thisWindow.close()
                })
            }
        }
    }
}