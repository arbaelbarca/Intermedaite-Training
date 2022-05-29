package com.arbaelbarca.trainingclassintermediate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LifeCycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LifeCycleFragment(val getName: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnActionToActivity: Button
    lateinit var btnActionFragment: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_life_cycle, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnActionToActivity = view.findViewById(R.id.btnToActivity)
        btnActionFragment = view.findViewById(R.id.btnToFragment)

        btnActionToActivity.setOnClickListener {
            val intent = Intent(requireContext(), LifeCycleActivity::class.java)
            intent.putExtra("name", "dari fragment")
            startActivity(intent)
        }

        btnActionFragment.setOnClickListener {

            val fragmentLifeCycleDuaFragment = LifeCycleDuaFragment()

            val bundle = Bundle()
            bundle.putString("from", "framgent A")

            fragmentLifeCycleDuaFragment.arguments = bundle

            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayoutFragment, fragmentLifeCycleDuaFragment)
                .addToBackStack(null)
                .commit()

        }

        Toast.makeText(requireContext(), "dapat get $getName", Toast.LENGTH_SHORT).show()
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        Toast.makeText(requireContext(), "Ini On Destroy View", Toast.LENGTH_SHORT).show()
//    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) {

        }
    }
}