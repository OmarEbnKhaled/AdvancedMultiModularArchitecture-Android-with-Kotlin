package com.minafarid.advancedmultimodulararchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.minafarid.advancedmultimodulararchitecture.ui.theme.AdvancedMultiModularArchitectureTheme
import com.minafarid.datastore.settings.AppSettings
import com.minafarid.datastore.settings.AppSettingsSerializer
import com.minafarid.datastore.settings.Language
import com.minafarid.datastore.settings.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

  lateinit var appSettingDataStore: DataStore<AppSettings>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    appSettingDataStore = DataStoreFactory.create(
      serializer = AppSettingsSerializer(),
      produceFile = { dataStoreFile("app_setting.json") },
      scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    )

    // val room = Room.databaseBuilder()

    enableEdgeToEdge()
    setContent {
      AdvancedMultiModularArchitectureTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = DataProvider.userName,
//                        modifier = Modifier.padding(innerPadding),
//                    )

          SettingScreen(appSettingDataStore, modifier = Modifier.padding(innerPadding))
        }
      }
    }

    val counter = 100

    print(counter)
  }

  fun mainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMain() {
  }
}

@Composable
fun SettingScreen(appSettingDataStore: DataStore<AppSettings>, modifier: Modifier) {
  val scope = rememberCoroutineScope()
  val appSetting by appSettingDataStore.data.collectAsState(initial = AppSettings())

  Column(modifier = Modifier.padding(50.dp)) {
    // Display saved language
    Text(
      text = "Language ${appSetting.language}, \nFrom (Data Store).",
      modifier = modifier,
    )
    Spacer(modifier = Modifier.height(16.dp))

    // Display saved Location
    Text(
      text = "Last Known Location: \nFrom (Data Store).",
      modifier = modifier,
    )
    appSetting.lastKnownLocations.forEach { location ->
      Spacer(modifier = Modifier.height(16.dp))
      Text(text = "Lat: ${location.lat}, Long: ${location.long}")
    }
    Spacer(modifier = Modifier.height(16.dp))

    // Create drop down menu to display language options and set location as well
    val newLocation = Location(37.123, 122.908)
    Language.entries.forEach { language ->
      DropdownMenuItem(text = { Text(text = language.name) }, onClick = {
        scope.launch {
          appSettingDataStore.updateData { currentSettings ->
            currentSettings.copy(
              language = language,
              lastKnownLocations = currentSettings.lastKnownLocations.add(newLocation),
            )
          }
        }
      })
    }
    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
@Suppress("FunctionNaming")
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Text(
      text = "Hello $name, \nFrom (DataProvider.kt).",
      modifier = modifier,
    )
    Text(
      text = "Base Url: ${BuildConfig.BASE_URL}, \nFrom (BuildConfig.java).",
      modifier = modifier,
    )
    Text(
      text = "Can Clear Cash: ${BuildConfig.CAN_CLEAR_CASH}, \nFrom (BuildConfig.java).",
      modifier = modifier,
    )
    Text(
      text = "DB Version: ${BuildConfig.DB_VERSION}, \nFrom (BuildConfig.java).",
      modifier = modifier,
    )
    Text(
      text = "Map Key: ${BuildConfig.MAP_KEY}, \nFrom (BuildConfig.java).",
      modifier = modifier,
    )
  }
}

@Preview(showBackground = true)
@Composable
@Suppress("FunctionNaming")
fun GreetingPreview() {
  AdvancedMultiModularArchitectureTheme {
    Greeting("Android")
  }
}
