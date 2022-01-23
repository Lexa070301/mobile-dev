package com.example.myapplication6.data

class NodeRepository(private val nodeDao: NodeDao) {

    suspend fun insertAsync(node: Node) {
        nodeDao.insertNode(node)
    }

    suspend fun getAllNodes(): List<Node> {
        return nodeDao.getAllNodes()
    }

    suspend fun updateNode(
        id: Int,
        nodes: List<Node>
    ) {
        nodeDao.updateNode(id, nodes)
    }
}
