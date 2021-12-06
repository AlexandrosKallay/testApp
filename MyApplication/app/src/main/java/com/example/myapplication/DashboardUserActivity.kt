package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import com.example.myapplication.Fragments.*
import com.example.myapplication.databinding.ActivityDashboardUserBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class DashboardUserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    //view binding
    private lateinit var binding: ActivityDashboardUserBinding

    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth


    lateinit var homeFragment: HomeFragment
    lateinit var categoriesFragment: CategoriesFragment
    lateinit var favoritesFragment: FavoritesFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var settingsFragment: SettingsFragment
    lateinit var aboutFragment: AboutFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.userToolbar.toolBar);

        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.userToolbar.toolBar,
            (R.string.open),
            (R.string.close)
        ){

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


    }

    private fun showPopup() {
        val alert: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@DashboardUserActivity)
        alert.setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    logout() // Last step. Logout function
                }).setNegativeButton("Cancel", null)

        val alert1: android.app.AlertDialog? = alert.create()
        alert1?.show()

    }

    private fun logout() {
        firebaseAuth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    private fun checkUser() {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            //not logged in, user can stay in user dashboard without login to
            //binding.subTitleTv.text = "Not Logged In"
        }
        else{
            //logged in, get and show user info
            val email = firebaseUser.email
            //set to textView of toolbar
            //binding.subTitleTv.text = email
        }

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.category -> {
                categoriesFragment = CategoriesFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, categoriesFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.favorite -> {
                favoritesFragment = FavoritesFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, favoritesFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.profile -> {
                profileFragment = ProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, profileFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.settings -> {
                settingsFragment = SettingsFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, settingsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.about -> {
                aboutFragment = AboutFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, aboutFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.logoutBtn -> {
                //handle click, logout with alert dialog
                    showPopup();

            }

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

    }



}