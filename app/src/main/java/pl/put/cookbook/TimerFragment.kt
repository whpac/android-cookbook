package pl.put.cookbook

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import pl.put.cookbook.recipes.Action


class TimerFragment : Fragment() {
    private var timerSuggestions: Array<Long> = arrayOf()
    private lateinit var timerWrapper: LinearLayout
    private var timerTextViews: ArrayList<TextView> = ArrayList()

    fun setSuggestedTimes(times: Array<Long>) {
        timerSuggestions = times
        applySuggestedTimes()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) return
        timerSuggestions = unstringifyLongArray(savedInstanceState.getString("timerSuggestions"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_timer, container, false)
        runTimer(layout)

        applySuggestedTimes(layout)

        timerWrapper = layout.findViewById(R.id.timerWrapper)

        for(t in App.timers) {
            generateTimerRow(t, timerWrapper)
        }

        val timerSpinner = layout.findViewById<Spinner>(R.id.timerInitialValues)
        val addTimerButton = layout.findViewById<Button>(R.id.addTimerButton)
        addTimerButton.setOnClickListener {
            val index = timerSpinner.selectedItemPosition
            val time = timerSuggestions[index]
            val timer = Timer(time)
            App.timers.add(timer)

            generateTimerRow(timer, timerWrapper)
        }

        return layout
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
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

    private fun runTimer(view: View) {
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                for((t, tv) in App.timers zip timerTextViews) {
                    val seconds = t.getRemainingTime()

                    val minutes = seconds / 60
                    val secs = seconds % 60
                    val time = String.format("%d:%02d", minutes, secs)
                    tv.text = time
                }

                handler.postDelayed(this, 100)
            }
        })
    }

    private fun generateTimerRow(t: Timer, timerWrapper: LinearLayout) {
        val timerText = TextView(this.activity)
        timerText.text = ""
        timerText.setTextAppearance(android.R.style.TextAppearance_Large)
        timerTextViews.add(timerText)

        val timerButton = Button(this.activity)
        timerButton.text = getString(if (t.isRunning) R.string.stop else R.string.start)
        timerButton.setOnClickListener {
            if(t.isRunning) {
                t.stop()
                timerButton.text = getString(R.string.start)
            } else {
                t.start()
                timerButton.text = getString(R.string.stop)
            }
        }


        val timerLayout = LinearLayout(this.activity)
        timerLayout.addView(timerText)
        timerLayout.addView(timerButton)
        timerWrapper.addView(timerLayout)
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