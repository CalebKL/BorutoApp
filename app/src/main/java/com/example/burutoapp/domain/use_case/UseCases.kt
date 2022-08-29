package com.example.burutoapp.domain.use_case

import com.example.burutoapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.example.burutoapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)
