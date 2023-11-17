let burger = document.getElementById("burger");
let u12 = document.getElementById("navbar-menu-open");

burger.addEventListener("click", function (){
    u12.classList.toggle("u12");
});

window.addEventListener("relize", function (){
   if(window.innerWidth > 768){
       u12.classList.toggle("u12");
   }
});