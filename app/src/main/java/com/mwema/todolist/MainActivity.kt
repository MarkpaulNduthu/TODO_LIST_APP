package com.mwema.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mwema.todolist.data.TODODatabase
import com.mwema.todolist.data.TODORepositoryImplementation
import com.mwema.todolist.ui.addtodo.AddEditTODOScreen
import com.mwema.todolist.ui.addtodo.AddEditTODOViewModel
import com.mwema.todolist.ui.addtodo.AddEditTodoViewModelFactory
import com.mwema.todolist.ui.theme.TODOLISTTheme
import com.mwema.todolist.ui.todo_list.TODOScreen
import com.mwema.todolist.ui.todo_list.TODOViewModel
import com.mwema.todolist.ui.todo_list.TODOViewModelFactory
import com.mwema.todolist.util.Routes
import com.mwema.todolist.util.UiEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<TODOViewModel>(
            factoryProducer = {
                TODOViewModelFactory(
                    repo = TODORepositoryImplementation(
                        TODODatabase
                            .getDB(this)
                            .todoDao()
                    )
                )
            }
        )

        val addEditViewModel by viewModels<AddEditTODOViewModel>(
            factoryProducer = {
                AddEditTodoViewModelFactory(
                    repo = TODORepositoryImplementation(
                        TODODatabase
                            .getDB(this)
                            .todoDao()
                    )
                )
            }
        )


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOLISTTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TODOLIST
                ) {
                    composable(Routes.TODOLIST) {
                        TODOScreen(
                            viewModel
                        ) { uiEvent ->
                            when (uiEvent) {
                                is UiEvent.Navigate -> {
                                    navController.navigate(uiEvent.route)
                                }

                                else -> Unit
                            }
                        }
                    }
                    composable(
                        Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument("todoId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditTODOScreen(
                            viewModel = addEditViewModel
                        ){
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}