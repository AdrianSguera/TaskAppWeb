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