package com.example.myapplication6.adapters

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication6.databinding.ItemRelationshipBinding

import com.example.myapplication6.data.Node
import com.example.myapplication6.views.NodeRelItem
import com.example.myapplication6.views.NodeRelType

class NodeRelListAdapter(
    private val nodes: List<NodeRelItem>,
    private val currentNode: Node,
    private val nodeRelationshipType: NodeRelType,
    private var nodeRelationshipClickListener: NodeRelClickListener
) : RecyclerView.Adapter<NodeRelListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRelationshipBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = nodes[position]
        val text = holder.relationship
        if (nodes[position].isRelationship) {
            text.setBackgroundColor(Color.rgb(76, 175, 80))
        } else {
            text.setBackgroundColor(Color.WHITE)
        }
        text.text =
            """id:${currentNode.id} value:${currentNode.value} - id:${item.node.id} value:${item.node.value}"""

        text.setOnClickListener {
            switchRelationship(item)
        }
    }

    private fun switchRelationship(item: NodeRelItem) {
        val newRelationship = !item.isRelationship
        when {
            newRelationship -> when (nodeRelationshipType) {
                NodeRelType.PARENT -> {
                    currentNode.nodes.add(item.node)
                    nodeRelationshipClickListener.onRelationshipClick(currentNode)
                }
                else -> {
                    item.node.nodes.add(currentNode)
                    nodeRelationshipClickListener.onRelationshipClick(item.node)
                }
            }
            else -> when (nodeRelationshipType) {
                NodeRelType.PARENT -> {
                    currentNode.nodes.remove(item.node)
                    nodeRelationshipClickListener.onRelationshipClick(currentNode)
                }
                else -> {
                    item.node.nodes.remove(currentNode)
                    nodeRelationshipClickListener.onRelationshipClick(item.node)
                }
            }
        }
    }

    override fun getItemCount(): Int = nodes.size

    inner class ViewHolder(binding: ItemRelationshipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val relationship: TextView = binding.itemRelationship
    }

}
