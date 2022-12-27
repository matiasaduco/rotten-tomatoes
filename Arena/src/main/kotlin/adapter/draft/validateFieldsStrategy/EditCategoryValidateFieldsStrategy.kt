package adapter.draft.validateFieldsStrategy
import adapter.draft.DraftAdapter
import adapter.draft.DraftCategoryAdapter
import ui.EmptyField

open class EditCategoryValidateFieldsStrategy : ValidateFieldsStrategy {

    override fun validateFields(draftAdapter: DraftAdapter) {
        draftAdapter as DraftCategoryAdapter
        if (draftAdapter.name.isBlank()) throw EmptyField("Error: Please, enter a name for the category")
    }
}