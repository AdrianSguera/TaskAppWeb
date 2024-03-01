document.addEventListener("DOMContentLoaded", function() {
    let optionBtns = document.querySelectorAll(".option-btn");
    let optionSections = document.querySelectorAll(".option-section");

    optionBtns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            // Ocultar todas las secciones
            optionSections.forEach(function(section) {
                section.style.display = "none";
            });

            // Mostrar la sección correspondiente al botón clickeado
            let sectionId = btn.id.split("-")[0] + "-section";
            let sectionToShow = document.getElementById(sectionId);
            sectionToShow.style.display = "block";
        });
    });
});

function deleteTask(idTask){
    let baseUrl = window.location.protocol + "//" + window.location.host + "/TaskAppWeb_war/api?idTask=" + idTask;

    fetch(baseUrl, {
        method: 'GET',
        headers: {
            'Content-Type': 'text',
        },

    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("tableTask").innerHTML = data;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


