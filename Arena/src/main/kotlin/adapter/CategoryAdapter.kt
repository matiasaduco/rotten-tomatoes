package adapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryAdapter(id: String, var name: String) : Adapter(id)
