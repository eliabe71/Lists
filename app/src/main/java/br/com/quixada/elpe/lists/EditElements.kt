package br.com.quixada.elpe.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class EditElements : Fragment() {

    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_elements, container, false)
        val editTextNumber = view.findViewById<EditText>(R.id.editTextNumber)
        val editTextName = view.findViewById<EditText>(R.id.editTextTextPersonName2)
        val editTextDescption = view.findViewById<EditText>(R.id.editTextTextPersonName3)
        val buttonSave = view.findViewById<Button>(R.id.salvar)
        val buttonCancel = view.findViewById<Button>(R.id.cancel)
        val extras = activity?.intent?.extras
        val ids = extras?.get("ids")?.toString()?.toInt()
        //val idsFire = extras?.get("idFire") as ArrayList<String>
        buttonCancel.setOnClickListener { activity?.onBackPressed() }
        buttonSave.setOnClickListener {
            if(editTextNumber.text.toString().toInt() > ids!! ||
                editTextNumber.text.toString().toInt() <= 0){
                Toast.makeText(activity, "Id Inválido", Toast.LENGTH_SHORT).show()
            }else{
                val data = hashMapOf(
                    "id" to editTextNumber.text.toString().toInt(),
                    "descricao" to editTextDescption.text?.toString(),
                    "titulo" to editTextName.text?.toString()
                )
                val pass = editTextDescption.text.toString().isEmpty()
                        || editTextName.text?.toString()?.isEmpty() ?: true
                if(pass){
                    Toast.makeText(activity, "Há Campos Vazios", Toast.LENGTH_SHORT).show()
                }else{
                    var idDoc = String()
                    db.collection("dados").get().addOnSuccessListener {
                        result ->
                        for (doc in result){
                            if(editTextNumber.text.toString().toInt() == doc["id"].toString().toInt()){
                                idDoc = doc.id
                            }
                        }
                        db.collection("dados").document(idDoc).set(data)
                        Toast.makeText(activity, "Elemento Alterado", Toast.LENGTH_SHORT).show()
                        activity?.onBackPressed()
                    }
                }
            }
        }

        return view
    }

}