package dev.dslam.twitchtop

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialogFragment

class FeedbackDialog(private val actionListener: ActionListener) : AppCompatDialogFragment() {

    private lateinit var etComment: EditText
    private lateinit var btOk: Button
    private lateinit var btClose: ImageView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.feedback_dialog, null)

        etComment = view?.findViewById(R.id.etComment)!!
        btOk = view.findViewById(R.id.btOk)
        btClose = view.findViewById(R.id.btClose)

        builder.setView(view)

        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btOk.setOnClickListener {
            actionListener.onSendPressed(etComment.text.toString())
        }

        btClose.setOnClickListener {
            actionListener.onClosePressed()
        }
    }

    interface ActionListener {
        fun onClosePressed()
        fun onSendPressed(text: String)
    }
}