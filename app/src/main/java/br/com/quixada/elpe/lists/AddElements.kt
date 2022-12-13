package br.com.quixada.elpe.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddElements : Fragment() {

    val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_elements, container, false)
        val editTextName = view.findViewById<EditText>(R.id.editTextTextPersonName)
        val editTextDescption = view.findViewById<EditText>(R.id.description)
        val textView = view.findViewById<TextView>(R.id.idText)
        val extras = activity?.intent?.extras
        val buttonFinish = view.findViewById<Button>(R.id.finish)
        val buttonCancel = view.findViewById<Button>(R.id.cancel)
        textView.text = "Id: #"+extras?.get("id")?.toString() ?: ""
        buttonFinish.setOnClickListener {
            var data = hashMapOf(
                "id" to (extras?.get("id")?.toString()?.toInt() ?: 0),
                "titulo" to editTextName?.text.toString(),
                "descricao" to editTextDescption?.text.toString()
            )
            db.collection("dados").add(data).addOnSuccessListener {
                Toast.makeText(activity, "Novo Item Adcionado", Toast.LENGTH_LONG).show()
                activity?.onBackPressed()
            }
        }

        buttonCancel.setOnClickListener { activity?.onBackPressed() }


        return view

    }
}