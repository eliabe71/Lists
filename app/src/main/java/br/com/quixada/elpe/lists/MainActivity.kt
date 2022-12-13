package br.com.quixada.elpe.lists

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private val arrayTitle = ArrayList<String>()
    private val arrayDescription = ArrayList<String>()
    private val arrayIds = ArrayList<String>()
    private val arrayIdsFirebase = ArrayList<String>()
    private lateinit var recyclerView: RecyclerView
    private val db = Firebase.firestore
    private lateinit var buttonEdit: Button
    private lateinit var buttonAdd: Button

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        buttonEdit = findViewById(R.id.buttonEdit)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            var intent = Intent(this, ManagerInfos::class.java)
            intent.putExtra("option", "a")
            //intent.putExtra("ids", arrayIds)
            intent.putExtra("id", arrayTitle.size+1)
            startActivity(intent)
        }
        buttonEdit.setOnClickListener {
            var intent = Intent(this, ManagerInfos::class.java)
            intent.putExtra("option", "e")
            intent.putExtra("ids", arrayIds.size)
            //intent.putExtra("idFire", arrayIdsFirebase)
            startActivity(intent)
        }
        val myAdapter = LineAdapter(arrayTitle,arrayDescription,arrayIds)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
        getData()


    }

    override fun onRestart() {
        getData()
        super.onRestart()
    }
    override fun onStart() {

        super.onStart()
    }
    private fun getData(){
        arrayTitle.clear()
        arrayDescription.clear()
        arrayIds.clear()
        arrayIdsFirebase.clear()
        db.collection("dados").get().addOnSuccessListener {
                result ->
            for(document in result){
                arrayTitle.add(document.data["titulo"].toString())
                arrayDescription.add(document.data["descricao"].toString())
                arrayIds.add(document.data["id"].toString())
                arrayIdsFirebase.add(document.id)
                //Toast.makeText(this, "AQUi"+document.data["titulo"].toString(), Toast.LENGTH_LONG).show()
            }
            recyclerView.adapter?.notifyDataSetChanged()
        }


    }
}