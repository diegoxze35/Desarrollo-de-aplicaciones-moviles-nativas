package com.example.navigationactivity.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.navigationactivity.R

@Composable
fun SelectionScreen() {
    var checkboxState by remember { mutableStateOf(false) }
    @StringRes var radioSelection: Int by remember { mutableIntStateOf(R.string.selection_option_1) }
    var switchState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.selection_description),
            style = MaterialTheme.typography.bodyMedium
        )

        // CheckBox
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(R.string.selection_checkbox_title),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(R.string.selection_checkbox_description),
                    style = MaterialTheme.typography.bodySmall
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Checkbox(
                        checked = checkboxState,
                        onCheckedChange = { checkboxState = it }
                    )
                    Text(stringResource(R.string.selection_checkbox_text))
                }
            }
        }

        // RadioButton
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(R.string.selection_radiobutton_title),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(R.string.selection_radiobutton_description),
                    style = MaterialTheme.typography.bodySmall
                )

                val radioOptions: List<Int> = listOf(
                    R.string.selection_option_1,
                    R.string.selection_option_2,
                    R.string.selection_option_3
                )

                radioOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (option == radioSelection),
                                onClick = { radioSelection = option }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = (option == radioSelection),
                            onClick = { radioSelection = option }
                        )
                        Text(
                            text = stringResource(option),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }

        // Switch
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(R.string.selection_switch_title),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(R.string.selection_switch_description),
                    style = MaterialTheme.typography.bodySmall
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(stringResource(R.string.selection_notifications))
                    Switch(
                        checked = switchState,
                        onCheckedChange = { switchState = it }
                    )
                }
            }
        }

        // Status display
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.selection_current_state), fontWeight = FontWeight.Bold)
                Text(
                    stringResource(
                        R.string.selection_checkbox_state,
                        if (checkboxState) stringResource(R.string.selection_checkbox_checked) else stringResource(
                            R.string.selection_checkbox_unchecked
                        )
                    )
                )
                Text(stringResource(R.string.selection_radiobutton_state, radioSelection))
                Text(
                    stringResource(
                        R.string.selection_switch_state,
                        if (switchState) stringResource(R.string.selection_switch_activated) else stringResource(
                            R.string.selection_switch_deactivated
                        )
                    )
                )
            }
        }
    }
}