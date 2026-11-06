package br.com.ecoponto.screens.auth

// Tela para iniciar a recuperação de senha.
// O envio de e-mail ainda depende da integração futura com a API.


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.components.darkFieldColors
import br.com.ecoponto.theme.BrightGreen
import br.com.ecoponto.theme.PaleGreen
import br.com.ecoponto.theme.TextPrimary
import br.com.ecoponto.theme.TextSecondary

// @Composable define a interface desta etapa de recuperação.
// onSubmit encaminha o e-mail informado; onBackClick retorna ao login.
// A solicitação real de recuperação será implementada junto com a API.
@Composable
fun RecoverPasswordScreen(
    onSubmit: (String) -> Unit,
    onBackClick: () -> Unit,
    message: String? = null
) {
    // Estado local do campo de e-mail, atualizado conforme o usuário digita.
    var email by remember { mutableStateOf("") }

    // Mantém o mesmo layout-base usado nas outras telas de autenticação.
    AuthScreenContainer {
        Text("Recuperar senha", color = TextPrimary, fontSize = 25.sp)
        Text(
            "Informe o e-mail da sua conta. A recuperação será conectada ao serviço quando a API estiver disponível.",
            color = TextSecondary,
            fontSize = 13.sp
        )
        Spacer(Modifier.height(12.dp))
        // Campo de texto Material 3; value representa o conteúdo atual
        // e onValueChange atualiza o estado email.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("E-mail") },
            singleLine = true,
            colors = darkFieldColors()
        )
        // Mostra o retorno da validação ou informa que a API ainda será integrada.
        if (message != null) {
            Text(message, color = PaleGreen, fontSize = 12.sp)
        }
        // Encaminha o e-mail sem espaços nas extremidades para a navegação.
        Button(
            onClick = { onSubmit(email.trim()) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = BrightGreen)
        ) {
            Text("Continuar", color = TextPrimary)
        }
        TextButton(onClick = onBackClick, modifier = Modifier.fillMaxWidth()) {
            Text("Voltar para o login", color = PaleGreen)
        }
    }
}
