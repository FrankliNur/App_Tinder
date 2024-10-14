package com.example.myappl_tind3r.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myappl_tind3r.ImagePagerAdapter
import com.example.myappl_tind3r.MainActivity
import com.example.myappl_tind3r.R

class PersonFragment : Fragment() {

    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var viewPager: ViewPager2
    private var currentPosition: Int = 0  // Posición actual de la imagen en el ViewPager
    private val likedImages = mutableListOf<Int>()  // Lista para almacenar las imágenes que diste "Like"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_person, container, false)

        // Configurar el ViewPager2
        viewPager = view.findViewById(R.id.view_pager)

        // Lista mutable de imágenes
        val images = mutableListOf(
            R.drawable.golden1, // Imagen 1
            R.drawable.puppy, // Imagen 2
            R.drawable.rottweiler,  // Imagen 3
            R.drawable.doberman, // Imagen 4
            R.drawable.orejon, // Imagen 5
            R.drawable.dog // Imagen 6
        )

        // Configurar el adaptador
        imagePagerAdapter = ImagePagerAdapter(images)
        viewPager.adapter = imagePagerAdapter

        // Listener para cuando se cambia de imagen
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPosition = position  // Actualiza la posición actual
            }
        })

        // Botón de Like
        val buttonLike = view.findViewById<Button>(R.id.button_like)
        buttonLike.setOnClickListener {
            handleLike()  // Llamar a la función para manejar el Like
        }

        // Botón de Dislike
        val buttonDislike = view.findViewById<Button>(R.id.button_dislike)
        buttonDislike.setOnClickListener {
            handleDislike()  // Llamar a la función para manejar el Dislike
        }

        // Botón de "Ir a Mis Likes"
        val buttonLikes = view.findViewById<Button>(R.id.button_likes)
        buttonLikes.setOnClickListener {
            (activity as? MainActivity)?.goToLikesFragment(likedImages)  // Pasar la lista de Likes
        }

        return view
    }

    // Función que maneja el evento "Like"
    private fun handleLike() {
        if (currentPosition < imagePagerAdapter.itemCount) {
            likedImages.add(imagePagerAdapter.images[currentPosition])  // Agregar la imagen a la lista de Likes
            imagePagerAdapter.removeImage(currentPosition)  // Eliminar la imagen actual
            updateViewPagerAfterRemoval()  // Actualizar el ViewPager
        }
    }

    // Función que maneja el evento "Dislike"
    private fun handleDislike() {
        if (currentPosition < imagePagerAdapter.itemCount) {
            imagePagerAdapter.removeImage(currentPosition)  // Eliminar la imagen actual
            updateViewPagerAfterRemoval()  // Actualizar el ViewPager
        }
    }

    // Actualiza el ViewPager después de eliminar una imagen
    private fun updateViewPagerAfterRemoval() {
        // Si la posición actual es la última imagen, regresa a la imagen anterior
        if (currentPosition >= imagePagerAdapter.itemCount) {
            currentPosition = imagePagerAdapter.itemCount - 1
        }
        // Asegúrate de que la posición actual sea válida
        if (currentPosition >= 0) {
            viewPager.setCurrentItem(currentPosition, true)  // Cambiar a la imagen actual
        }
    }
}
