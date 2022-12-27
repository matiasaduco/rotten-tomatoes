package adapter.draft.validateFieldsStrategy
import adapter.draft.DraftAdapter
import adapter.draft.DraftMovieAdapter
import ui.EmptyField

open class EditMovieValidateFieldsStrategy : ValidateFieldsStrategy {

    override fun validateFields(draftAdapter : DraftAdapter) {
        draftAdapter as DraftMovieAdapter
        if (draftAdapter.title.isBlank()) throw EmptyField("Error: Please, enter a title for the movie")
        if (draftAdapter.description.isBlank()) throw EmptyField("Error: Please, enter a description for the movie")
        if (draftAdapter.poster.isBlank()) throw EmptyField("Error: Please, enter a poster for the movie")
        if (draftAdapter.categories.isEmpty()) throw EmptyField("Error: Please, select a category for the movie")
    }
}