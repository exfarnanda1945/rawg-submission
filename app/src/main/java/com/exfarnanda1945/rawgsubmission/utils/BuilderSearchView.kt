package com.exfarnanda1945.rawgsubmission.utils

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.exfarnanda1945.rawgsubmission.R

object BuilderSearchView {
    fun build(
        menuHost: MenuHost,
        lifecycleOwner: LifecycleOwner,
        lifeCycleState: Lifecycle.State,
        onSubmitCallback: OnSubmitCallback
    ) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val search = menu.findItem(R.id.search_menu)
                val searchView = search.actionView as SearchView

                searchView.apply {
                    imeOptions = EditorInfo.IME_ACTION_SEARCH
                    isSubmitButtonEnabled = true
                    queryHint = "Search Here"
                    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        @SuppressLint("SetTextI18n")
                        override fun onQueryTextSubmit(query: String): Boolean {
                            onSubmitCallback.onQuerySubmit(query)
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            return false
                        }
                    })
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }


        }, lifecycleOwner, lifeCycleState)
    }

    interface OnSubmitCallback {
        fun onQuerySubmit(query: String)
    }
}