package adapter.draft.validateFieldsStrategy

import adapter.Adapter
import adapter.CategoryAdapter
import adapter.draft.DraftAdapter
import adapter.draft.DraftCategoryAdapter
import ui.EmptyField

open class AddCategoryValidateFieldsStrategy(private val categoriesSys: MutableList<Adapter>) : EditCategoryValidateFieldsStrategy() {

    override fun validateFields(draftAdapter: DraftAdapter) {
        draftAdapter as DraftCategoryAdapter
        super.validateFields(draftAdapter)
        if (categoriesSys.find { category -> category as CategoryAdapter; category.name == draftAdapter.name} != null) throw EmptyField("Error: A category with that name already exist")
    }
}