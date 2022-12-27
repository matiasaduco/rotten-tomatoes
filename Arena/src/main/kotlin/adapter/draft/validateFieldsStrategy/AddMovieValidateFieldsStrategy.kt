package adapter.draft.validateFieldsStrategy

import adapter.MovieAdapter
import adapter.draft.DraftAdapter
import adapter.draft.DraftMovieAdapter
import ui.EmptyField

class AddMovieValidateFieldsStrategy : EditMovieValidateFieldsStrategy() {

    override fun validateFields(draftAdapter: DraftAdapter) {
        draftAdapter as DraftMovieAdapter
        super.validateFields(draftAdapter)
        if (draftAdapter.relatedContentSys.find { movie -> movie as MovieAdapter; movie.title == draftAdapter.title} != null) throw EmptyField("Error: A movie with that title already exist")
    }
}