package com.example.navigationactivity.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.navigationactivity.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldsScreen() {
    var basicText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var numberText by remember { mutableStateOf("") }
    var multilineText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.text_fields_description),
            style = MaterialTheme.typography.bodyMedium,
        )

        // Basic TextField
        OutlinedTextField(
            value = basicText,
            onValueChange = { basicText = it },
            label = { Text(stringResource(R.string.text_field_basic_label)) },
            placeholder = { Text(stringResource(R.string.text_field_basic_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        // Password TextField
        OutlinedTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text(stringResource(R.string.text_field_password_label)) },
            placeholder = { Text(stringResource(R.string.text_field_password_placeholder)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Number TextField
        OutlinedTextField(
            value = numberText,
            onValueChange = { numberText = it },
            label = { Text(stringResource(R.string.text_field_number_label)) },
            placeholder = { Text(stringResource(R.string.text_field_number_placeholder)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Multiline TextField
        OutlinedTextField(
            value = multilineText,
            onValueChange = { multilineText = it },
            label = { Text(stringResource(R.string.text_field_multiline_label)) },
            placeholder = { Text(stringResource(R.string.text_field_multiline_placeholder)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            minLines = 3,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )

        // Display current values
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(R.string.text_fields_current_values),
                    fontWeight = FontWeight.Bold
                )
                Text(stringResource(R.string.text_fields_basic_value, basicText))
                Text(
                    stringResource(
                        R.string.text_fields_password_value,
                        "*".repeat(passwordText.length)
                    )
                )
                Text(stringResource(R.string.text_fields_number_value, numberText))
                Text(
                    stringResource(
                        R.string.text_fields_multiline_value,
                        multilineText.take(50) + if (multilineText.length > 50) stringResource(R.string.text_fields_ellipsis) else ""
                    )
                )
            }
        }
    }
}