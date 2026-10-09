package com.felipe.treinofacil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.felipe.treinofacil.databinding.ActivityWorkoutDetailBinding

/**
 * Tela 2: detalhe do treino que chegou pela Intent.
 * Interacao: o botao "Fiz mais 1 serie" atualiza o texto, a barra e o status.
 */
class WorkoutDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_WORKOUT_ID = "extra_workout_id"
        private const val STATE_SETS_DONE = "state_sets_done"
    }

    private lateinit var binding: ActivityWorkoutDetailBinding
    private var setsDone = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWorkoutDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Se o id nao existir, fecha a tela em vez de quebrar o app.
        val workout = WorkoutMockData.findById(intent.getStringExtra(EXTRA_WORKOUT_ID))
        if (workout == null) {
            finish()
            return
        }

        // Guarda o progresso quando a tela gira.
        setsDone = savedInstanceState?.getInt(STATE_SETS_DONE, 0) ?: 0

        showWorkout(workout)
        updateProgress(workout)

        binding.addSetButton.setOnClickListener {
            setsDone = (setsDone + 1).coerceAtMost(workout.totalSets)
            updateProgress(workout)
        }
        binding.resetButton.setOnClickListener {
            setsDone = 0
            updateProgress(workout)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SETS_DONE, setsDone)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun showWorkout(workout: Workout) {
        binding.name.text = workout.name
        binding.muscleGroup.text = workout.muscleGroup
        binding.series.text = getString(R.string.series_format, workout.totalSets, workout.repsPerSet)

        // Campos opcionais: aparece o texto se existir, senao um aviso (ou o campo some).
        binding.description.text = workout.description ?: getString(R.string.no_description)

        binding.rest.isVisible = workout.restSeconds != null
        workout.restSeconds?.let { binding.rest.text = getString(R.string.rest_format, it) }

        binding.tip.isVisible = workout.tip != null
        workout.tip?.let { binding.tip.text = getString(R.string.tip_format, it) }
    }

    private fun updateProgress(workout: Workout) {
        val percent = setsDone * 100 / workout.totalSets

        binding.progressText.text = getString(R.string.progress_format, setsDone, workout.totalSets, percent)
        binding.progressBar.progress = percent
        binding.status.text = getString(
            when {
                setsDone >= workout.totalSets -> R.string.status_done
                setsDone > 0 -> R.string.status_in_progress
                else -> R.string.status_not_started
            }
        )

        binding.addSetButton.isEnabled = setsDone < workout.totalSets
        binding.resetButton.isEnabled = setsDone > 0
    }
}
