package br.com.kaz.domain

sealed class EntityResult<T> {
    data class Success<T>(val data: T): EntityResult<T>()
    data class Error<T>(val error: EntityErrorResult): EntityResult<T>()
}