package com.example.myapplication6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication6.databinding.ItemNodeBinding
import com.example.myapplication6.views.NodeItem

class NodeListAdapter(
    private val nodeItems: List<NodeItem>,
    private val nodeClickListener: NodeClickListener
) : RecyclerView.Adapter<NodeListAdapter.NodeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNodeBinding.inflate(
            inflater,
            parent,
            false
        )
        return NodeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NodeViewHolder,
        position: Int
    ) {
        val currentNode = nodeItems[position].node
        val nodeColor = nodeItems[position].color
        holder.nodeTextView.text = """id: ${currentNode.id} value: ${currentNode.value}"""
        holder.nodeTextView.setBackgroundColor(nodeColor.toArgb())

        holder.nodeTextView.setOnClickListener {
            nodeClickListener.showRelationshipActivity(currentNode.id)
        }
    }

    override fun getItemCount(): Int {
        return nodeItems.size
    }

    inner class NodeViewHolder(binding: ItemNodeBinding) : RecyclerView.ViewHolder(binding.root) {
        var nodeTextView: TextView = binding.nodeTextView
    }
}