import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private var itemsList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        // Configura RecyclerView
        recyclerView = findViewById(R.id.recyclerViewItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adiciona item
        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString()
            if (name.isNotEmpty()) {
                databaseHelper.insertItem(name)
                loadItems()
            }
        }

        // Atualiza item
        buttonUpdate.setOnClickListener {
            val name = editTextName.text.toString()
            if (name.isNotEmpty()) {
                // Usando ID fictício, você pode adaptar para selecionar o item específico
                val id = 1
                databaseHelper.updateItem(id, name)
                loadItems()
            }
        }

        // Deleta item
        buttonDelete.setOnClickListener {
            val id = 1  // Exemplo de ID, você pode fazer a seleção
            databaseHelper.deleteItem(id)
            loadItems()
        }

        loadItems()
    }

    // Carregar todos os itens
    private fun loadItems() {
        itemsList = databaseHelper.getAllItems().toMutableList()
        adapter = ItemAdapter(itemsList)
        recyclerView.adapter = adapter
    }
}
