package `in`.bitcode.fragment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    lateinit var edtCount: EditText
    lateinit var btnResetCounter: Button
    lateinit var btnAddCounter: Button
    lateinit var btnRemoveCounter: Button

    var fragmentCounters = ArrayList<CounterFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btnResetCounter.setOnClickListener(View.OnClickListener {
            for(counterFragment in fragmentCounters) {
                counterFragment.count = Integer.parseInt( edtCount.text.toString() )
            }
        })
    }

    private fun init() {
        edtCount = findViewById(R.id.edtCount)
        btnResetCounter = findViewById(R.id.btnResetCounter)
        btnAddCounter = findViewById(R.id.btnAddCounter)
        btnRemoveCounter = findViewById(R.id.btnRemoveCounter)

        btnAddCounter.setOnClickListener(BtnAddCounterClickListener())
        btnRemoveCounter.setOnClickListener(BtnRemoveCounterClickListener())
    }

    inner private class BtnAddCounterClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            var newCounterFragment = CounterFragment()

            var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.mainContainer, newCounterFragment, "mytag")
            fragmentTransaction.addToBackStack("transaction-name")
            fragmentTransaction.commit()

            fragmentCounters.add(newCounterFragment)
        }
    }

    inner private class BtnRemoveCounterClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            if (fragmentCounters.size <= 0)
                return

            supportFragmentManager.beginTransaction()
                .remove(fragmentCounters.get(fragmentCounters.size - 1))
                //.addToBackStack("transaction-name")
                .commit()

            fragmentCounters.removeAt(fragmentCounters.size - 1)
        }
    }
}