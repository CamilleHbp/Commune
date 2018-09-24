package me.communes.commune

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // We use the searchable activity to retrieve information about the search query
        menuInflater.inflate(R.menu.search_menu, menu)
        var searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchItem = menu?.findItem(R.id.item_searchView)
        var searchView= searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(ComponentName(this, ContactListActivity::class.java)))
        return super.onCreateOptionsMenu(menu)
    }
}
