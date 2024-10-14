package com.example.myappl_tind3r.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.myappl_tind3r.R

class LikedUsersFragment : Fragment() {

    private var likedImages: List<Int> = emptyList()  // Lista para almacenar las imágenes de Likes

    fun setLikedImages(images: List<Int>) {
        likedImages = images  // Almacenar la lista de imágenes recibidas
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.liked_users_layout, container, false)
        val layout = view.findViewById<LinearLayout>(R.id.liked_users_layout)

        layout.removeAllViews()  // Limpia cualquier vista anterior

        // Iterar sobre cada imagen en likedImages y agregarla a la LinearLayout
        for (imageRes in likedImages) {
            val imageView = ImageView(context)
            imageView.setImageResource(imageRes)
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                200 // Ajusta la altura según sea necesario
            )
            layout.addView(imageView)  // Agregar la imagen a la LinearLayout
        }

        return view
    }
}
