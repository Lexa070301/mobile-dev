package com.example.myapplication6.views

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication6.adapters.NodeClickListener
import com.example.myapplication6.adapters.NodeListAdapter
import com.example.myapplication6.data.Node
import com.example.myapplication6.databinding.FragmentMainBinding
import com.example.myapplication6.viewmodels.NodeViewModel
import com.example.myapplication6.viewmodels.NodeViewModelFactory
import com.example.myapplication6.viewmodels.RepositoryInitializer
import com.example.myapplication6.views.AddNodeActivity.Companion.REQUEST_CREATE_NODE
import com.example.myapplication6.views.AddNodeActivity.Companion.VALUE
import com.example.myapplication6.views.NodeRelActivity.Companion.NODE_ID
import com.example.myapplication6.views.NodeRelActivity.Companion.REQUEST_UPDATE_RELATIONSHIPS

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerView: RecyclerView
    private var nodeItems = mutableListOf<NodeItem>()
    private var nodeViewModel: NodeViewModel? = null

    private val adapter = NodeListAdapter(
        nodeItems,
        object : NodeClickListener {
            override fun showRelationshipActivity(id: Int) {
                Intent(
                    this@MainFragment.context,
                    NodeRelActivity::class.java
                ).apply {
                    putExtra(NODE_ID, id)
                    startActivityForResult(
                        this,
                        REQUEST_UPDATE_RELATIONSHIPS
                    )
                }
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        val viewModelFactory =
            NodeViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        nodeViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(NodeViewModel::class.java)
        updateNodes()

        binding.createNodeButton.setOnClickListener {
            Intent(
                this@MainFragment.context,
                AddNodeActivity::class.java
            ).apply {
                startActivityForResult(
                    this,
                    REQUEST_CREATE_NODE
                )
            }
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == REQUEST_CREATE_NODE) {
            if (resultCode == RESULT_OK) {
                data?.let {
                    val node = Node(
                        nodeItems.size + 1,
                        it.getStringExtra(VALUE).toString().toInt(),
                        mutableListOf()
                    )
                    nodeViewModel?.insertNode(node)
                    nodeItems.add(
                        NodeItem(
                            node,
                            Color.valueOf(Color.WHITE)
                        )
                    )
                    adapter.notifyDataSetChanged()
                }
            }
        }

        if (requestCode == REQUEST_UPDATE_RELATIONSHIPS) {
            updateNodes()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateNodes() {
        nodeViewModel?.getAllNodes()?.let { items ->
            nodeItems.clear()
            items.forEach { node ->
                nodeItems.add(
                    NodeItem(
                        node,
                        Color.valueOf(Color.WHITE)
                    )
                )
            }
            val allNodes = nodeItems.map { it.node }
            nodeItems.indices.forEach { index ->
                val node = nodeItems[index].node
                when {
                    node.hasParent(allNodes) && node.nodes.size > 0 ->
                        nodeItems[index] = NodeItem(
                            node,
                            Color.valueOf(Color.rgb(255, 82, 82))
                        )
                    node.hasParent(allNodes) ->
                        nodeItems[index] = NodeItem(
                            node,
                            Color.valueOf(Color.rgb(33, 150, 243))
                        )
                    node.nodes.size > 0 ->
                        nodeItems[index] = NodeItem(
                            node,
                            Color.valueOf(Color.rgb(255, 235, 59))
                        )
                }
            }
            adapter.notifyDataSetChanged()
        }
    }
}
