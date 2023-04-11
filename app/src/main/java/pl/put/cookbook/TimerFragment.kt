package pl.put.cookbook

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import pl.put.cookbook.recipes.Action


class TimerFragment : Fragment() {
    private var timerEnds: MutableList<Long> = ArrayList()
    private var timerSuggestions: Array<Long> = arrayOf()

    fun setSuggestedTimes(times: Array<Long>) {
        timerSuggestions = times
        applySuggestedTimes()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) return
        timerEnds = unstringifyLongArray(savedInstanceState.getString("timerEnds")).toMutableList()
        timerSuggestions = unstringifyLongArray(savedInstanceState.getString("timerSuggestions"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_timer, container, false)
        runTimer(layout)

        val startButton = layout.findViewById<Button>(R.id.start_button)
        val stopButton = layout.findViewById<Button>(R.id.stop_button)

        startButton.setOnClickListener { onClickStart() }
        stopButton.setOnClickListener { onClickStop() }

        applySuggestedTimes(layout)

        val timerSpinner = layout.findViewById<Spinner>(R.id.timerInitialValues)
        val addTimerButton = layout.findViewById<Button>(R.id.addTimerButton)
        addTimerButton.setOnClickListener {
            val index = timerSpinner.selectedItemPosition
            val time = timerSuggestions[index]
            timerEnds.add((System.currentTimeMillis() / 1000) + time)
        }

        return layout
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putString("timerEnds", stringifyLongArray(timerEnds))
        savedInstanceState.putString("timerSuggestions", stringifyLongArray(timerSuggestions.asIterable()))
    }

    private fun applySuggestedTimes(layout: View? = null) {
        val view = layout ?: view ?: return
        val timeList = ArrayList<String>()
        for(entry in timerSuggestions){
            val time = Action.getHumanReadableTime(entry.toInt()) ?: continue
            timeList.add(time)
        }

        val timerSpinner = view.findViewById<Spinner>(R.id.timerInitialValues)
        val adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_spinner_item,
            timeList.toArray()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timerSpinner.adapter = adapter
    }

    private fun onClickStart() {
        // running = true
    }

    private fun onClickStop() {
        // running = false
    }

    private fun runTimer(view: View) {
        val timeView = view.findViewById<TextView>(R.id.time_view)
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                val sb = StringBuilder()
                for(t in timerEnds) {
                    val seconds = t - (System.currentTimeMillis() / 1000)

                    val minutes = seconds / 60
                    val secs = seconds % 60
                    val time = String.format("%d:%02d", minutes, secs)
                    sb.append(time)
                    sb.append("\n")
                }

                timeView.text = sb.toString()
                handler.postDelayed(this, 100)
            }
        })
    }

    private fun stringifyLongArray(array: Iterable<Long>): String {
        val sb = StringBuilder()
        for(t in array){
            sb.append(';')
            sb.append(t)
        }
        val str = sb.toString()
        return str.substring(if (str.isNotEmpty()) 1 else 0)
    }

    private fun unstringifyLongArray(string: String?): Array<Long> {
        if(string == null || string.isEmpty()) return arrayOf()

        val timerSuggestionsList = ArrayList<Long>()
        for(t in string.split(';')) {
            try {
                timerSuggestionsList.add(t.toLong())
            }catch(_: Exception){

            }
        }
        return timerSuggestionsList.toArray(arrayOf())
    }
}