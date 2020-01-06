package com.example.poverty


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*var v:View = inflater.inflate(R.layout.fragment_profile,container,false)
        var bundle: Bundle? =arguments
        var name: String? = bundle?.getString("Name")
        textViewProfileTest.setText(name)*/
        /*var name: String? = getArguments()?.getString("Name")
        textViewProfileTest.setText(name)*/

        return inflater.inflate(R.layout.fragment_profile,container,false)
    }


}
