package com.example.myappl_tind3r

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myappl_tind3r.fragments.PersonFragment
import com.example.myappl_tind3r.fragments.LikedUsersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustar la vista para system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar el PersonFragment cuando se abre la app
        if (savedInstanceState == null) {
            loadFragment(PersonFragment())
        }
    }

    // Función para cargar un fragmento en el contenedor
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // Función para ir a LikesFragment (opcional si necesitas cambiar de fragment)
    fun goToLikesFragment(likedImages: List<Int>) {
        val likedUsersFragment = LikedUsersFragment()
        likedUsersFragment.setLikedImages(likedImages)  // Pasar la lista de imágenes liked
        loadFragment(likedUsersFragment)  // Cargar el nuevo fragmento
    }


}
