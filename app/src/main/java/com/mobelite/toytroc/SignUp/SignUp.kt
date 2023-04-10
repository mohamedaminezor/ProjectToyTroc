package com.mobelite.toytroc.SignUp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.mobelite.toytroc.ExpandableRecyclerView.ExpandableRecycler
import com.mobelite.toytroc.R
import com.mobelite.toytroc.SignIn

class SignUp : AppCompatActivity() ,SignUpContract.View{
    private lateinit var presenter: SignUpContract.Presenter
    private lateinit var textView: TextView
            @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val location = arrayOf(getString(R.string.Ariana), getString(R.string.Béja), getString(R.string.Ben_Arous), getString(
            R.string.Bizerte
        ), getString(R.string.Gabès), getString(R.string.Gafsa), getString(R.string.Jendouba), getString(
            R.string.Kairouan
        ), getString(R.string.Kasserine), getString(R.string.Kebili), getString(R.string.Kef), getString(
            R.string.Mahdia
        ), getString(R.string.Manouba), getString(R.string.Medenine), getString(R.string.Monastir), getString(
            R.string.Nabeul
        ), getString(R.string.Sfax), getString(R.string.Sidi_Bouzid), getString(R.string.Siliana), getString(
            R.string.Sousse
        ), getString(R.string.Tataouine), getString(R.string.Tozeur), getString(R.string.Tunis), getString(
            R.string.Zaghouan
        ))
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, location)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { Toast.makeText(applicationContext, "selected locations is " + location[position], Toast.LENGTH_SHORT).show() }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }










        val namee =findViewById<EditText>(R.id.edt11)
        val lastname =findViewById<EditText>(R.id.edt22)
        val email =findViewById<EditText>(R.id.edt33)
        val pass =findViewById<EditText>(R.id.edt44)
        val nmber =findViewById<EditText>(R.id.edt55)




        textView = findViewById<TextView>(R.id.textView)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn1 = findViewById<Button>(R.id.btn1)

        presenter = SignUpPresenter(this,this)


        btn1.setOnClickListener {
            val email1=email.text.toString()
            val namee1=namee.text.toString()
            val lastname1=lastname.text.toString()
            val pass1=pass.text.toString()
            val nmber1=nmber.text.toString()
            val user = User(namee1,lastname1,email1,pass1,nmber1)

            var result =presenter.inscription(user)
           if (result) {
               val intent =Intent(this, ExpandableRecycler::class.java)
               intent.putExtra("email",email1 )
               intent.putExtra("name",namee1)
               intent.putExtra("lastname",lastname1)
               intent.putExtra("number",nmber1)
               startActivity(intent)

           }

        }

        btn2.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)}
    }







    override fun Showmessage(msg:String){
        textView.setText(msg)
    }
}
