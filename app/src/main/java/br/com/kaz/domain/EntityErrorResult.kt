package br.com.kaz.domain

sealed class EntityErrorResult{
    object InvalidUser: EntityErrorResult()
    object WeakPassword: EntityErrorResult()
    object InvalidCredentials: EntityErrorResult()
    object UserCollision: EntityErrorResult()
    object OtherException: EntityErrorResult()
}