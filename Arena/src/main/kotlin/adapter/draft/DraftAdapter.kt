package adapter.draft
import adapter.Adapter
import adapter.AdapterConverter
import adapter.draft.validateFieldsStrategy.ValidateFieldsStrategy
import com.github.unqUi.model.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
open class DraftAdapter() {
    lateinit var validateFieldsStrategy : ValidateFieldsStrategy
    open fun loadBasicDataAdapter(adapter: Adapter) {}
    open fun validateFields() {
        validateFieldsStrategy.validateFields(this)
    }
}
