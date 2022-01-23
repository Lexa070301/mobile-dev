package com.example.myapplication6.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication6.data.Node
import com.example.myapplication6.data.NodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NodeViewModel(private val nodeRepository: NodeRepository) : ViewModel() {

    fun insertNode(node: Node) = viewModelScope.launch(Dispatchers.IO) {
        nodeRepository.insertAsync(node)
    }

    fun getAllNodes(): List<Node> {
        return runBlocking {
            nodeRepository.getAllNodes()
        }
    }

    fun updateNode(
        id: Int,
        nodes: List<Node>
    ) = viewModelScope.launch {
        nodeRepository.updateNode(id, nodes)
    }
}