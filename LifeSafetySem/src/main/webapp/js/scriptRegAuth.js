document.addEventListener("DOMContentLoaded",function (){
    var form = document.getElementById("registration");

    form.addEventListener("submit", function (event){
        var login = checkInputLogin("login", "resLogin");
        var password = checkInputPass("password", "resPass");

        if((login || password)){
            warning.style.display = "none";
            event.preventDefault();
        }
    });
});
function checkInputLogin(inputFieldId, resultDivId) {
    var inputField = document.getElementById(inputFieldId);
    var resultDiv = document.getElementById(resultDivId);
    var inputField1 = inputField.value;
    var pattern = /^[a-zA-Z0-9]{6,15}$/;

    if (pattern.test(inputField1)) {
        resultDiv.textContent = "";
        return false;
    } else{
        resultDiv.textContent = "Invalid login. Enter 1 to 15 characters containing only English letters.";
        return true;

    }
}
function checkInputPass(inputFieldId, resultDivId) {
    var inputField = document.getElementById(inputFieldId);
    var resultDiv = document.getElementById(resultDivId);
    var inputField1 = inputField.value;
    var pattern = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,25}$/;

    if (pattern.test(inputField1)) {
        resultDiv.textContent = "";
        return false;
    } else {
        resultDiv.textContent = "Invalid input. Enter 8 characters containing letters and numbers.";
        return true;
    }
}