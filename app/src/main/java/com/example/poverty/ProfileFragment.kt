package com.example.poverty


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v:View=inflater.inflate(R.layout.fragment_profile,container,false)
        //var name: String? = getArguments()?.getString("Name")
        //var name= this.arguments?.getString("Namesss")
        if(arguments!=null){
            var bundle:Bundle? =this.arguments
            var name= bundle?.getString("Namesss")
            Log.d("TAG", name+"is this empty?")
            var textViewProfileTest:TextView = v.findViewById(R.id.textViewProfileTest)
            textViewProfileTest.setText(name)
        }else{
            Log.d("TAG", "This is is sis is is this empty?")
        }



        return v
    }


}
