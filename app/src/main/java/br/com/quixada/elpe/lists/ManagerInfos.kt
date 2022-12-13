package br.com.quixada.elpe.lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ManagerInfos : AppCompatActivity() {

    private val arrayTitle = ArrayList<String>()
    private val arrayDescription = ArrayList<String>()
    private val arrayIds = ArrayList<String>()
    private lateinit var recyclerView: RecyclerView
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_infos)
        val trate = intent.extras?.get("option")?.toString()
        when(trate){
            "e" -> loadFragment(EditElements())
            "a" -> loadFragment(AddElements())
        }

    }
    private fun loadFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment, fragment)
        fragmentManager.commit()
    }
}