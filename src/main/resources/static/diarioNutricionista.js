document.addEventListener("DOMContentLoaded", function() {
    const selecaoPaciente = document.getElementById('pacientesSelect');
    const listaDiarios = document.getElementById('listaDiarios');

    fetch('http://localhost:8080/api/pacientes')
    .then(res => res.json())
    .then(pacientes => {
        for(const p of pacientes) {
            const option = document.createElement("option");
            option.value = p.id;
            option.textContent = p.nome;
            selecaoPaciente.appendChild(option);
            }
        });

    selecaoPaciente.addEventListener("change", function(){
        const pacienteId = selecaoPaciente.value;
        if(!pacienteId) return;

        fetch(`http://localhost:8080/diario/paciente/${pacienteId}`)
        .then(res => res.json())
        .then(diarios => {
            listaDiarios.innerHTML = "";
            for(const d of diarios){
                const div = document.createElement('div');
                div.innerHTML = `
                        <h3>${d.titulo}</h3>
                        <p>${d.conteudo}</p>
                        <small>Data: ${d.data}</small>
                    `;
                        listaDiarios.appendChild(div);
            }
        })
    })
});