document.getElementById("formLogin").addEventListener("submit", async function(e) {
    e.preventDefault();

    const loginInput = document.getElementById("login").value;
    const senhaInput = document.getElementById("senha").value;

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ crnUf: loginInput, senha: senhaInput })
        });

        if (response.ok) {
            const data = await response.json();
            
            console.log("Login bem-sucedido! Dados recebidos:", data);

            if (!data.id || !data.tipo) {
                alert("Erro: A resposta do servidor está incompleta. Contate o suporte.");
                return;
            }

            localStorage.setItem('nutricionistaLogado', JSON.stringify(data));
            console.log("Dados salvos no localStorage com a chave 'nutricionistaLogado'");

            if (data.tipo === "PACIENTE") {
                window.location.href = "TelaPrincipalPac.html";
            } else if (data.tipo === "NUTRICIONISTA") {
                window.location.href = "TelaPrincipalNutri.html";
            } else {
                alert("Usuário sem tipo definido.");
            }
        } else {
            alert("Login (CRN) ou senha inválidos!");
        }
    } catch (error) {
        console.error("Erro ao conectar com o backend:", error);
        alert("Erro de conexão com o servidor. Verifique se o backend está rodando.");
    }
});