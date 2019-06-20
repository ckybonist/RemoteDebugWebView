package com.example.myapplication

import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var searchBar: EditText
    private lateinit var browseButton: Button
    private lateinit var clearButton: ImageButton

    private val protocol = "https://"

    private var isFirstClick = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        searchBar = findViewById(R.id.search_bar)
        browseButton = findViewById(R.id.action_search)
        clearButton = findViewById(R.id.action_clear)

        searchBar.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                clearSearchBar()
            }
        }

        searchBar.setOnClickListener {
            Log.d("onSearchBarClick:", "first click")
            if (isFirstClick) {
                clearSearchBar()
                isFirstClick = false
            }
        }

        clearButton.setOnClickListener {
            clearSearchBar()
        }

        browseButton.setOnClickListener {
            val url = searchBar.text.toString()
            val webPageIntent = Intent(this, WebPageActivity::class.java)

            webPageIntent.putExtra("url", url)
            startActivity(webPageIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearSearchBar() {
        searchBar.text.clear()
        setDefaultURL()
    }

    private fun setDefaultURL() {
        searchBar.setText(protocol)
    }
}
