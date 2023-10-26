package wrt.app.fllmasterpiecepoints

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.punktezaehler_auswertung.R
import com.example.punktezaehler_auswertung.databinding.ItemSavedrunBinding


class SavedRunsAdapter(
    var savedRuns: List<SavedRun>
) : RecyclerView.Adapter<SavedRunsAdapter.SavedRunsViewHolder>() {

    inner class SavedRunsViewHolder(val binding: ItemSavedrunBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRunsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSavedrunBinding.inflate(layoutInflater, parent, false)
        return SavedRunsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedRunsViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.binding.apply {
            var temppoints = savedRuns[position].points.toString() + " pts"
            tvPoints.text = temppoints
            tvTimecode.text = savedRuns[position].timestamp
        }
        holder.binding.btnSolvedMissions.setOnClickListener { v ->
            Intent(v.context, MainActivity3::class.java).also {
                it.putExtra("EXTRA_POSITION", position)
                startActivity(v.context, it, null)
            }
        }
        holder.binding.btnDeleteSavedRun.setOnClickListener { v ->
            val clearWarningDialog = AlertDialog.Builder(v.context, R.style.AlertDialogItemStyle)
                .setTitle(R.string.titleWarningSingleItem)
                .setMessage(R.string.messageWarningSingleItem)
                .setIcon(R.drawable.ic_warning_delete)
                .setPositiveButton(R.string.positiveWarning) { _, _ ->
                    (v.context as MainActivity2).deleteItem(getItemViewType(position))
                }
                .setNegativeButton(R.string.negativeWarning) { _, _ ->}.create()
            clearWarningDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return savedRuns.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}