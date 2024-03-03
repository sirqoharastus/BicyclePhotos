package com.abdulqohar.bicyclephotos.domain.usecase

import com.abdulqohar.bicyclephotos.data.repository.FakeBicyclePhotosRepository
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.util.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetBicyclePhotosUseCaseTest {

    // Create a fake repository for testing
    private val fakeRepository = FakeBicyclePhotosRepository()

    // Create an instance of GetBicyclePhotosUseCase with the fake repository
    private val useCase = GetBicyclePhotosUseCase(fakeRepository)

    @Test
    fun `invoke should return correct flow`() = runBlocking {
        // Call the use case
        val resultFlow: Flow<Resource<MutableList<BicyclePhotoItem>>> = useCase.invoke()
        val repositoryFlow:Flow<Resource<MutableList<BicyclePhotoItem>>> = fakeRepository.getBicyclePhotos()

        // Collect the flow to a list
        val resultList = resultFlow.toList()
        val repositoryResult = repositoryFlow.toList()

        // Assert that the result list contains the expected data
        assertEquals(repositoryResult.first(), resultList.first())
    }


    @Test
    fun `invoke should return empty list`() = runBlocking {
        // Set up the fake repository to return an empty list
        fakeRepository.setReturnEmptyList(true)

        val resultFlow = useCase.invoke()
        val resultList = resultFlow.toList()

        // Assert that the result is a success with an empty list
        assertEquals(Resource.Success(emptyList<BicyclePhotoItem>()), resultList.first())
    }

    @Test
    fun `invoke should handle error`() = runBlocking {
        // Set up the fake repository to return an error
        fakeRepository.setReturnError(true)

        val resultFlow = useCase.invoke()
        val resultList = mutableListOf<Resource<MutableList<BicyclePhotoItem>>>()

        // Collect the flow and store the results
        resultFlow.collect { result ->
            resultList.add(result)
        }

        // Assert that the result is an error
        assertEquals(1, resultList.size)
        assertEquals(Resource.Error<BicyclePhotoItem>("Failed to fetch bicycle photos"), resultList.first())
    }

    @Test
    fun `invoke should handle loading state`() = runBlocking {
        // Set up the fake repository to return loading state
        fakeRepository.setLoading(true)

        val resultFlow = useCase.invoke()
        val resultList = mutableListOf<Resource<MutableList<BicyclePhotoItem>>>()

        // Collect the flow and store the results
        resultFlow.collect { result ->
            resultList.add(result)
        }

        // Assert that the result is a loading state
        assertEquals(1, resultList.size)
        assertEquals(Resource.Loading<BicyclePhotoItem>(), resultList.first())
    }
}