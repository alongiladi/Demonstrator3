package com.example.demonstrator3 // תוודא שזו החבילה שלך

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // חשוב לייבא את זה!
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demonstrator3.data.ShoppingItem
import com.example.demonstrator3.ShoppingViewModel
import com.example.demonstrator3.Screens.AddItemScreen
import com.example.demonstrator3.Screens.ShoppingListScreen
import com.example.demonstrator3.Screens.ShoppingItemRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // יצירת ה-ViewModel פעם אחת ברמה הגבוהה ביותר
            // אנחנו משתמשים ב-Factory שבנינו בשיעור הקודם!
            val viewModel: ShoppingViewModel = viewModel(factory = ShoppingViewModel.Factory)

            // הגדרת מערכת הניווט
            val navController = rememberNavController()

            // מיכל הניווט (החלפת המסכים)
            NavHost(navController = navController, startDestination = "list_screen") {

                // מסך 1: הרשימה
                composable("list_screen") {
                    ShoppingListScreen(
                        viewModel = viewModel,
                        onNavigateToAdd = { navController.navigate("add_screen") }
                    )
                }

                // מסך 2: הוספה
                composable("add_screen") {
                    AddItemScreen(
                        viewModel = viewModel,
                        onNavigateBack = { navController.popBackStack() } // חזור אחורה
                    )
                }
            }
        }
    }
}