package adapter.draft
import adapter.Adapter
import adapter.CategoryAdapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class DraftCategoryAdapter() : DraftAdapter() {
    var name: String = ""

    override fun loadBasicDataAdapter(adapter: Adapter) {
        adapter as CategoryAdapter
        name = adapter.name
    }
}
