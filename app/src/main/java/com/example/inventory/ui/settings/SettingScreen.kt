package com.example.inventory.ui.settings

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination

object SettingDestination : NavigationDestination {
    override val route = "settings"
    override val titleRes = R.string.setting_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: SettingViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(SettingDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            val field1 = remember { mutableStateOf(viewModel.settingUiState.defaultSupplier) }
            OutlinedTextField(
                value = field1.value,
                //onValueChange = { onValueChange(itemDetails.copy(name = it)) },
                onValueChange = {
                    field1.value = it
                    viewModel.updateSettingUiState(viewModel.settingUiState.copy(defaultSupplier = it)) },
                label = { Text(stringResource(R.string.default_distributor_name)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                singleLine = true,
                isError = !viewModel.validateSupplier()
            )
            val field2 = remember { mutableStateOf(viewModel.settingUiState.defaultEmail) }
            OutlinedTextField(
                value = field2.value,
                onValueChange = {
                    field2.value = it
                    viewModel.updateSettingUiState(viewModel.settingUiState.copy(defaultEmail = it)) },
                label = { Text(stringResource(R.string.default_distributor_email)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                singleLine = true,
                isError = !viewModel.validateEmail()
            )
            val field3 = remember { mutableStateOf(viewModel.settingUiState.defaultPhone) }
            OutlinedTextField(
                value = field3.value,
                onValueChange = {
                    field3.value = it
                    viewModel.updateSettingUiState(viewModel.settingUiState.copy(defaultPhone = it)) },
                label = { Text(stringResource(R.string.default_distributor_phone)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                singleLine = true,
                isError = !viewModel.validatePhone()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Substitute default values", style = TextStyle(fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)
                )
                Spacer(Modifier.weight(1f))
                val checkedState = remember { mutableStateOf(viewModel.settingUiState.useDefaultValues) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        viewModel.updateSettingUiState(viewModel.settingUiState.copy(useDefaultValues = it))
                        Log.d("aaa", viewModel.settingUiState.toString())
                    }
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Hide sensitive data", style = TextStyle(fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)
                )
                Spacer(Modifier.weight(1f))
                val checkedState = remember { mutableStateOf(viewModel.settingUiState.hideSensitiveData) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        viewModel.updateSettingUiState(viewModel.settingUiState.copy(hideSensitiveData = it))
                        Log.d("aaa", viewModel.settingUiState.toString())
                    }
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Allow sharing data", style = TextStyle(fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)
                )
                Spacer(Modifier.weight(1f))
                val checkedState = remember { mutableStateOf(viewModel.settingUiState.allowSharingData) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        viewModel.updateSettingUiState(viewModel.settingUiState.copy(allowSharingData = it))
                        Log.d("aaa", viewModel.settingUiState.toString())
                    }
                )
            }
            Button(
                onClick = {
                    viewModel.saveSetting()
                    navigateBack.invoke()
                },
                enabled = viewModel.validateSupplier() && viewModel.validateEmail() && viewModel.validatePhone(),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save_action))
            }
        }
    }
}