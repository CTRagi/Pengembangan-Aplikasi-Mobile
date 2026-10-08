package com.mahawira.tugaspraktikum4.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahawira.tugaspraktikum4.ui.components.LabeledTextField

@Composable
fun EditProfileForm(
    initialName: String,
    initialBio: String,
    onSave: (name: String, bio: String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var name by rememberSaveable { mutableStateOf(initialName) }
    var bio by rememberSaveable { mutableStateOf(initialBio) }
    val isNameValid = name.isNotBlank()

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.titleLarge,
            )

            LabeledTextField(
                label = "Nama",
                value = name,
                onValueChange = { name = it },
                errorMessage = if (isNameValid) null else "Nama tidak boleh kosong",
            )

            LabeledTextField(
                label = "Bio",
                value = bio,
                onValueChange = { bio = it },
                singleLine = false,
                minLines = 3,
                maxLines = 5,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            ) {
                OutlinedButton(onClick = onCancel) {
                    Text("Batal")
                }
                Button(
                    onClick = { onSave(name, bio) },
                    enabled = isNameValid,
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}