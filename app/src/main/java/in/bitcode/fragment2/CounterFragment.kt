package `in`.bitcode.fragment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {

    lateinit var txtCount : TextView
    lateinit var btnPlus : Button
    lateinit var btnMinus : Button

    var count = 0
    set(value)  {
        field = value
        txtCount.text = field.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(
            R.layout.counter_fragment_layout,
            null
        )

        txtCount = view.findViewById(R.id.txtCount)
        btnPlus = view.findViewById(R.id.btnPlus)
        btnMinus = view.findViewById(R.id.btnMinus)
        txtCount.text = count.toString()

        btnPlus.setOnClickListener(BtnPlusClickListener())
        btnMinus.setOnClickListener(BtnMinusClickListener())

        return view
    }

    inner private class BtnPlusClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            count++;
            txtCount.text = count.toString()
        }
    }

    inner private class BtnMinusClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            count--;
            txtCount.text = count.toString()
        }
    }
}