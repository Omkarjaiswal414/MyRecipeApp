package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ReceipeApp(navController: NavHostController){
    val recipeViewModel:MainViewModel= viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController=navController, startDestination = Screen.RecipeScreen.route){
        composable(route=Screen.RecipeScreen.route) {
            RecipeScreen(viewState=viewState, navigateToDetails = {

                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(Screen.DetailScreen.route) {
            val category=navController.previousBackStackEntry?.savedStateHandle?.get<Category>(
                "cat")?: Category("","","","")
            CategoryDetailScreen(category=category)
        }

    }


}