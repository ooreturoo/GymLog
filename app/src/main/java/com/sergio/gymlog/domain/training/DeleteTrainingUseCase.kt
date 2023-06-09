package com.sergio.gymlog.domain.training

import com.sergio.gymlog.data.model.repository.ApplicationData
import com.sergio.gymlog.data.repository.user.TrainingsRepository
import com.sergio.gymlog.domain.training.daily.GetDailyTrainingUseCase
import com.sergio.gymlog.domain.training.daily.RemoveDailyTrainingUseCase
import javax.inject.Inject

class DeleteTrainingUseCase @Inject constructor(
    private val applicationData: ApplicationData,
    private val trainingsRepository: TrainingsRepository,
    private val removeDailyTrainingUseCase: RemoveDailyTrainingUseCase,
    private val getDailyTrainingUseCase: GetDailyTrainingUseCase
) {

    suspend operator fun invoke(trainingID : String){

        trainingsRepository.deleteUserTraining(applicationData.userInfo.id, trainingID)

        val dailyTraining = getDailyTrainingUseCase()

        if (dailyTraining?.training?.id == trainingID){

            removeDailyTrainingUseCase()

        }

        val position = applicationData.userTrainings.indexOfFirst { it.id == trainingID }

        applicationData.userTrainings.removeAt(position)
        
    }

}