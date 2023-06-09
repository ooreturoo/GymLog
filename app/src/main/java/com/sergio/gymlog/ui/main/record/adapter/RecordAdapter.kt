package com.sergio.gymlog.ui.main.record.adapter

import android.icu.text.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gymlog.R
import com.sergio.gymlog.data.model.training.TrainingLog
import com.sergio.gymlog.databinding.TrainingRecordItemBinding
import java.util.Locale

class RecordAdapter(
    var trainingLogs : List<TrainingLog>,
    private val onClickTrainingRecord : (position : Int) -> Unit
) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.training_record_item, parent, false)
        return RecordViewHolder(view)
    }

    override fun getItemCount() = trainingLogs.size

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(trainingLogs[position])
    }

    inner class RecordViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = TrainingRecordItemBinding.bind(view)

        fun bind(trainingLog: TrainingLog) {

            val format = DateFormat.getDateTimeInstance(
                DateFormat.LONG,
                DateFormat.NONE,
                Locale.getDefault()
            )

            binding.tvTrainingRecordItemDate.text = format.format(trainingLog.date!!.toDate())
            binding.tvTrainingRecordItemTrainingName.text = trainingLog.training!!.training!!.name

            binding.root.setOnClickListener{
                onClickTrainingRecord(layoutPosition)
            }

        }

    }

}