package su.leff.smartcounter.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.events.calendar.views.EventsCalendar
import kotlinx.android.synthetic.main.fragment_calendar.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.ui.homepage.HomePageFragment
import su.leff.smartcounter.util.BaseFragment
import java.util.*
import java.text.SimpleDateFormat


class CalendarFragment : BaseFragment(), EventsCalendar.Callback {

    var sdf = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.applicationContext?.let {
            category_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvTitle.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvDate.setTextColor(ResourceManager.getUsualTextColorColor(it))

            val today = Calendar.getInstance()
            val lastYearToday = Calendar.getInstance()
            lastYearToday.set(2018, 3, 23)
            val futureYearToday = Calendar.getInstance()
            futureYearToday.set(2020, 3, 23)

            val typeface = ResourcesCompat.getFont(it, R.font.latoregular)

            typeface?.let { tf ->
                eventsCalendar.setSelectionMode(eventsCalendar.SINGLE_SELECTION) //set mode of Calendar
                    .setToday(today) //set today's date [today: Calendar]
                    .setMonthRange(
                        lastYearToday,
                        futureYearToday
                    ) //set starting month [start: Calendar] and ending month [end: Calendar]
                    .setWeekStartDay(
                        Calendar.MONDAY,
                        false
                    ) //set start day of the week as you wish [startday: Int, doReset: Boolean]
                    .setCurrentSelectedDate(today) //set current date and scrolls the calendar to the corresponding month of the selected date [today: Calendar]
                    .setDatesTypeface(tf) //set font for dates
                    .setDateTextFontSize(16f) //set font size for dates
                    .setMonthTitleTypeface(tf) //set font for title of the calendar
                    .setMonthTitleFontSize(16f) //set font size for title of the calendar
                    .setWeekHeaderTypeface(tf) //set font for week names
                    .setWeekHeaderFontSize(16f) //set font size for week names
                    .setCallback(this) //set the callback for EventsCalendar
                    .setSelectionColor(ResourceManager.getOrangeAccentColor(it))
//            .addEvent(c) //set events on the EventsCalendar [c: Calendar]
//            .disableDate(dc) //disable a specific day on the EventsCalendar [c: Calendar]
                eventsCalendar
                    .setBackgroundColor(ResourceManager.getBackgroundColor(it))
                eventsCalendar.build()
//                    .build()
            }
        }

        setTitleText("calendar")

        imgvBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        recyclerFood.layoutManager = LinearLayoutManager(activity)

        val data = mealViewModel.getMealOnDay(System.currentTimeMillis()) as ArrayList<Meal>

        val adapter = CalendarAdapter(context, data)

        recyclerFood.adapter = adapter
    }

    private fun setDateText(value: String) {
        txvDate.text = value
    }

    private fun setTitleText(value: String) {
        txvTitle.text = value
    }

    override fun onDayLongPressed(selectedDate: Calendar?) {
    }

    override fun onDaySelected(selectedDate: Calendar?) {
        selectedDate?.let { date ->
            setDateText(sdf.format(date.time))
            (recyclerFood.adapter as CalendarAdapter).setList(mealViewModel.getMealOnDay(selectedDate.timeInMillis) as ArrayList<Meal>)
        }
    }

    override fun onMonthChanged(monthStartDate: Calendar?) {
    }

}