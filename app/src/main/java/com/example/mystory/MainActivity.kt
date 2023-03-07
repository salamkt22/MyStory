package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var textViewEmail: TextView
    lateinit var drawerLayout : DrawerLayout
    lateinit var toolbarView : Toolbar
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val email = intent.getStringExtra("email")

        connectViews()
        textViewEmail.text = email
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpDrawer()
        updateEmailInHeader(email!!)
        drawerClicks()

    }
    private fun drawerClicks() {
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.logout -> {
                    finish()
                    val i = Intent(this,LoginActivity::class.java)
                    startActivity(i)
                    true
                }else -> true
            }
        }

    }
    private fun updateEmailInHeader(email : String) {
        val headerView = navigationView.getHeaderView(0)
        val textViewEmail = headerView.findViewById<TextView>(R.id.tvEmail)
        textViewEmail.text = email
    }
    private fun connectViews() {
        textViewEmail=findViewById(R.id.tvEmail)
        drawerLayout =findViewById(R.id.drawer)
        toolbarView =findViewById(R.id.toolbar)
        navigationView =findViewById(R.id.navView)
    }
    private fun setUpDrawer(){
        val toggle = ActionBarDrawerToggle(this,drawerLayout
            ,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> true
        }
    }


}