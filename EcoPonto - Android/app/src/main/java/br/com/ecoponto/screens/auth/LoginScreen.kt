package br.com.ecoponto.screens.auth

// Tela de entrada do usuário. A autenticação real será conectada à API futuramente.
// Por enquanto, esta tela coleta os dados e os envia para a função de retorno onLogin.


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

// @Composable informa ao Compose que esta função descreve elementos da interface.
// Os parâmetros são callbacks: a tela avisa a navegação quando o usuário tenta entrar,
// escolhe cadastrar-se ou solicita recuperar a senha. message exibe o retorno da validação.
@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit,
    message: String? = null
) {
    // remember mantém os valores enquanto esta tela permanece na composição.
    // mutableStateOf cria estados observáveis: quando mudam, o Compose atualiza a interface.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // Controla se a senha aparece legível ou protegida por PasswordVisualTransformation.
    var passwordVisible by remember { mutableStateOf(false) }

    // Reutiliza o contêiner visual compartilhado entre as telas de autenticação.
    AuthScreenContainer {
        Text("Bem-vindo de volta!", color = TextPrimary, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Entre na sua conta para continuar.", color = TextSecondary, fontSize = 13.sp)
        Spacer(Modifier.height(12.dp))

        // OutlinedTextField é um campo de texto do Material 3.
        // value mostra o estado atual e onValueChange atualiza esse estado a cada digitação.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("E-mail") },
            singleLine = true,
            colors = darkFieldColors()
        )
        // O campo de senha usa uma transformação visual para ocultar os caracteres.
        // trailingIcon adiciona o controle que alterna entre mostrar e ocultar a senha.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(if (passwordVisible) "Ocultar" else "Mostrar", color = PaleGreen)
                }
            },
            colors = darkFieldColors()
        )

        // Exibe mensagens de validação ou de retorno enviadas pela navegação.
        if (message != null) {
            Text(message, color = PaleGreen, fontSize = 12.sp)
        }

        TextButton(onClick = onRecoverClick, modifier = Modifier.fillMaxWidth()) {
            Text("Esqueci minha senha", color = PaleGreen)
        }
        // Button executa o callback com os valores digitados; a validação fica fora da tela.
        Button(
            onClick = { onLogin(email.trim(), password) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = BrightGreen)
        ) {
            Text("Entrar", color = TextPrimary, fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = onRegisterClick, modifier = Modifier.fillMaxWidth()) {
            Text("Não tem uma conta? Cadastre-se", color = PaleGreen)
        }
    }
}

// Contêiner reutilizável para manter alinhamento, rolagem e espaçamento iguais
// nas telas de login, cadastro e recuperação de senha.
@Composable
internal fun AuthScreenContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "EcoPonto",
            color = BrightGreen,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(22.dp))

        // Executa o conteúdo recebido usando o escopo da própria Column.
        content(this)
    }
}
