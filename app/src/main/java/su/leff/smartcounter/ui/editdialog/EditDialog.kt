package su.leff.smartcounter.ui.editdialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import su.leff.smartcounter.R
import su.leff.smartcounter.ui.calendar.CalendarFood

class EditDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_editfood)
    }


    companion object {
        fun showDialog(context: Context, editDialogChangeListener: EditDialogChangeListener) {
            val editDialog = EditDialog(context)
            editDialog.show()
        }
    }

    interface EditDialogChangeListener {
        fun onSave(calendarFood: CalendarFood)
        fun onDelete(calendarFood: CalendarFood)
        fun onDismiss(calendarFood: CalendarFood)
    }
}