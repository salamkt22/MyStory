package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var editTextUsername: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var checkBoxView: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        connectViews()
        login()
//        checkFields()

    }
    private fun connectViews() {
        editTextUsername = findViewById(R.id.etUsername)
        editTextPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.btnLogin)
        checkBoxView = findViewById(R.id.checkbox)
    }
    private fun login() {
        val arr : ArrayList<User> = ArrayList()
        arr.add(User("t@test.com","1234"))
        arr.add(User("be@test.com","12345"))
        arr.add(User("test@test.com","123456"))

        buttonLogin.setOnClickListener {

            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            val user = User(username,password)

            for (userArray in arr) {
                if (userArray.email == user.email
                    && userArray.password == user.password) {
                    finish()
//                    Toast.makeText(this, "Welcome ${user.email}", Toast.LENGTH_LONG).show()
                    val i = Intent(this,MainActivity::class.java)
                    i.putExtra("email",userArray.email)
                    startActivity(i)
                    break
                } else {
                    Toast.makeText(this, "Check your data", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }
    private fun checkFields(){
        buttonLogin.setOnClickListener {
            if (editTextUsername.text.isEmpty() ){
                editTextUsername.error = "Enter your email"
            }else if (editTextPassword.text.isEmpty()){
                editTextPassword.error= "Enter your password"
            }
        }
    }

}