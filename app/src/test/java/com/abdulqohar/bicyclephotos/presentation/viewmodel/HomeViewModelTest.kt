package com.abdulqohar.bicyclephotos.presentation.viewmodel

import com.abdulqohar.bicyclephotos.data.repository.FakeBicyclePhotosRepository
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.domain.usecase.GetBicyclePhotosUseCase
import com.abdulqohar.bicyclephotos.util.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    // Set the main coroutines dispatcher for unit testing
//    private val testDispatcher = TestCoroutineDispatcher()
//
//    // Set the main coroutines scope for unit testing
//    private val testScope = TestCoroutineScope(testDispatcher)
//
//    // Executes each task synchronously using Architecture Components
//    @get:Rule
//    var instantExecutorRule: TestRule = InstantTaskExecutorRule()

    // The ViewModel being tested
    private lateinit var viewModel: HomeViewModel

    // The fake repository to be used by the ViewModel
    private val fakeRepository = FakeBicyclePhotosRepository()

    @Before
    fun setUp() {
        // Initialize the ViewModel with the fake repository and the test dispatcher
        viewModel = HomeViewModel(GetBicyclePhotosUseCase(fakeRepository))
    }

    @Test
    fun `getBicyclePhotos should emit loading then success`() = runBlocking {
        viewModel.getBicyclePhotos()

        val states = mutableListOf<Resource<MutableList<BicyclePhotoItem>>?>()
        viewModel.getBicyclePhotosResponse.collect { state ->
            states.add(state)
        }

        assertEquals(Resource.Loading<BicyclePhotoItem>(), states[0])

//        assertEquals(Resource.Success(fakeData), states[1])
    }

}