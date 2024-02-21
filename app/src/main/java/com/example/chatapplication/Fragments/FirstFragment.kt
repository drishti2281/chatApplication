package com.example.chatapplication.Fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapplication.MainActivity
import com.example.chatapplication.MessageDataClass
import com.example.chatapplication.R
import com.example.chatapplication.RecyclerClass
import com.example.chatapplication.databinding.FragmentFirstBinding
import com.example.chatapplication.databinding.SelectionDialogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFirstBinding
    lateinit var mainActivity: MainActivity
    lateinit var recyclerClass: RecyclerClass
    var messageDataClassArray = arrayListOf<MessageDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater)
      //  mainActivity.supportActionBar?.setTitle("Testing")
        binding.btnPop.setOnClickListener {
        findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerClass = RecyclerClass(messageDataClassArray)
        binding.recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerView.adapter = recyclerClass

        binding.ibSend.setOnClickListener {
            if(binding.etSendText.text.toString().trim().isNullOrEmpty()){
                binding.etSendText.error = mainActivity.resources.getString(R.string.send_message)
                binding.etSendText.requestFocus()
            }else{
                var dialogBinding = SelectionDialogBinding.inflate(layoutInflater)
                var customDialog = Dialog(mainActivity)
                customDialog.setContentView(dialogBinding.root)
                customDialog.show()

                dialogBinding.btnSend.setOnClickListener {
                    var id = if(dialogBinding.rbFirst.isChecked){
                        0
                    }else 1
                    messageDataClassArray.add(MessageDataClass(id = id, message = binding.etSendText.text.toString()))
                    customDialog.dismiss()
                    recyclerClass.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}