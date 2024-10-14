package com.example.myappl_tind3r

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImagePagerAdapter(val images: MutableList<Int>) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view) // Asegúrate de que tienes este ID en tu layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false) // Asegúrate de que este layout exista
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])  // Establecer la imagen en el ImageView
    }

    override fun getItemCount(): Int {
        return images.size  // Retornar el tamaño de la lista de imágenes
    }

    // Función para eliminar una imagen
    fun removeImage(position: Int) {
        if (position in 0 until images.size) { // Verifica que la posición sea válida
            images.removeAt(position)
            notifyItemRemoved(position)  // Actualiza el adaptador
            notifyItemRangeChanged(position, images.size) // Notifica que el rango ha cambiado
        }
    }
}
