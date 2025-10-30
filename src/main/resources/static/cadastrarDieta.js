document.getElementById("formDieta").addEventListener("submit", async function(event) {
    event.preventDefault();

    const pacienteId = document.getElementById("pacienteId").value;
    const nutricionistaId = document.getElementById("nutricionistaId").value;

    const dataInicio = document.getElementById("dataInicio").value;
    const dataFim = document.getElementById("dataFim").value;
    const objetivo = document.getElementById("objetivo").value;

    const refeicoes = Array.from(document.querySelectorAll(".refeicao-container")).map(ref => {
        const nomeRefeicao = ref.querySelector("input[name='nomeRefeicao']").value;
        const horarioRefeicao = ref.querySelector("input[name='horarioRefeicao']").value;
        const descricaoRefeicao = ref.querySelector("textarea[name='descricaoRefeicao']").value;

        const itens = Array.from(ref.querySelectorAll(".itensContainer .form-group")).map(item => ({
            alimento: item.querySelector("input[name='alimento']").value,
            quantidade: parseFloat(item.querySelector("input[name='quantidade']").value),
            unidadeMedida: item.querySelector("input[name='unidadeMedida']").value,
            resumoNutricional: null
        }));

        return { nomeRefeicao, horarioRefeicao, descricaoRefeicao, itens };
    });

    const dieta = {
        pacienteId,
        nutricionistaId,
        dataInicio,
        dataFim,
        objetivo,
        refeicoes
    };

    try {
        const response = await fetch("http://localhost:8080/api/dietas", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dieta)
        });

        if (response.ok) {
            alert("Dieta cadastrada com sucesso!");
            window.location.href = "paciente-detalhes.html?id=" + pacienteId;
        } else {
            const msg = await response.text();
            alert("Erro ao cadastrar dieta: " + msg);
        }
    } catch (error) {
        alert("Erro de conexão: " + error.message);
    }
});
