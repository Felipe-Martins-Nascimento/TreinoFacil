package com.felipe.treinofacil

/**
 * Um exercicio do treino. Imutavel (so `val`).
 * `description`, `restSeconds` e `tip` sao opcionais (nullable): nem todo exercicio tem essas informacoes.
 */
data class Workout(
    val id: String,
    val name: String,
    val muscleGroup: String,
    val totalSets: Int,
    val repsPerSet: Int,
    val description: String? = null,
    val restSeconds: Int? = null,
    val tip: String? = null,
)

/** Dados simulados (mocks): nesta etapa nao existe API nem banco de dados. */
object WorkoutMockData {

    val workouts: List<Workout> = listOf(
        Workout(
            id = "w1",
            name = "Supino reto",
            muscleGroup = "Peito",
            totalSets = 4,
            repsPerSet = 10,
            description = "Deitado no banco, desça a barra até a altura do peito e empurre de volta.",
            restSeconds = 90,
            tip = "Mantenha os pés firmes no chão e as escápulas juntas.",
        ),
        Workout(
            id = "w2",
            name = "Agachamento livre",
            muscleGroup = "Pernas",
            totalSets = 4,
            repsPerSet = 12,
            description = "Com a barra apoiada nas costas, flexione os joelhos até as coxas ficarem paralelas ao chão.",
            restSeconds = 120,
            tip = "Os joelhos acompanham a direção dos pés.",
        ),
        Workout(
            id = "w3",
            name = "Puxada na frente",
            muscleGroup = "Costas",
            totalSets = 3,
            repsPerSet = 12,
            description = "Sentado no aparelho, puxe a barra até a altura do queixo.",
            restSeconds = 60,
        ),
        Workout(
            id = "w4",
            name = "Rosca direta",
            muscleGroup = "Bíceps",
            totalSets = 3,
            repsPerSet = 15,
            restSeconds = 45,
        ),
        // Sem descricao, descanso e dica: serve para testar os campos opcionais.
        Workout(id = "w5", name = "Prancha", muscleGroup = "Abdômen", totalSets = 3, repsPerSet = 1),
    )

    fun findById(id: String?): Workout? = workouts.firstOrNull { it.id == id }
}
