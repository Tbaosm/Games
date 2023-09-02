package com.example.tictactoe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.tictactoe.BoardCellValue
import com.example.tictactoe.GameViewModel
import com.example.tictactoe.UserAction
import com.example.tictactoe.ui.theme.BlueCustom
import com.example.tictactoe.ui.theme.GrayBackground
import com.example.tictactoe.ui.theme.Purple40

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Player 'O': ${state.playerCircleCount}", fontSize = 16.sp)
          Text(text = "Draw: ${state.drawCount}", fontSize = 16.sp)
          Text(text = "Player 'X': ${state.playerCrossCount}", fontSize = 16.sp)
        }
        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = BlueCustom,
            fontFamily = FontFamily.Cursive
        )
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground))
        {
            BoardBase()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f),

                columns = GridCells.Fixed(3)
            )
            {
                viewModel.boardItems.forEach{ (cellNo, BoardCellValue) ->
                    item {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                viewModel.onAction(UserAction.BoardTapped(cellNo))

                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center)
                        {
                            AnimatedVisibility(visible = viewModel
                                .boardItems[cellNo] != com.example.tictactoe.BoardCellValue.NONE,
                            enter = scaleIn(tween(700))
                            ) {
                                if (BoardCellValue == com.example.tictactoe.BoardCellValue.CIRCLE){
                                    Circle()
                                }
                                else if (BoardCellValue == com.example.tictactoe.BoardCellValue.CROSS){
                                    Cross()
                                }
                            }


                        }
                    }

                }

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.hintText,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic)
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults
                    .elevatedButtonElevation(
                        defaultElevation = 5.dp,
                        hoveredElevation = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,

                )
            ) {
                Text(text = "Play again", fontSize = 16.sp)
                
            }
            
        }
        
    }
    
    
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(viewModel = GameViewModel())

}