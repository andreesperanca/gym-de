package com.andreesperanca.gymde.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.HealthArticlesAdapter.*
import com.andreesperanca.gymde.data.healthArticles
import com.andreesperanca.gymde.databinding.HealthArticlesItemBinding
import com.andreesperanca.gymde.models.HealthArticles
import com.google.android.material.snackbar.Snackbar

class HealthArticlesAdapter() : RecyclerView.Adapter<HealthArticlesViewHolder>() {

    val healthArticlesList: List<HealthArticles> = healthArticles

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthArticlesViewHolder {
        val binding = HealthArticlesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthArticlesViewHolder, position: Int) {
        holder.bind(healthArticlesList[position])
    }

    override fun getItemCount(): Int = healthArticlesList.size

    inner class HealthArticlesViewHolder(private val binding: HealthArticlesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(healthArticles: HealthArticles) {

            with(binding) {
                ivHealthArticles.setImageResource(healthArticles.bg)
                tvTitleArticle.text = healthArticles.title
                btnReadArticle.setOnClickListener {
                    Snackbar.make(binding.root, root.context.getText(R.string.no_implementation), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}