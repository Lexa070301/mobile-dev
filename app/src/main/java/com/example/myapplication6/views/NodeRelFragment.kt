package com.example.myapplication6.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication6.data.Node
import com.example.myapplication6.adapters.NodeRelClickListener
import com.example.myapplication6.adapters.NodeRelListAdapter
import com.example.myapplication6.databinding.FragmentNodeRelationshipsBinding
import com.example.myapplication6.viewmodels.NodeViewModel
import com.example.myapplication6.viewmodels.NodeViewModelFactory
import com.example.myapplication6.viewmodels.RepositoryInitializer

class NodeRelFragment : Fragment() {

    private lateinit var binding: FragmentNodeRelationshipsBinding
    private var nodeId = 1
    private var nodeRelationshipType: NodeRelType = NodeRelType.PARENT
    private lateinit var recyclerView: RecyclerView
    private lateinit var nodeViewModel: NodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nodeId = it.getInt(ARG_NODE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNodeRelationshipsBinding.inflate(inflater)

        recyclerView = binding.recyclerView

        val viewModelFactory = NodeViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        nodeViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )
            .get(NodeViewModel::class.java)

        initNodes()

        binding.parentButton.setOnClickListener{
            binding.childButton.isChecked = false
            binding.parentButton.isChecked = true
            nodeRelationshipType = NodeRelType.PARENT
            initNodes()
        }

        binding.childButton.setOnClickListener {
            binding.childButton.isChecked = true
            binding.parentButton.isChecked = false
            nodeRelationshipType = NodeRelType.CHILD
            initNodes()
        }

        return binding.root
    }

    private fun initNodes() {
        val listNodes = nodeViewModel.getAllNodes()
        val nodeList: MutableList<NodeRelItem> = mutableListOf()
        val currentNode = listNodes.find { it.id == nodeId }!!

        listNodes.forEach { node ->
            when (nodeRelationshipType) {
                NodeRelType.PARENT -> {
                    if (node.canBeChildFor(currentNode)) {
                        nodeList.add(NodeRelItem(node, currentNode.hasDirectChild(node)))
                    }

                }
                NodeRelType.CHILD -> {
                    if (currentNode.canBeChildFor(node)) {
                        nodeList.add(NodeRelItem(node, node.hasDirectChild(currentNode)))
                    }
                }
            }
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = NodeRelListAdapter(
                nodeList,
                currentNode,
                nodeRelationshipType,
                object : NodeRelClickListener {
                    override fun onRelationshipClick(parent: Node) {
                        nodeViewModel.updateNode(
                            parent.id,
                            parent.nodes
                        ).invokeOnCompletion {
                            initNodes()
                        }
                    }
                }
            )
        }
    }

    companion object {

        const val ARG_NODE_ID = ""

        fun newInstance(nodeId: Int) =
            NodeRelFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NODE_ID, nodeId)
                }
            }
    }
}