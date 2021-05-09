package com.jaemin.features.presentation.main

enum class MainOptions(val menuId : Int) {
    ADDGALLERY(1000),
    LOGOUT(2000),
    MYPAGE(3000);
    fun id() : Int = menuId
}