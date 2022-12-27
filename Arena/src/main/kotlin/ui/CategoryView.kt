package ui
import adapter.draft.DraftCategoryAdapter
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class CategoryView(owner: WindowOwner, model: DraftCategoryAdapter) : Dialog<DraftCategoryAdapter>(owner, model) {
    var tituloActual = ""
    override fun addActions(mainPanel: Panel) {
    }

    override fun createFormPanel(mainPanel: Panel) {
        thisWindow.title = tituloActual
        Panel(mainPanel)  with {
            asHorizontal()
            Label(it) with {
                text = "Name"
            }
            TextBox(it) with {
                width = 200
                bindTo("name")
            }
        }
        Panel(mainPanel)  with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick(Action {
                    try {
                        thisWindow.modelObject.validateFields()
                        thisWindow.accept()
                        thisWindow.close()
                    } catch (e: Exception) {
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
