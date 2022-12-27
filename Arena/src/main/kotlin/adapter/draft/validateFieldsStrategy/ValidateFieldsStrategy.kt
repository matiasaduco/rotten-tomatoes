package adapter.draft.validateFieldsStrategy
import adapter.draft.DraftAdapter

interface ValidateFieldsStrategy {
    fun validateFields(draftAdapter: DraftAdapter)
}