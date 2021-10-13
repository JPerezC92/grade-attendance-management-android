package com.example.gradeattendancemanagement.miscellaneous.hooks

import androidx.compose.runtime.*
import com.example.gradeattendancemanagement.auth.types.UseFetchResult
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse


@Composable
fun <T> useFetch(
    repository: Repository<Resource<SuccessfulResponse<T>>>
): UseFetchResult<T> {

    val loading = useLoading()

    val data = remember { mutableStateOf<SuccessfulResponse<T>?>(null) }
    val error = remember { mutableStateOf<String?>(null) }


    val response =
        produceState<Resource<SuccessfulResponse<T>>>(
            initialValue = Resource.Loading()
        ) {
            value = repository.execute()
        }.value

    LaunchedEffect(repository) {
        loading.startLoading()
        when (response) {
            is Resource.Success -> {
                data.value = response.data
                loading.finishLoading()
            }
            is Resource.Error -> {
                error.value = response.message!!
                loading.finishLoading()
            }
            is Resource.Loading -> {
                loading.finishLoading()
            }
        }
    }

    return UseFetchResult(data = data.value, error = error.value, isLoading = loading.isLoading)
}