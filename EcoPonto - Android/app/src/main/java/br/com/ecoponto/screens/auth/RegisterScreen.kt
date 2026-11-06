package br.com.ecoponto.screens.auth

// Tela de criação de conta. Os campos são controlados pelo estado do Compose.
// Os dados preenchidos são enviados à navegação, onde ficam as validações temporárias.


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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.components.darkFieldColors
import br.com.ecoponto.theme.BrightGreen
import br.com.ecoponto.theme.PaleGreen
import br.com.ecoponto.theme.TextPrimary
import br.com.ecoponto.theme.TextSecondary

// @Composable declara uma função de interface do Jetpack Compose.
// onRegister envia nome, e-mail, senha e confirmação para a navegação validar.
// onLoginClick permite voltar à tela de entrada e message apresenta o resultado.
@Composable
fun RegisterScreen(
    onRegister: (String, String, String, String) -> Unit,
    onLoginClick: () -> Unit,
    message: String? = null
) {
    // Cada campo possui seu próprio estado, preservado durante a composição da tela.
    // remember + mutableStateOf permitem que a interface acompanhe o que foi digitado.
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    // Um único estado controla a visualização dos dois campos de senha.
    var passwordsVisible by remember { mutableStateOf(false) }

    // Usa o mesmo contêiner das demais telas para manter o padrão visual.
    AuthScreenContainer {
        Text("Criar conta", color = TextPrimary, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Preencha os dados para começar.", color = TextSecondary, fontSize = 13.sp)
        Spacer(Modifier.height(12.dp))

        // OutlinedTextField cria campos editáveis do Material 3.
        // onValueChange recebe o novo texto e atualiza a variável de estado correspondente.
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome completo") },
            singleLine = true,
            colors = darkFieldColors()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("E-mail") },
            singleLine = true,
            colors = darkFieldColors()
        )
        // PasswordVisualTransformation oculta a senha; VisualTransformation.None
        // mostra o texto quando o usuário ativa a opção Mostrar.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (passwordsVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = darkFieldColors()
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Confirmar senha") },
            singleLine = true,
            visualTransformation = if (passwordsVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { passwordsVisible = !passwordsVisible }) {
                    Text(if (passwordsVisible) "Ocultar" else "Mostrar", color = PaleGreen)
                }
            },
            colors = darkFieldColors()
        )

        // A mensagem informa erros de preenchimento ou a conclusão do cadastro.
        if (message != null) {
            Text(message, color = PaleGreen, fontSize = 12.sp)
        }

        // Envia os quatro valores para a navegação, que verifica os dados antes de prosseguir.
        Button(
            onClick = { onRegister(name.trim(), email.trim(), password, confirmPassword) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = BrightGreen)
        ) {
            Text("Cadastrar", color = TextPrimary, fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
            Text("Já tem uma conta? Entrar", color = PaleGreen)
        }
    }
}
