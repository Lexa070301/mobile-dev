package com.example.myapplication6.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "node")
data class Node(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) @NonNull val id: Int,
    @SerializedName("value")
    val value: Int,
    @SerializedName("nodes")
    val nodes: MutableList<Node>,
) {

    fun canBeChildFor(parent: Node): Boolean =
        (parent.id != id && !hasChild(this, parent) || parent.hasDirectChild(this))

    fun hasParent(allNodes: List<Node>): Boolean =
        allNodes.any { parentNode -> parentNode.hasDirectChild(this) }

    fun hasDirectChild(node: Node): Boolean = nodes.any { it.id == node.id }

    private fun hasChild(
        parentNode: Node,
        childNode: Node
    ): Boolean {
        parentNode.nodes.forEach { node ->
            if (node.id == childNode.id || hasChild(node, childNode))
                return true
        }
        return false
    }
}