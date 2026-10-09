package com.felipe.treinofacil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipe.treinofacil.databinding.ActivityWorkoutListBinding
import com.felipe.treinofacil.databinding.ItemWorkoutBinding

/** Tela 1: lista de treinos. Tocar em um item abre a tela de detalhe. */
class WorkoutListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWorkoutListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = WorkoutAdapter(WorkoutMockData.workouts) { workout ->
            openDetail(workout)
        }
    }

    /** Intent explicita: leva o id do treino para a segunda tela. */
    private fun openDetail(workout: Workout) {
        val intent = Intent(this, WorkoutDetailActivity::class.java)
        intent.putExtra(WorkoutDetailActivity.EXTRA_WORKOUT_ID, workout.id)
        startActivity(intent)
    }
}

class WorkoutAdapter(
    private val workouts: List<Workout>,
    private val onClick: (Workout) -> Unit,
) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemWorkoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWorkoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        val context = holder.itemView.context

        holder.binding.name.text = workout.name
        holder.binding.muscleGroup.text = workout.muscleGroup
        holder.binding.series.text = context.getString(R.string.series_format, workout.totalSets, workout.repsPerSet)
        holder.binding.root.setOnClickListener { onClick(workout) }
    }

    override fun getItemCount(): Int = workouts.size
}
