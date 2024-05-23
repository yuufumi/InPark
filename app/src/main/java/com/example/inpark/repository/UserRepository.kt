package com.example.inpark.repository

import com.example.inpark.data.dao.UserDao
import com.example.inpark.data.model.User

class UserRepository( private val userDao: UserDao) {
    fun getAllUsers() = userDao.getAllUsers()
    fun getUserByEmail(email: String) = userDao.getUserByEmail(email)
    fun addUser(user: User) = userDao.addUser(user)
    fun addUsers(vararg users: User) = userDao.addUsers(*users)
    fun updateUser(user: User) = userDao.updateUser(user)
    fun deleteUser(user: User) = userDao.deleteUser(user)
}