package com.sergio.gymlog.domain.user

import com.sergio.gymlog.data.model.repository.ApplicationData
import com.sergio.gymlog.data.repository.access.LoginRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val applicationData: ApplicationData,
    private val loginRepository: LoginRepository
) {

    operator fun invoke(){

        applicationData.clear()
        loginRepository.logout()

    }

}