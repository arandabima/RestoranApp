package com.example.restoranapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restoranapp.data.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    prefsManager: PreferencesManager,
    onNavigateToEdit: () -> Unit,
    onBack: () -> Unit
) {
    var refreshKey by remember { mutableIntStateOf(0) }
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var jamBuka by remember { mutableStateOf("") }

    LaunchedEffect(refreshKey) {
        nama = prefsManager.getNama()
        alamat = prefsManager.getAlamat()
        deskripsi = prefsManager.getDeskripsi()
        jamBuka = prefsManager.getJamBuka()
    }

    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                refreshKey++
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Restoran") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Profil")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileInfoCard(label = "🏠 Nama Restoran", value = nama)
            ProfileInfoCard(label = "📍 Alamat", value = alamat)
            ProfileInfoCard(label = "📝 Deskripsi", value = deskripsi)
            ProfileInfoCard(label = "🕐 Jam Buka", value = jamBuka)

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onNavigateToEdit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("✏️  Edit Profil")
            }
        }
    }
}

@Composable
fun ProfileInfoCard(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value.ifEmpty { "-" },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}