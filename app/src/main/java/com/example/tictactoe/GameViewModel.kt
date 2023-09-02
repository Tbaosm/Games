package com.example.tictactoe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    var state = mutableStateOf(GameState())

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE
    )
    fun onAction(action: UserAction) {
        when(action){
            is UserAction.BoardTapped -> {
                TODO()
            }
            UserAction.PlayAgainButtonClicked -> {

            }
        }


    }
}